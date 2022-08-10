package com.example.vendingmachine.vending.exceptions;

public class InvalidCoin extends Exception{

    public InvalidCoin(){
        super("Please Input a Valid Coin! (10ST, 20ST, 50ST, 1LV, 2LV)");
    }

}
