package com.example.Productmicroservice.exceptions;

public class ProductNotFoundException extends RuntimeException{

    public ProductNotFoundException(){
        super("Resource not available on server");
    }

    public ProductNotFoundException(String msg){
        super(msg);
    }

}
