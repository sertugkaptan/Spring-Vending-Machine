package com.example.vendingmachine.vendingmachinetests;

import com.example.vendingmachine.product.ProductEntity;
import com.example.vendingmachine.product.ProductService;
import com.example.vendingmachine.product.exceptions.VendingMachineFull;
import com.example.vendingmachine.vending.VendingMachineService;
import com.example.vendingmachine.vending.exceptions.InvalidCoin;
import com.example.vendingmachine.vending.exceptions.PriceNotReached;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootTest
public class TestVendingMachine {

    @Test
    void testInsertMoney() throws InvalidCoin {
        try{
            VendingMachineService vendingMachineService = new VendingMachineService();
            String coin = "2LV";

            Assertions.assertEquals("Current Money: 2.0"  , vendingMachineService.insertCoins(coin).getBody());
        }catch (InvalidCoin e){
            throw e;
        }
    }

    @Test
    void testResetCoins() throws InvalidCoin {
        try{

            VendingMachineService vendingMachineService = new VendingMachineService();
            String coin = "2LV";
            vendingMachineService.insertCoins(coin);
            Assertions.assertEquals("Amount of Money Returned: 2Lv 00St"  , vendingMachineService.resetCoins().getBody());
        }catch (InvalidCoin e){
            throw e;
        }
    }

    @Test
    void testBuyProduct() throws InvalidCoin, VendingMachineFull, PriceNotReached {
        try{

            VendingMachineService vendingMachineService = new VendingMachineService();
            ProductService productService = new ProductService();
            String coin = "2LV";
            String product= "chocolate";
            vendingMachineService.insertCoins(coin);

            ProductEntity productEntity = new ProductEntity();
            productEntity.setPrice(2.0);
            productEntity.setName(product);
            productService.addProduct(productEntity);
            Assertions.assertEquals(product +" Successfully Bought! Money left: 0.0"  , vendingMachineService.buyProduct(product).getBody());
        }catch (InvalidCoin | VendingMachineFull | PriceNotReached e){
            throw e;
        }
    }
}
