package com.example.vendingmachine.product.exceptions;

public class InvalidProductId extends Exception{

    public InvalidProductId(Long id){
        super("There is no product with id " + id +".");
    }

}
