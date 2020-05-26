package com.shopping.calculator.repository;

import com.shopping.calculator.discount.engine.Discounts;

import java.util.List;

public interface IDiscountRepository {
	
	List<Discounts> initializeDiscountRepository(IProductRepository productRepository);
	List<Discounts> getAllActiveDiscounts();

}
