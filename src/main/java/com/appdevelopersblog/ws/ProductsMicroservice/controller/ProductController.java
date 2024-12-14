package com.appdevelopersblog.ws.ProductsMicroservice.controller;

import com.appdevelopersblog.ws.ProductsMicroservice.model.CreateProductRestModel;
import com.appdevelopersblog.ws.ProductsMicroservice.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping
    public ResponseEntity<String> createProduct(@RequestBody CreateProductRestModel product) {

        String productId = null;
        try {
            productId = productService.createProduct(product);
        } catch (Exception e) {

        }

        return ResponseEntity.status(HttpStatus.CREATED).body(productId);
    }
}
