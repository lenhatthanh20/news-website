package com.lenhatthanh.blog.modules.user.application.even_handler;

import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.domain.event.RoleCreatedEvent;
import com.lenhatthanh.blog.modules.user.dto.RoleEventDto;
import lombok.AllArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class RoleCreatedEventHandler {
    public static final String MESSAGE_QUEUE_TOPIC = "role";
    public static final String MESSAGE_KEY = "RoleCreatedEvent";

    private KafkaTemplate<String, RoleEventDto> kafkaTemplate;

    @Async
    @EventListener
    public void handleRoleCreatedEvent(RoleCreatedEvent event) {
        System.out.println("RoleCreatedEvent: " + event.getAggregateId());
        sendMessageToKafkaBroker(event.getEventData());
    }

    private void sendMessageToKafkaBroker(Role role) {
        RoleEventDto roleEventDto = new RoleEventDto(
                role.getId().toString(),
                role.getName(),
                role.getDescription()
        );

        ProducerRecord<String, RoleEventDto> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, MESSAGE_KEY, roleEventDto);
        this.kafkaTemplate.send(record);
    }
}
