package com.scaler.productservice.repo;

public interface CustomQueries {

    String FIND_PRODUCT_BY_TITLE = " Select * from product where title like =:title";
}
