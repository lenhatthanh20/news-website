package com.lenhatthanh.blog.core.domain;

public interface DomainEventInterface {
    public Object getAggregateId();

    public Long getOccurredOn();

    public Object getEventData();
}
