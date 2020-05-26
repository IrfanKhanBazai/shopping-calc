package com.shopping.calculator.repository;

import com.shopping.calculator.discount.engine.Discounts;
import com.shopping.calculator.discount.engine.PercentageDiscount;
import com.shopping.calculator.model.Product;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DiscountRepository implements IDiscountRepository {
	
    private List<Discounts> discounts = new ArrayList<Discounts> ();	
	
	public List<Discounts> initializeDiscountRepository(IProductRepository productRepository) {
		
		Product apple = productRepository.findProductByName("APPLES").get();
		Product soup = productRepository.findProductByName("SOUP").get();
		Product bread = productRepository.findProductByName("BREAD").get();
		
		PercentageDiscount percDiscountApple = new PercentageDiscount("10 % Apple Discount", "Apples 10% off :", new BigDecimal(0.10),LocalDate.now() , LocalDate.of(2020, 8, 30),apple);
		PercentageDiscount percDiscountSoup = new PercentageDiscount("5 % Soup Discount", "Soup 5% off :", new BigDecimal(0.05),LocalDate.now() , LocalDate.of(2020, 8, 30),soup);
		PercentageDiscount percDiscountBread = new PercentageDiscount("15% Bread Discount", "Bread 15% off :", new BigDecimal(0.15),LocalDate.now() , LocalDate.of(2020, 8, 30),bread);

    	discounts.add(percDiscountApple);
		discounts.add(percDiscountSoup);
		discounts.add(percDiscountBread);
		
		return discounts;
		
	}
	
	@Override
	public List<Discounts> getAllActiveDiscounts() {
		return discounts.stream().filter(t -> t.isDiscountApplicable()).collect(Collectors.toList());
	}


}
