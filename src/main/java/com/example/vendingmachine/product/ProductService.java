package com.example.vendingmachine.product;

import com.example.vendingmachine.product.exceptions.InvalidProductId;
import com.example.vendingmachine.product.exceptions.VendingMachineFull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {
    private static Map<Long,Product> products = new HashMap<>();
    private static Long index = 0L;


    public ResponseEntity addProduct(Product product) throws VendingMachineFull {
        try{
            if(products.size()>= 10){
                throw new VendingMachineFull();
            }else{
                index++;
                product.setId(index);
                products.put(index,product);
                return new ResponseEntity<>(products, HttpStatus.OK);
            }
        }catch (Exception e){
            throw e;
        }

    }

    public ResponseEntity updateProduct(Product newProduct) throws InvalidProductId {
        try {

            Product product = fillProduct(newProduct);
            if(!products.containsKey(product.getId()))
                throw new InvalidProductId(product.getId());
            else {
                products.put(product.getId(),product);
                return new ResponseEntity<>("Updated Successfully!", HttpStatus.OK);
            }
        }catch (InvalidProductId e){
            throw e;
        }
    }

    public ResponseEntity removeProduct(Long id) throws InvalidProductId {
        try {
            if(!products.containsKey(id))
                throw new InvalidProductId(id);

            products.remove(id);
            return new ResponseEntity<>("Successfully Removed!", HttpStatus.OK);

        }catch (InvalidProductId e){
            throw e;
        }
    }

    public ResponseEntity allProducts(){
        try {
            return new ResponseEntity<>(products, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    public Product getProduct(String name){
        Product product = products.values().stream().filter(x->x.getName().toUpperCase().equals(name.toUpperCase())).findFirst().orElse(null);
        return product;
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
