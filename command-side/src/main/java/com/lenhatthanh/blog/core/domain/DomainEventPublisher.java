package com.lenhatthanh.blog.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DomainEventPublisher implements DomainEventPublisherInterface {
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
