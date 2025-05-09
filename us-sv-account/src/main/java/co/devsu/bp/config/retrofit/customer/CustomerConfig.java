package co.devsu.bp.config.retrofit.customer;

import co.devsu.bp.report.infrastructure.proxy.CustomerProxy;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Dispatcher;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Configuration
@EnableConfigurationProperties(CustomerRestProperties.class)
class CustomerConfig {
    @Value("${spring.application.name}")
    private String appName;
    private final ObjectMapper objectMapper;
    private final CustomerRestProperties properties;

    @Autowired
    public CustomerConfig(CustomerRestProperties properties, ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
        this.properties = properties;
    }

    @Bean
    public CustomerProxy customerRestClient() {
        log.info(String.format("Creating Customer Rest Client for endpoint url: %s", properties.getBaseUrl()));
        OkHttpClient httpsClient = this.getDefaultHttpClientBuilder(appName, properties.getMaxRequest(), properties.getConnectTimeout(), properties.getReadTimeout(), properties.getWriteTimeout()).build();
        return (new Retrofit.Builder())
            .client(httpsClient)
            .baseUrl(properties.getBaseUrl())
            .addConverterFactory(JacksonConverterFactory.create(objectMapper))
            .build()
            .create(CustomerProxy.class);
    }

    private OkHttpClient.Builder getDefaultHttpClientBuilder(final String appName, int maxRequest, long connectTimeout, long readTimeout, long writeTimeout) {
        Dispatcher dispatcher = new Dispatcher();
        dispatcher.setMaxRequests(maxRequest);
        return (new OkHttpClient.Builder()).dispatcher(dispatcher).connectTimeout(connectTimeout, TimeUnit.SECONDS).readTimeout(readTimeout, TimeUnit.SECONDS).writeTimeout(writeTimeout, TimeUnit.SECONDS).hostnameVerifier((s, sslSession) -> true);
    }
}
