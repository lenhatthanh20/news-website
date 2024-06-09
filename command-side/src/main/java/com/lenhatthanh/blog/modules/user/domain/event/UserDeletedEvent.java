package com.lenhatthanh.blog.modules.user.domain.event;

import com.lenhatthanh.blog.core.domain.DomainEvent;
import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.modules.user.domain.entity.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class UserDeletedEvent extends ApplicationEvent implements DomainEvent {
    public UserDeletedEvent(Object source) {
        super(source);
    }

    public UserDeletedEvent(Object source, Clock clock) {
        super(source, clock);
    }

    @Override
    public Id getAggregateId() {
        User source = (User)getSource();
        return source.getId();
    }

    @Override
    public LocalDateTime getOccurredOn() {
        return LocalDateTime.now();
    }

    @Override
    public User getEventData() {
        return (User)getSource();
    }
}
