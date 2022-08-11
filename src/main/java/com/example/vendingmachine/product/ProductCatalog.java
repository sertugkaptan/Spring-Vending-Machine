package com.example.vendingmachine.product;

import java.util.HashMap;
import java.util.Map;

public class ProductCatalog {

    public Map<Long, ProductEntity> getProductList() {
        return productList;
    }

    public void put(Long index, ProductEntity product) {
        this.productList.put(index,product);
    }

    public void remove(Long id) {
        this.productList.remove(id);
    }

    private  Map<Long, ProductEntity> productList = new HashMap<>(10);

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
