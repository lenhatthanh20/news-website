package com.lenhatthanh.blog.core.domain;

import lombok.Getter;
import org.springframework.data.domain.DomainEvents;
import org.springframework.data.domain.AfterDomainEventPublication;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AggregateRoot<Type> extends Entity<Type> {
    @Getter(onMethod = @__(@DomainEvents))
    private final List<DomainEventInterface<Type>> domainEvents = new ArrayList<>();

    public AggregateRoot(Type id) {
        super(id);
    }

    protected void registerEvent(DomainEventInterface<Type> event) {
        domainEvents.add(event);
    }

    @AfterDomainEventPublication
    public void clearDomainEvents() {
        domainEvents.clear();
    }
}
