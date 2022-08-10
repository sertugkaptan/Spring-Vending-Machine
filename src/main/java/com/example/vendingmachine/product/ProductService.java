package com.example.vendingmachine.product;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {
    private static Map<Long,Product> products = new HashMap<>();
    private static Long index = 0L;


    public ResponseEntity addProduct(Product product){
        try{
            if(products.size()>= 10){
                return new ResponseEntity<>("Vending Machine is full! Please remove something", HttpStatus.OK);
            }else{
                index++;
                product.setId(index);
                products.put(index,product);
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity updateProduct(Product newProduct){
        try {

            Product product = fillProduct(newProduct);
            if(!products.containsKey(product.getId()))
                return new ResponseEntity<>("No object Found", HttpStatus.OK);
            else {
                products.put(product.getId(),product);
                return new ResponseEntity<>("Updated Successfully!", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    public ResponseEntity removeProduct(Long id){
        try {

            if(!products.containsKey(id))
                return new ResponseEntity<>("No object Found", HttpStatus.OK);
            else{
                products.remove(id);
                return new ResponseEntity<>("Successfully Removed!", HttpStatus.OK);
            }
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }
    public Product getProduct(String name){
        Product product = products.values().stream().filter(x->x.getName().toUpperCase().equals(name.toUpperCase())).findFirst().orElse(null);
        return product;
    }


    public ResponseEntity allProducts(){
        try {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    public Product fillProduct(Product newProduct){
        Product product = new Product();
        if(!newProduct.getId().equals(null))
            product = products.get(newProduct.getId());
        else{
            products.get(newProduct.getName());
        }
        if(!newProduct.getName().isEmpty())
            product.setName(newProduct.getName());
        if(!newProduct.getPrice().equals(null))
            product.setPrice(newProduct.getPrice());

        return product;
    }
}
