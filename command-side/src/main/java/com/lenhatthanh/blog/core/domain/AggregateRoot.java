package com.lenhatthanh.blog.core.domain;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AggregateRoot<Type> extends Entity<Type> {
    private final List<DomainEventInterface> domainEvents = new ArrayList<>();

    public AggregateRoot(Type id) {
        super(id);
    }

    protected void registerEvent(DomainEventInterface event) {
        domainEvents.add(event);
    }

    public void publishEvents(DomainEventPublisherInterface publisher) {
        domainEvents.forEach(publisher::publishEvent);

        // After publish events, we need to clear them
        clearDomainEvents();
    }

    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
