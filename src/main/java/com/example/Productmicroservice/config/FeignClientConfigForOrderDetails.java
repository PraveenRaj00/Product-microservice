package com.example.Productmicroservice.config;

import com.example.Productmicroservice.entity.OrderDetails;
import com.example.Productmicroservice.entity.Product;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Service
@FeignClient(name = "ORDER-SERVICE")
public interface FeignClientConfigForOrderDetails {

    @GetMapping("/orders/product/{productId}")
    List<OrderDetails> getUsingProductId(@PathVariable long productId);


}
