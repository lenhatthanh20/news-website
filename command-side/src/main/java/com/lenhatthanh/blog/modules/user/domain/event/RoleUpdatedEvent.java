package com.lenhatthanh.blog.modules.user.domain.event;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.DomainEventInterface;
import com.lenhatthanh.blog.modules.user.domain.Role;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;
import java.time.LocalDateTime;

public class RoleUpdatedEvent extends ApplicationEvent implements DomainEventInterface {
    public RoleUpdatedEvent(Object source) {
        super(source);
    }

    public RoleUpdatedEvent(Object source, Clock clock) {
        super(source, clock);
    }

    @Override
    public AggregateId getAggregateId() {
        Role source = (Role) this.getSource();
        return source.getId();
    }

    @Override
    public LocalDateTime getOccurredOn() {
        return LocalDateTime.now();
    }

    @Override
    public Role getEventData() {
        return (Role) this.getSource();
    }
}
