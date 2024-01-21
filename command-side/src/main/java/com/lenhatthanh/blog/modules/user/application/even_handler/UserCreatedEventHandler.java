package com.lenhatthanh.blog.modules.user.application.even_handler;

import com.lenhatthanh.blog.modules.user.domain.event.UserCreatedEvent;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Service
public class UserCreatedEventHandler {
    @Async
    @TransactionalEventListener
    public void handleUserCreatedEvent(UserCreatedEvent event) {
        System.out.println("UserCreatedEvent: " + event.getAggregateId());
    }
}
