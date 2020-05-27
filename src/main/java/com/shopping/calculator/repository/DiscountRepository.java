package com.shopping.calculator.repository;

import com.shopping.calculator.discountengine.Discounts;

import java.util.List;

public interface DiscountRepository {

    List<Discounts> initializeDiscountRepository(ProductRepository productRepository);

    List<Discounts> getAllActiveDiscounts();

}
