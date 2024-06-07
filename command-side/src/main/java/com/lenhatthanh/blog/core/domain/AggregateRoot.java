package com.lenhatthanh.blog.core.domain;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
public class AggregateRoot<Type> extends Entity<Type> {
    public static final Long CONCURRENCY_CHECKING_INITIAL_VERSION = 0L;
    private List<DomainEvent> domainEvents = new ArrayList<>();

    /**
     * The version of the aggregate
     * It is used to checking the concurrency (optimistic locking)
     */
    private Long aggregateVersion;

    public void setAggregateVersion(Long aggregateVersion) {
        if (aggregateVersion < CONCURRENCY_CHECKING_INITIAL_VERSION) {
            log.info("The aggregate version is less than 0");
            throw new RuntimeException();
        }

        this.aggregateVersion = aggregateVersion;
    }

    public void registerEvent(DomainEvent event) {
        for(DomainEvent domainEvent : domainEvents) {
            if (domainEvent.getClass().equals(event.getClass())) {
                return;
            }
        }

        domainEvents.add(event);
        log.info("The domain event has been registered: " + event.getClass().getSimpleName());
    }

    public void publishEvents(DomainEventPublisher publisher) {
        if (domainEvents.isEmpty()) {
            return;
        }

        domainEvents.forEach(publisher::publishEvent);
        log.info("The domain events have been published");

        // After publish events, we need to clear them
        clearDomainEvents();
    }

    public void clearDomainEvents() {
        domainEvents.clear();
        log.info("The domain events have been cleared");
    }
}
