package com.lenhatthanh.blog.core.domain;

public interface DomainEventsPublisherInterface {
    void publishEvent(DomainEventInterface event);
}
