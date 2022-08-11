package com.example.vendingmachine.vending;

import com.example.vendingmachine.product.ProductEntity;
import com.example.vendingmachine.product.ProductService;
import com.example.vendingmachine.vending.exceptions.InvalidCoin;
import com.example.vendingmachine.vending.exceptions.PriceNotReached;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class VendingMachineService {

    MoneyInserted moneyInserted = new MoneyInserted();

    @Autowired
    private ProductService productService = new ProductService();


    public ResponseEntity insertCoins(String coins) throws InvalidCoin {
        try {
            if(!checkMoney(coins))
                throw new InvalidCoin();
            calculateMoney(coins);
            return new ResponseEntity<>("Current Money: " + moneyInserted.getMoney(), HttpStatus.OK);
        }catch (InvalidCoin e){
            throw e;
        }
    }

    public ResponseEntity resetCoins(){
        try{
            Integer amountofMoney = moneyInserted.getMoney().intValue();
            String decimalPart = moneyInserted.getMoney().toString().split("\\.")[1] + "0";
            moneyInserted.setMoney(0.0);
            return new ResponseEntity<>("Amount of Money Returned: "+ amountofMoney +"Lv "+ decimalPart + "St" , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity buyProduct(String name) throws PriceNotReached {
        try{
            ProductEntity productEntity =  productService.getProduct(name);
            if(moneyInserted.getMoney()< productEntity.getPrice())
                throw new PriceNotReached(productEntity.getName(), productEntity.getPrice());
            else {
                Double currentMoney =  moneyInserted.getMoney()- productEntity.getPrice();
                moneyInserted.setMoney(currentMoney);
                return new ResponseEntity<>(name +" Successfully Bought! Money left: " + moneyInserted.getMoney().toString(), HttpStatus.OK);
            }
        }catch (PriceNotReached e){
            throw e;
        }
    }

    public void calculateMoney(String coins){
        String money = coins.replaceAll("[^0-9]","");
        Integer res = Integer.parseInt(money);

        if(res%10 != 0){
            Double currentMoney = moneyInserted.getMoney() + res.doubleValue();
            moneyInserted.setMoney(currentMoney);
        }else{
            Double div = res.doubleValue()/100;
            Double currentMoney = moneyInserted.getMoney() + div;
            moneyInserted.setMoney(currentMoney);
        }
    }

    public Boolean checkMoney(String coins){
        ArrayList<String> list = new ArrayList<>();
        list.add("10ST");
        list.add("1LV");
        list.add("2LV");
        list.add("50ST");
        list.add("20ST");
        Boolean passed = false;
        for (String str: list) {
            if(coins.toUpperCase().equals(str))
                passed = true;
        }
        return passed;
    }
}
