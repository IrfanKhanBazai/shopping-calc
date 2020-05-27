package com.shopping.calculator.service;

import com.shopping.calculator.exceptions.UnIdentifiedItemException;
import com.shopping.calculator.model.Order;

import java.util.List;

public interface OrderService {
    Order createOrder(List<String> items) throws UnIdentifiedItemException;
}
