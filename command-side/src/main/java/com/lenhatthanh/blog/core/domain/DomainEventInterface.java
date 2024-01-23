package com.lenhatthanh.blog.core.domain;

import java.time.LocalDateTime;

public interface DomainEventInterface {
    public Object getAggregateId();

    public LocalDateTime getOccurredOn();

    public Object getEventData();
}
