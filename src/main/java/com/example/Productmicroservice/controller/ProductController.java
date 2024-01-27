package com.example.Productmicroservice.controller;

import com.example.Productmicroservice.config.FeignClientConfigForOrderDetails;
import com.example.Productmicroservice.entity.OrderDetails;
import com.example.Productmicroservice.entity.Product;
import com.example.Productmicroservice.service.ProductService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @Autowired
    private FeignClientConfigForOrderDetails config;



    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product){
        Product data= service.createProduct(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(data);
    }

    @GetMapping
    public ResponseEntity<List<Product>> getAll(){
        return ResponseEntity.ok(service.getAll());
    }

    @GetMapping("/{productId}")
    @CircuitBreaker(name = "getOneCircuitBreaker", fallbackMethod = "handleOrderServiceDowntime")
    public ResponseEntity<Product> getOne(@PathVariable long productId){

        Product product= service.getSingle(productId);

        List<OrderDetails> orderDetailsList= config.getUsingProductId(product.getProductId());

        product.setOrderDetails(orderDetailsList);



        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> handleOrderServiceDowntime(long productId, Exception e){
        return ResponseEntity.ok(service.getSingle(productId));
    }

    @PatchMapping("/{productId}")
    public ResponseEntity<Product> updateProductPrice(@PathVariable long productId, @RequestParam double price){

        return ResponseEntity.ok(service.updatePriceUsingPatch(productId,price));
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<String> deleteProduct(@PathVariable long productId){
        return ResponseEntity.ok(service.deleteProduct(productId));
    }

    @DeleteMapping("/delete_cache")
    public String deleteSavedCache(@RequestParam long productId){
        return service.deleteCache(productId);
    }

}
