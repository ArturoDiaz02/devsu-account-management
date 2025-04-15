package co.devsu.bp.customer.infrastructure.adapter.producer;

import co.devsu.bp.customer.application.port.output.AccountBrokerPort;
import co.devsu.bp.customer.domain.Customer;
import co.devsu.bp.customer.infrastructure.adapter.producer.event.CreatedAccountEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.header.Header;
import org.apache.kafka.common.header.internals.RecordHeader;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

import static co.devsu.bp.util.constant.TopicConstant.GENERATE_ACCOUNT_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
class KafkaProducer implements AccountBrokerPort {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Override
    public void createAccount(Customer customer, Double openingBalance, String accountType) throws JsonProcessingException {
        String key = customer.getUserId();
        String value = objectMapper.writeValueAsString(
            CreatedAccountEvent.builder()
                .documentNumber(customer.getDocumentNumber())
                .openingBalance(openingBalance)
                .type(accountType)
                .build()
        );

        ProducerRecord<String, String> producerRecord = buildProducerRecord(key, value, GENERATE_ACCOUNT_TOPIC);

        log.info("Sending message to Kafka topic {} with key {} and value {}", GENERATE_ACCOUNT_TOPIC, key, value);
        kafkaTemplate.send(producerRecord).whenComplete((sendResult, ex) -> {
            if (ex != null) {
                log.error("Error sending message to Kafka: {}", ex.getMessage());
            } else {
                log.info("Message sent to Kafka topic {} with offset {}", sendResult.getRecordMetadata().topic(), sendResult.getRecordMetadata().offset());
            }
        });
    }

    private ProducerRecord<String, String> buildProducerRecord(String key, String value, String topic) {
        List<Header> recordHeaders = List.of(new RecordHeader(topic, "scanner".getBytes()));
        return new ProducerRecord<>(topic, null, key, value, recordHeaders);
    }
}
