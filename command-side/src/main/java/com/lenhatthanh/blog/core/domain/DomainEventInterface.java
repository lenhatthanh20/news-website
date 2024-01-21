package com.lenhatthanh.blog.core.domain;

import java.time.LocalDateTime;

public interface DomainEventInterface<Type> {
    public LocalDateTime dateTimeOccurred = LocalDateTime.now();
    public Type getAggregateId ();
}
