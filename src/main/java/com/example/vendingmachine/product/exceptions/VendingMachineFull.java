package com.example.vendingmachine.product.exceptions;

public class VendingMachineFull extends Exception{

    public VendingMachineFull(){
        super("Vending Machine is full! Please remove something");
    }
}
