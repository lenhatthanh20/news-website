package com.lenhatthanh.blog.modules.user.application.even_handler;

import com.lenhatthanh.blog.core.domain.DomainEventInterface;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.dto.RoleEventDto;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    private final Log logger = LogFactory.getLog(getClass());

    @Async
    @EventListener
    public void handleRoleCreatedEvent(DomainEventInterface event) {
        sendMessageToKafkaBroker(event);
    }

    private void sendMessageToKafkaBroker(DomainEventInterface event) {
        Role role = (Role) event.getEventData();
        RoleEventDto roleEventDto = new RoleEventDto(
                role.getId().toString(),
                role.getName().getValue(),
                role.getDescription().getValue()
        );

        ProducerRecord<String, RoleEventDto> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, MESSAGE_KEY, roleEventDto);
        this.kafkaTemplate.send(record);

        logger.info("Event sent to kafka broker - RoleCreatedEvent with aggregate ID:" + event.getAggregateId() + " !!");
    }
}
