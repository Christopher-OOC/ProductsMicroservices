package com.appdevelopersblog.ws.ProductsMicroservice.service;

import com.appdevelopersblog.ws.ProductsMicroservice.model.CreateProductRestModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class ProductServiceImpl implements ProductService {

    private KafkaTemplate<String, ProductCreatedEvent> kafkaTemplate;
    private Logger LOGGER = LoggerFactory.getLogger(this.getClass());

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
        SendResult<String, ProductCreatedEvent> result = kafkaTemplate.send("product-created-events-topic2", productId, productCreatedEvent).get();



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
