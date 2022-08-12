package com.example.vendingmachine.product;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {

    private static ProductCatalog productCatalog;

    private Map<Long, ProductEntity> productList ;

    public static ProductCatalog getInstance() {
        if(productCatalog == null)
            productCatalog = new ProductCatalog();
        return productCatalog;
    }


    public Map<Long, ProductEntity> getProductList() {
        if(productList == null)
            productList = new HashMap<>(10);
        return productList;
    }

    public void put(Long index, ProductEntity product) {
        this.productList.put(index,product);
    }

    public void remove(Long id) {
        this.productList.remove(id);
    }


    public Integer getSize() {
        return productList.size();
    }

    public ProductCatalog(Map<Long, ProductEntity> productList) {
        this.productList = productList;
    }

    public ProductCatalog() {
        this.productList =  new HashMap<>(10);
    }

}
