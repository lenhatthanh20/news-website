package com.lenhatthanh.blog.modules.user.application.even_handler;

import com.lenhatthanh.blog.core.domain.DomainEvent;
import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.event.UserCreatedEvent;
import com.lenhatthanh.blog.modules.user.dto.UserEventDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class UserEventHandler {
    public static final String MESSAGE_QUEUE_TOPIC = "user";

    private KafkaTemplate<String, UserEventDto> kafkaTemplate;

    @EventListener(UserCreatedEvent.class)
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        sendMessageToKafkaBroker(event);
    }

    private void sendMessageToKafkaBroker(DomainEvent event) {
        User user = (User) event.getEventData();
        List<Id> roleIds = user.getRoleIds();
        List<String> stringRoleIds = roleIds.stream().map(Id::toString).collect(Collectors.toList());
        UserEventDto userEventDto = new UserEventDto(
                user.getId().toString(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getMobilePhone().getValue(),
                user.getIsActive(),
                stringRoleIds
        );

        String messageKey = event.getClass().getSimpleName();
        ProducerRecord<String, UserEventDto> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, messageKey, userEventDto);
        this.kafkaTemplate.send(record);

        log.info("Event sent to Kafka broker - " + messageKey + " with aggregate ID: " + event.getAggregateId() + " !!");
    }
}
