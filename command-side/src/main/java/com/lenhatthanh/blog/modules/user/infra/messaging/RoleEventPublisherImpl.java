package com.lenhatthanh.blog.modules.user.infra.messaging;

import com.lenhatthanh.blog.core.domain.DomainEvent;
import com.lenhatthanh.blog.modules.user.application.evenpublisher.RoleEventPublisher;
import com.lenhatthanh.blog.modules.user.domain.Role;
import com.lenhatthanh.blog.modules.user.dto.RoleEventDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class RoleEventPublisherImpl implements RoleEventPublisher {
    public static final String MESSAGE_QUEUE_TOPIC = "role";
    private KafkaTemplate<String, RoleEventDto> kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        Role role = (Role) event.getEventData();
        RoleEventDto roleEventDto = new RoleEventDto(
                role.getId().toString(),
                role.getName().getValue(),
                role.getDescription().getValue()
        );

        String messageKey = event.getClass().getSimpleName();
        ProducerRecord<String, RoleEventDto> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, messageKey, roleEventDto);
        kafkaTemplate.send(record);

        log.info("Event sent to Kafka broker - {} with aggregate ID: {} !!", messageKey, event.getAggregateId());
    }
}
