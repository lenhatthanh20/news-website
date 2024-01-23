package com.lenhatthanh.blog.core.domain;

public interface DomainEventPublisherInterface {
    void publishEvent(DomainEventInterface event);
}
