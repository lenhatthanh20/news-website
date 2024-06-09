package com.lenhatthanh.blog.modules.user.domain.event;

import com.lenhatthanh.blog.core.domain.Id;
import com.lenhatthanh.blog.core.domain.DomainEvent;
import com.lenhatthanh.blog.modules.user.domain.entity.Role;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

public class RoleDeletedEvent extends ApplicationEvent implements DomainEvent {
    public RoleDeletedEvent(Object source) {
        super(source);
    }

    public RoleDeletedEvent(Object source, Clock clock) {
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
