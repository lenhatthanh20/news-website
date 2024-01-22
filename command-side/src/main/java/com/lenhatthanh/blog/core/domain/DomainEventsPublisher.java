package com.lenhatthanh.blog.core.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class DomainEventsPublisher implements DomainEventsPublisherInterface{
    @Autowired
    ApplicationEventPublisher applicationEventPublisher;

    public void publishEvent(DomainEventInterface event) {
        applicationEventPublisher.publishEvent(event);
    }
}
