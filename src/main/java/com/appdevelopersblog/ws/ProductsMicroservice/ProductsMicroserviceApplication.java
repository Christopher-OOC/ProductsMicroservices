package com.appdevelopersblog.ws.ProductsMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.kafka.support.serializer.JsonSerializer;

@SpringBootApplication
public class ProductsMicroserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductsMicroserviceApplication.class, args);
	}

}
