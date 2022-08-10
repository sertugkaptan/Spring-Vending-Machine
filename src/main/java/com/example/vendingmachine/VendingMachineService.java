package com.example.vendingmachine;

import com.example.vendingmachine.product.Product;
import com.example.vendingmachine.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;


@Service
public class VendingMachineService {
    @Autowired
    private ProductService productService = new ProductService();

    static Double moneyInsterted = 0.0;

    public ResponseEntity insertCoins(String coins){
        try {
            if(!checkMoney(coins))
                return new ResponseEntity<>("Please Enter a Coin!", HttpStatus.OK);
            calculateMoney(coins);
            return new ResponseEntity<>("Current Money: " + moneyInsterted, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity resetCoins(){
        try{
            Integer amountofMoney = moneyInsterted.intValue();
            String decimalPart = moneyInsterted.toString().split("\\.")[1];
            moneyInsterted=0.0;
            return new ResponseEntity<>("Amount of Money Returned: "+ amountofMoney +"Lv "+ decimalPart+"St" , HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity buyProduct(String name){
        try{
            Product product =  productService.getProduct(name);
            if(moneyInsterted< product.getPrice())
                return new ResponseEntity<>("Not Enough Money! Please Insert More!", HttpStatus.OK);
            else {
                moneyInsterted -= product.getPrice();
                return new ResponseEntity<>(name +" Successfully Bought! Money left: " + moneyInsterted.toString(), HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
    }

    public void calculateMoney(String coins){
        String money = coins.replaceAll("[^0-9]","");
        Integer res = Integer.parseInt(money);

        if(res%10 != 0){
            moneyInsterted += res.doubleValue();
        }else{
            Double div = res.doubleValue()/100;
            moneyInsterted += div;
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
