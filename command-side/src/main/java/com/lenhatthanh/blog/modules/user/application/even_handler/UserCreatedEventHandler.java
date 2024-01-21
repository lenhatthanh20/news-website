package com.lenhatthanh.blog.modules.user.application.even_handler;

import com.lenhatthanh.blog.modules.user.domain.event.UserCreatedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class UserCreatedEventHandler {
    @Async
    @EventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        System.out.println("UserCreatedEvent: " + event.getAggregateId());
    }
}
