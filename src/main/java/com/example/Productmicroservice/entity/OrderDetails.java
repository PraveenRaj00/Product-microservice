package com.example.Productmicroservice.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetails {

    private long orderId;
    private long productId;
    private long quantity;
    private double totalPrice;
}
