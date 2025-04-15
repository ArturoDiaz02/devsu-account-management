package co.devsu.bp.config.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

import static co.devsu.bp.util.constant.TopicConstant.GENERATE_ACCOUNT_TOPIC;

@Configuration
public class KafkaConfig {

    @Bean
    public NewTopic generateAccountTopic() {
        Map<String, String> config = new HashMap<>();
        config.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE);
        config.put(TopicConfig.RETENTION_MS_CONFIG, String.valueOf(604800000));

        return TopicBuilder
            .name(GENERATE_ACCOUNT_TOPIC)
            .replicas(1)
            .configs(config)
            .build();
    }
}
