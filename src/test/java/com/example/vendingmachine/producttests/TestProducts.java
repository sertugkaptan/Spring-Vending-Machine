package com.example.vendingmachine.producttests;

import com.example.vendingmachine.product.ProductCatalog;
import com.example.vendingmachine.product.ProductEntity;
import com.example.vendingmachine.product.ProductService;
import com.example.vendingmachine.product.exceptions.InvalidProductId;
import com.example.vendingmachine.product.exceptions.VendingMachineFull;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestProducts {

    @Test
    void testAddProduct() throws VendingMachineFull {
        try{
            ProductEntity productEntity = new ProductEntity();
            productEntity.setPrice(2.0);
            productEntity.setName("chocolate");

            Assertions.assertEquals("Successfully added!",new ProductService().addProduct(productEntity).getBody());
        }catch (VendingMachineFull e){
            throw e;
        }

    }

    @Test
    void testAllProducts() throws VendingMachineFull{
        ProductService productService = new ProductService();

        ProductCatalog productCatalog = ProductCatalog.getInstance();
        ProductEntity productEntity = new ProductEntity();
        productEntity.setPrice(2.0);
        productEntity.setName("chocolate");
        try {
            productService.addProduct(productEntity);
        } catch (VendingMachineFull e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(productCatalog.getProductList(),productService.allProducts().getBody());
    }

    @Test
    void testRemoveProduct() throws VendingMachineFull, InvalidProductId{
        try{
            ProductService productService = new ProductService();
            ProductEntity productEntity = new ProductEntity();
            productEntity.setPrice(2.0);
            productEntity.setName("chocolate");
            productService.addProduct(productEntity);

            Assertions.assertEquals("Successfully Removed!",productService.removeProduct(productEntity.getId()).getBody());
        }catch(VendingMachineFull  | InvalidProductId e){
            throw e;
        }
    }

    @Test
    void testUpdateProduct() throws VendingMachineFull, InvalidProductId{
        try{
            ProductService productService = new ProductService();
            ProductEntity productEntity = new ProductEntity();
            productEntity.setPrice(2.0);
            productEntity.setName("chocolate");
            productService.addProduct(productEntity);
            productEntity.setPrice(3.0);
            Long id = productService.getProduct(productEntity.getName()).getId();

            Assertions.assertEquals("Updated Successfully!",productService.updateProduct(id,productEntity).getBody());
        }catch(VendingMachineFull  | InvalidProductId e){
            throw e;
        }
    }
}
