package com.appdevelopersblog.ws.ProductsMicroservice.service;

import com.appdevelopersblog.ws.ProductsMicroservice.model.CreateProductRestModel;

public interface ProductService {

    String createProduct(CreateProductRestModel productRestModel);

}
