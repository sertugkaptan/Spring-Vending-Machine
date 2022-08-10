package com.example.vendingmachine.vending.exceptions;

public class PriceNotReached extends Exception{

    public PriceNotReached(String name, Double price){
        super("The " + name + " costs " + price + ". Please insert more money!");
    }

}