package com.lenhatthanh.blog.modules.user.domain.event;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.DomainEventInterface;
import com.lenhatthanh.blog.modules.user.domain.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class UserCreatedEvent extends ApplicationEvent implements DomainEventInterface {
    public UserCreatedEvent(Object source) {
        super(source);
    }

    public UserCreatedEvent(Object source, Clock clock) {
        super(source, clock);
    }

    @Override
    public Id getAggregateId() {
        User source = (User) this.getSource();
        return source.getId();
    }

    @Override
    public LocalDateTime getOccurredOn() {
        return LocalDateTime.now();
    }

    @Override
    public User getEventData() {
        return (User) this.getSource();
    }
}
