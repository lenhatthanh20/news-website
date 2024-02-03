package com.lenhatthanh.blog.core.domain;

import lombok.Getter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AggregateRoot<Type> extends Entity<Type> {
    private final Log logger = LogFactory.getLog(getClass());

    private final List<DomainEventInterface> domainEvents = new ArrayList<>();

    /**
     * The version of the aggregate
     * It is used to checking the concurrency
     */
    private Long aggregateVersion;

    public AggregateRoot(Type id, Long aggregateVersion) {
        super(id);
        if (aggregateVersion < 1L) {
            logger.error("The aggregate version is less than 1");
            throw new RuntimeException();
        }

        this.aggregateVersion = aggregateVersion;
    }

    protected void registerEvent(DomainEventInterface event) {
        if (domainEvents.contains(event)) return;
        domainEvents.add(event);

        logger.error("The domain event has been registered: " + event.getClass().getSimpleName());
    }

    public void publishEvents(DomainEventPublisherInterface publisher) {
        domainEvents.forEach(publisher::publishEvent);
        logger.error("The domain events have been published");

        // After publish events, we need to clear them
        clearDomainEvents();
    }

    public void clearDomainEvents() {
        domainEvents.clear();
        logger.error("The domain events have been cleared");
    }

    public void updateAggregateVersion(Long aggregateVersion) {
        if (aggregateVersion <= this.aggregateVersion) {
            logger.error("The new version of the aggregate is less than or equal to the current version");
            // Don't need to show this error to the user
            throw new RuntimeException();
        }

        this.aggregateVersion = aggregateVersion;
    }
}
