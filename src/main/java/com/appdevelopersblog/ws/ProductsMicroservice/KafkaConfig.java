package com.appdevelopersblog.ws.ProductsMicroservice;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

    NewTopic createTopic() {

        return TopicBuilder.name("product-created-events-topic").build();
    }



}
