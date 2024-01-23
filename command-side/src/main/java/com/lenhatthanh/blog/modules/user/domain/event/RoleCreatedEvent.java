package com.lenhatthanh.blog.modules.user.domain.event;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.DomainEventInterface;
import com.lenhatthanh.blog.modules.user.domain.Role;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class RoleCreatedEvent extends ApplicationEvent implements DomainEventInterface {
    public RoleCreatedEvent(Object source) {
        super(source);
    }

    public RoleCreatedEvent(Object source, Clock clock) {
        super(source, clock);
    }

    @Override
    public AggregateId getAggregateId() {
        Role source = (Role) this.getSource();
        return source.getId();
    }

    @Override
    public Long getOccurredOn() {
        return this.getTimestamp();
    }

    @Override
    public Role getEventData() {
        return (Role) this.getSource();
    }
}
