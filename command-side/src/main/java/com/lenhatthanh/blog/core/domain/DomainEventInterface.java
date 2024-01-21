package com.lenhatthanh.blog.core.domain;

import java.time.LocalDateTime;

public interface DomainEventInterface<Type> {
    public Type getAggregateId ();
}
