package com.lenhatthanh.blog.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
class KafkaConfig {
    @Bean
    public NewTopic topic1() {
        return TopicBuilder.name("authors").build();
    }

    @Bean
    public NewTopic topic2() {
        return TopicBuilder.name("articles").build();
    }
}