package com.lenhatthanh.blog.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
class KafkaConfig {
    @Bean
    public NewTopic topicUser() {
        return TopicBuilder.name("user").build();
    }

    @Bean
    public NewTopic topicArticle() {
        return TopicBuilder.name("article").build();
    }

    @Bean
    public NewTopic topicRole() {
        return TopicBuilder.name("role").build();
    }
}
