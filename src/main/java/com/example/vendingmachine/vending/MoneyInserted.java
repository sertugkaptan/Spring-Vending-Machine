package com.example.vendingmachine.vending;

import lombok.Data;

@Data
public class MoneyInserted {

    private Double money;

    public MoneyInserted(Double money) {
        this.money = money;
    }
    public MoneyInserted() {
        this.money = 0.0;
    }

}
