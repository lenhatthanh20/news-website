package com.lenhatthanh.blog.core.domain;

public interface DomainEventInterface<Type> {
    public Type getAggregateId ();
}
