package com.lenhatthanh.blog.modules.post.application.eventpublisher;

import com.lenhatthanh.blog.core.domain.DomainEvent;

public interface PostEventPublisher {
    void publish(DomainEvent source);
}
