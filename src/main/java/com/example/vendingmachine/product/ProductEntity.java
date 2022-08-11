package com.example.vendingmachine.product;


import lombok.Data;

@Data
public class ProductEntity {
    private Long id;
    private String name;
    private Double price;

}
