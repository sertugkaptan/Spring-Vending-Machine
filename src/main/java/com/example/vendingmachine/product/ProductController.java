package com.example.vendingmachine.product;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {
    ProductService productService = new ProductService();

    @PostMapping("/addProduct")
    public ResponseEntity addProduct(@RequestBody Product product){
        try{
            return new ResponseEntity<>(productService.addProduct(product), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/allProducts")
    public ResponseEntity allProducts(){
        try{

            return new ResponseEntity<>(productService.allProducts(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }

    }

    @GetMapping("/getProduct/{name}")
    public ResponseEntity getProduct(@PathVariable String name){
        try{
            return new ResponseEntity<>(productService.getProduct(name), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }

    }

    @PostMapping("/updateProduct")
    public ResponseEntity updateProduct(@RequestBody Product newProduct){
        try {
            return new ResponseEntity<>(productService.updateProduct(newProduct), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/removeProduct/{id}")
    public ResponseEntity removeProduct(@PathVariable Long id){
        try {
            return new ResponseEntity<>(productService.removeProduct(id), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }

    }
}
