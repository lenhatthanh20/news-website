package com.lenhatthanh.blog.modules.user.application.even_handler;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.DomainEventInterface;
import com.lenhatthanh.blog.modules.user.domain.User;
import com.lenhatthanh.blog.modules.user.domain.event.UserCreatedEvent;
import com.lenhatthanh.blog.modules.user.dto.UserEventDto;
import lombok.AllArgsConstructor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.context.event.EventListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserCreatedEventHandler {
    public static final String MESSAGE_QUEUE_TOPIC = "user";
    public static final String MESSAGE_KEY = "UserCreatedEvent";

    private KafkaTemplate<String, UserEventDto> kafkaTemplate;
    private final Log logger = LogFactory.getLog(getClass());

    @Async
    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        sendMessageToKafkaBroker(event);
    }

    private void sendMessageToKafkaBroker(DomainEventInterface event) {
        User user = (User) event.getEventData();
        UserEventDto userEventDto = new UserEventDto(
                user.getId().toString(),
                user.getName().getValue(),
                user.getEmail().getValue(),
                user.getRoleIds().stream().map(AggregateId::toString).collect(Collectors.toSet())
        );

        ProducerRecord<String, UserEventDto> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, MESSAGE_KEY, userEventDto);
        this.kafkaTemplate.send(record);

        logger.info("Event sent to kafka broker - UserCreatedEvent with aggregate ID:" + event.getAggregateId() + " !!");
    }
}
