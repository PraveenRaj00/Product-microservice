package com.example.Productmicroservice.service;

import com.example.Productmicroservice.ProductMicroserviceApplication;
import com.example.Productmicroservice.entity.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);

    List<Product> getAll();

    Product getSingle(long productId);

    Product updatePriceUsingPatch(long productId, double Price);

    String deleteProduct(long productId);

    String deleteCache(long productId);

}
