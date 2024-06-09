package com.lenhatthanh.blog.modules.user.domain.event;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.DomainEvent;
import com.lenhatthanh.blog.modules.user.domain.entity.Role;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

@Getter
public class RoleCreatedEvent extends ApplicationEvent implements DomainEvent {
    public RoleCreatedEvent(Object source) {
        super(source);
    }

    public RoleCreatedEvent(Object source, Clock clock) {
        super(source, clock);
    }

    @Override
    public Id getAggregateId() {
        Role source = (Role)getSource();
        return source.getId();
    }

    @Override
    public LocalDateTime getOccurredOn() {
        return LocalDateTime.now();
    }

    @Override
    public Role getEventData() {
        return (Role)getSource();
    }
}
