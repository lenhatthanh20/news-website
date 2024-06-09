package com.lenhatthanh.blog.modules.post.domain.event;

import com.lenhatthanh.blog.core.domain.DomainEvent;
import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.post.domain.entity.Post;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class PostCreatedEvent extends ApplicationEvent implements DomainEvent {
    public PostCreatedEvent(Object source) {
        super(source);
    }

    public PostCreatedEvent(Object source, Clock clock) {
        super(source, clock);
    }

    @Override
    public Id getAggregateId() {
        Post source = (Post)getSource();
        return source.getId();
    }

    @Override
    public LocalDateTime getOccurredOn() {
        return LocalDateTime.now();
    }

    @Override
    public Post getEventData() {
        return (Post)getSource();
    }
}