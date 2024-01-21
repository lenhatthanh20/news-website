package com.lenhatthanh.blog.modules.user.domain.event;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.DomainEventInterface;
import com.lenhatthanh.blog.modules.user.domain.User;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Clock;

@Getter
public class UserCreatedEvent extends ApplicationEvent implements DomainEventInterface<AggregateId> {
    public UserCreatedEvent(Object source) {
        super(source);
    }

    public UserCreatedEvent(Object source, Clock clock) {
        super(source, clock);
    }

    @Override
    public AggregateId getAggregateId() {
        User source = (User) this.getSource();
        return source.getId();
    }
}
