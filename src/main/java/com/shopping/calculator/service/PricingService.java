package com.shopping.calculator.service;

import com.shopping.calculator.discount.engine.Discounts;
import com.shopping.calculator.model.LineItem;
import com.shopping.calculator.model.Order;
import com.shopping.calculator.model.PricingInfo;
import com.shopping.calculator.repository.IDiscountRepository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class PricingService implements IPricingService{

	private IDiscountRepository discountRepository;
	
	public PricingService (IDiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	public PricingInfo calculatePrice (Order order) {
		PricingInfo pricingDto = new PricingInfo();
		pricingDto.setTotalPriceBeforeDiscount(calculateGrossPrice(order));

		List<Discounts> discounts  =  discountRepository.getAllActiveDiscounts();

		discountRepository.getAllActiveDiscounts().stream().forEach(discount -> discount.applyDiscount(order));

		BigDecimal finalPrice = BigDecimal.ZERO;
		BigDecimal discountedPrice = BigDecimal.ZERO;

		for (LineItem item : order.getLineItems()) {

			BigDecimal subTotal = calculateSubTotalAfterDiscount(item.getProduct().getPrice(),item.getQuantity(),item.getDiscountAmount());
			finalPrice = finalPrice.add(subTotal);
			discountedPrice = discountedPrice.add(item.getDiscountAmount());
		}
		finalPrice = finalPrice.setScale(2,RoundingMode.HALF_UP );
		discountedPrice = discountedPrice.setScale(2,RoundingMode.HALF_UP );

		pricingDto.setTotalPriceAfterDiscount(finalPrice);
		pricingDto.setTotalDiscount(discountedPrice);
		return pricingDto;
	}


	private BigDecimal calculateGrossPrice(Order order) {
		BigDecimal totalPrice = BigDecimal.ZERO;
		for (LineItem item : order.getLineItems()) {
			BigDecimal subTotal = calculateSubTotalBeforeDiscount(item.getProduct().getPrice(),item.getQuantity());
			totalPrice = totalPrice.add(subTotal);
		}
		totalPrice = totalPrice.setScale(2,RoundingMode.HALF_UP);
		return totalPrice;
	}


	private BigDecimal calculateSubTotalBeforeDiscount(BigDecimal price, long quantity) {		
		return (price.multiply(new BigDecimal(quantity)));
	}
	
	private  BigDecimal calculateSubTotalAfterDiscount(BigDecimal price, long quantity,BigDecimal discountAmount ) {
		return (price.multiply(new BigDecimal(quantity))).subtract(discountAmount);
	}


}