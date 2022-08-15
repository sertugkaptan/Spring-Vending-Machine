package com.example.vendingmachine.product;

import com.example.vendingmachine.product.exceptions.InvalidProductId;
import com.example.vendingmachine.product.exceptions.VendingMachineFull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


@Service
public class ProductService {

    ProductCatalog products = ProductCatalog.getInstance();



    public ResponseEntity addProduct(ProductEntity productEntity) throws VendingMachineFull {
        try{
            if(products.getSize()>= 10){
                throw new VendingMachineFull();
            }else{
                productEntity.setId(products.getSize().longValue());
                products.put(productEntity);
                return new ResponseEntity<>("Successfully added!", HttpStatus.OK);
            }
        }catch (Exception e){
            throw e;
        }

    }

    public ResponseEntity updateProduct(Long id, ProductEntity newProductEntity) throws InvalidProductId {
        try {
            ProductEntity productEntity = fillProduct(id,newProductEntity);
            if(!products.getProductList().containsKey(id))
                throw new InvalidProductId(id);
            else {
                products.put(productEntity);
                return new ResponseEntity<>("Updated Successfully!", HttpStatus.OK);
            }
        }catch (InvalidProductId e){
            throw e;
        }
    }

    public ResponseEntity removeProduct(Long id) throws InvalidProductId {
        try {
            if(!products.getProductList().containsKey(id))
                throw new InvalidProductId(id);

            products.remove(id);
            return new ResponseEntity<>("Successfully Removed!", HttpStatus.OK);

        }catch (InvalidProductId e){
            throw e;
        }
    }

    public ResponseEntity allProducts(){
        try {
            return new ResponseEntity<>(products.getProductList(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity(e,HttpStatus.BAD_REQUEST);
        }
    }

    public ProductEntity getProduct(String name){
        //finds the product using the name otherwise it will return null.
        ProductEntity productEntity = products.getProductList().values().stream().filter(x->x.getName().toUpperCase().equals(name.toUpperCase())).findFirst().orElse(null);
        return productEntity;
    }

    public ProductEntity getProduct(Long id){
        //finds the product using the name otherwise it will return null.
        ProductEntity productEntity = products.getProductList().values().stream().filter(x->x.getId().equals(id)).findFirst().orElse(null);
        return productEntity;
    }

    public ProductEntity fillProduct(Long id,ProductEntity newProductEntity){
        ProductEntity productEntity = getProduct(id);


        // check if the product has a name if it does replace the name with the new name
        if(newProductEntity.getName() != null)
            productEntity.setName(newProductEntity.getName());
        if(newProductEntity.getPrice() != null)
            productEntity.setPrice(newProductEntity.getPrice());

        return productEntity;
    }
}
