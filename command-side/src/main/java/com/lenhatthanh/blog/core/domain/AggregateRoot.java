package com.lenhatthanh.blog.core.domain;

import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AggregateRoot<Type> extends Entity<Type> {
    private final Log logger = LogFactory.getLog(getClass());

    private final List<DomainEvent> domainEvents = new ArrayList<>();

    /**
     * The version of the aggregate
     * It is used to checking the concurrency (optimistic locking)
     */
    private final Long aggregateVersion;

    public AggregateRoot(Type id, Long aggregateVersion) {
        super(id);
        if (aggregateVersion < 0L) {
            logger.error("The aggregate version is less than 0");
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
        logger.error("The domain event has been registered: " + event.getClass().getSimpleName());
    }

    public void publishEvents(DomainEventPublisher publisher) {
        if (domainEvents.isEmpty()) {
            return;
        }

        domainEvents.forEach(publisher::publishEvent);
        logger.error("The domain events have been published");

        // After publish events, we need to clear them
        clearDomainEvents();
    }

    public void clearDomainEvents() {
        domainEvents.clear();
        logger.error("The domain events have been cleared");
    }
}
