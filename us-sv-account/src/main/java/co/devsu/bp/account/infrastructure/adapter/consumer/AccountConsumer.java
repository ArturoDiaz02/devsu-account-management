package co.devsu.bp.account.infrastructure.adapter.consumer;

import co.devsu.bp.account.application.port.input.AccountServicePort;
import co.devsu.bp.account.infrastructure.adapter.consumer.event.CreatedAccountEvent;
import co.devsu.bp.account.infrastructure.mapper.AccountMapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import static co.devsu.bp.util.constant.TopicConstant.GENERATE_ACCOUNT_TOPIC;

@Slf4j
@Component
@RequiredArgsConstructor
class AccountConsumer {

    private final ObjectMapper objectMapper;
    private final AccountMapper accountMapper;
    private final AccountServicePort accountServicePort;

    @KafkaListener(topics = GENERATE_ACCOUNT_TOPIC)
    public void createAccountConsumer(ConsumerRecord<String, String> consumerRecord){
        log.info("Received message with key: {} and value: {}", consumerRecord.key(), consumerRecord.value());
        try {
            CreatedAccountEvent createdAccountEvent = objectMapper.readValue(consumerRecord.value(), CreatedAccountEvent.class);
            accountServicePort.createAccount(
                accountMapper.toDomainFromCreationEvent(createdAccountEvent)
            );
        } catch (Exception e) {
            log.error("Error processing message: {}", e.getMessage());
        }
    }

}
