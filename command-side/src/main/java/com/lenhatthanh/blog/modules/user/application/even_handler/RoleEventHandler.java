package com.lenhatthanh.blog.modules.user.application.even_handler;

import com.lenhatthanh.blog.core.domain.DomainEvent;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.event.RoleCreatedEvent;
import com.lenhatthanh.blog.modules.user.domain.event.RoleUpdatedEvent;
import com.lenhatthanh.blog.modules.user.dto.RoleEventDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor
public class RoleEventHandler {
    public static final String MESSAGE_QUEUE_TOPIC = "role";
    private KafkaTemplate<String, RoleEventDto> kafkaTemplate;

    @EventListener(RoleCreatedEvent.class)
    public void handleRoleCreatedEvent(DomainEvent event) {
        sendMessageToKafkaBroker(event);
    }

    @EventListener(RoleUpdatedEvent.class)
    public void handleRoleUpdatedEvent(DomainEvent event) {
        sendMessageToKafkaBroker(event);
    }

    private void sendMessageToKafkaBroker(DomainEvent event) {
        Role role = (Role) event.getEventData();
        RoleEventDto roleEventDto = new RoleEventDto(
                role.getId().toString(),
                role.getName().getValue(),
                role.getDescription().getValue()
        );

        String messageKey = event.getClass().getSimpleName();
        ProducerRecord<String, RoleEventDto> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, messageKey, roleEventDto);
        this.kafkaTemplate.send(record);

        log.info("Event sent to Kafka broker - {} with aggregate ID: {} !!", messageKey, event.getAggregateId());
    }
}
