package com.example.vendingmachine.vending;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("web/api/v1/vending")
public class VendingMachineController {

    VendingMachineService vendingMachineService = new VendingMachineService();

    @GetMapping("/insertCoin/{coins}")
    public ResponseEntity insertCoin(@PathVariable String coins){
        try {
            return new ResponseEntity<>(vendingMachineService.insertCoins(coins), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e, HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/resetCoins")
    public ResponseEntity resetCoins(){
        try{
            return new ResponseEntity<>(vendingMachineService.resetCoins(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/buyProduct/{name}")
    public ResponseEntity buyProduct(@PathVariable String name){
        try{
            return new ResponseEntity<>(vendingMachineService.buyProduct(name), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e , HttpStatus.BAD_REQUEST);
        }
    }
}
