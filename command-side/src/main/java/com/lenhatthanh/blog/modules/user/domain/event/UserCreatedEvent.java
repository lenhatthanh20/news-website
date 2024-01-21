package com.lenhatthanh.blog.modules.user.domain.event;

import com.lenhatthanh.blog.core.domain.AggregateId;
import com.lenhatthanh.blog.core.domain.DomainEventInterface;
import com.lenhatthanh.blog.modules.user.domain.User;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class UserCreatedEvent implements DomainEventInterface<AggregateId> {
    private User user;

    @Override
    public AggregateId getAggregateId() {
        return this.user.getId();
    }
}
