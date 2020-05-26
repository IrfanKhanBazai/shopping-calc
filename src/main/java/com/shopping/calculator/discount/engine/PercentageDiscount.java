package com.shopping.calculator.discount.engine;

import com.shopping.calculator.model.LineItem;
import com.shopping.calculator.model.Order;
import com.shopping.calculator.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class PercentageDiscount extends Discounts{
	
	private Product eligibleProduct;	

	
	public PercentageDiscount(String name, 
							  String description,
							  BigDecimal discountRate,
							  LocalDate validFrom,
							  LocalDate expiryDate,
							  Product prod) {
		super(name,description,discountRate,validFrom,expiryDate);
		this.eligibleProduct = prod; 
	}
	
	public void applyDiscount(Order order) {		
		for (LineItem item : order.getLineItems()) {
			
			if(item.getProduct().equals(eligibleProduct)) {
				item.setDiscountAmount(calculateDiscountAmount(item.getProduct().getPrice(),item.getQuantity()));	
				
				// Add discount details for each product (e.g. Apple) in a given Order
				for (int i = 0 ; i < item.getQuantity(); i++) {
					order.addDiscountDetails(createDiscountDetails(item.getProduct().getPrice()));
				}
			}
		}		
	}
	
	private BigDecimal calculateDiscountAmount(BigDecimal price, long quantity) {
		return discountRate.multiply(new BigDecimal(quantity)).multiply(price).setScale(2, RoundingMode.HALF_UP);
	}
	
}
