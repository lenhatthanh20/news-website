package com.lenhatthanh.blog.modules.user.infra.messaging;

import com.lenhatthanh.blog.core.domain.DomainEvent;
import com.lenhatthanh.blog.core.domain.RoleStatus;
import com.lenhatthanh.blog.modules.user.application.evenpublisher.RoleEventPublisher;
import com.lenhatthanh.blog.modules.user.domain.entity.Role;
import com.lenhatthanh.blog.modules.user.dto.RoleEventDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
@AllArgsConstructor
public class RoleEventKafkaPublisher implements RoleEventPublisher {
    public static final String MESSAGE_QUEUE_TOPIC = "role";
    private KafkaTemplate<String, RoleEventDto> kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        Role role = (Role) event.getEventData();
        RoleEventDto roleEventDto = new RoleEventDto(
                role.getId().toString(),
                role.getName().getValue(),
                role.getDescription().getValue(),
                role.getStatus() == RoleStatus.DELETED
        );

        String messageKey = event.getClass().getSimpleName();
        ProducerRecord<String, RoleEventDto> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, messageKey, roleEventDto);
        CompletableFuture<SendResult<String, RoleEventDto>> future = kafkaTemplate.send(record);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("The Kafka broker received the message {} with aggregate ID: {} !", messageKey, event.getAggregateId());
            }
            else {
                log.info("The Kafka broker did not receive the message {} with aggregate ID: {} !", messageKey, event.getAggregateId());
            }
        });

        log.info("Event sent to Kafka broker - {} with aggregate ID: {} !!", messageKey, event.getAggregateId());
    }
}
