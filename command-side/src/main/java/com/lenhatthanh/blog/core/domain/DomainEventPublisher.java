package com.lenhatthanh.blog.core.domain;

public interface DomainEventPublisher {
    void publishEvent(DomainEvent event);
}
