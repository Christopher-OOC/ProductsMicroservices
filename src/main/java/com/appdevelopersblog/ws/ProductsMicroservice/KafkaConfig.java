package com.appdevelopersblog.ws.ProductsMicroservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    @Bean
    NewTopic createTopic() {

        return TopicBuilder
                .name("product-created-events-topic2")
                .partitions(3)
                .replicas(1)
                .config("min.insync.replicas", "1")
                .build();
    }
}
