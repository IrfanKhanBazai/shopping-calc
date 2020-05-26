package com.shopping.calculator.service;

import com.shopping.calculator.model.Order;
import com.shopping.calculator.model.PricingInfo;

import java.math.BigDecimal;

public interface IPricingService {

	PricingInfo calculatePrice (Order order);

}
