package com.lenhatthanh.blog.modules.post.infra.messaging;

import com.lenhatthanh.blog.core.domain.DomainEvent;
import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.application.eventpublisher.PostEventPublisher;
import com.lenhatthanh.blog.modules.post.domain.entity.Post;
import com.lenhatthanh.blog.modules.post.dto.PostEventDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Slf4j
@Component
@AllArgsConstructor
public class PostEventKafkaPublisher implements PostEventPublisher {
    public static final String MESSAGE_QUEUE_TOPIC = "post";
    private KafkaTemplate<String, PostEventDto> kafkaTemplate;

    @Override
    public void publish(DomainEvent event) {
        Post post = (Post) event.getEventData();
        PostEventDto postEventDto = new PostEventDto(
                post.getParentId() != null ? post.getParentId().toString() : null,
                post.getTitle().getValue(),
                post.getMetaTitle(),
                post.getContent().getValue(),
                post.getSummary().getValue(),
                post.getThumbnail(),
                post.getSlug().getValue(),
                post.getStatus().name(),
                post.getPublishedAt(),
                post.getUserId().toString(),
                post.getCategoryIds().stream().map(Id::toString).collect(Collectors.toList()),
                post.getTagIds().stream().map(Id::toString).collect(Collectors.toList())
        );

        String messageKey = event.getClass().getSimpleName();
        ProducerRecord<String, PostEventDto> record = new ProducerRecord<>(MESSAGE_QUEUE_TOPIC, messageKey, postEventDto);
        kafkaTemplate.send(record);

        log.info("Event sent to Kafka broker - {} with aggregate ID: {} !!", messageKey, event.getAggregateId());
    }
}
