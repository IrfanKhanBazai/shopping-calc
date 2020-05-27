package com.shopping.calculator.service;

import com.shopping.calculator.model.Order;
import com.shopping.calculator.model.PricingInfo;

public interface IPricingService {

    PricingInfo calculatePrice(Order order);

}
