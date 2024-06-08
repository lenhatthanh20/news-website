package com.lenhatthanh.blog.modules.user.application.evenpublisher;

import com.lenhatthanh.blog.core.domain.DomainEvent;

public interface RoleEventPublisher {
    void publish(DomainEvent event);
}
