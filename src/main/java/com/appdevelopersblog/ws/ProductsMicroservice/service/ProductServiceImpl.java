package com.appdevelopersblog.ws.ProductsMicroservice.service;

import com.appdevelopersblog.ws.ProductsMicroservice.model.CreateProductRestModel;
import com.appdevelopersblog.ws.ProductsMicroservice.model.ProductCreatedEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    public ProductServiceImpl(KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public String createProduct(CreateProductRestModel productRestModel) throws Exception {

        String productId = UUID.randomUUID().toString();

        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent(
                productId,
                productRestModel.getTitle(),
                productRestModel.getPrice(),
                productRestModel.getQuantity());

        // Synchronous approach

        LOGGER.info("Before sending event");

        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send("product-created-events-topic2", productId, productCreatedEvent).get();

        LOGGER.info("Partition: " + result.getRecordMetadata().partition());
        LOGGER.info("Topic: " + result.getRecordMetadata().topic());
        LOGGER.info("Offset: " + result.getRecordMetadata().offset());

        // Asynchronous approach
//        CompletableFuture<SendResult<String, ProductCreatedEvent>> future = kafkaTemplate.send("product-created-events-topic2", productId, productCreatedEvent);
//
//        future.whenComplete((result, exception) -> {
//           if (exception != null) {
//                LOGGER.error("***** Failed to send message: " + exception.getMessage());
//           }
//           else {
//                LOGGER.info("***** Message sent successfully: " + result.getRecordMetadata());
//           }
//        });

        //future.join();
        LOGGER.info("***** Returning product id");

        return productId;
    }
}
