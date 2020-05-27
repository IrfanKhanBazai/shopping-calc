package com.shopping.calculator.service;

import com.shopping.calculator.exceptions.UnIdentifiedItemException;
import com.shopping.calculator.model.Order;
import com.shopping.calculator.repository.DiscountRepositoryImpl;
import com.shopping.calculator.repository.DiscountRepository;
import com.shopping.calculator.repository.ProductRepository;
import com.shopping.calculator.repository.ProductRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;

public class OrderServiceTest {

    ProductRepository productRepository = new ProductRepositoryImpl();
    DiscountRepository discountRepository = new DiscountRepositoryImpl();

    @Before
    public void initializeRepositories() {
        productRepository.initializeProductRepository();
        discountRepository.initializeDiscountRepository(productRepository);
    }

    @Test
    public void testCreateOrder() throws UnIdentifiedItemException {

        OrderService orderService = new OrderServiceImpl(productRepository);

        Order order = orderService.createOrder(Arrays.asList("Soup", "soup", "Apple", "Bread"));

        assertEquals(order.getLineItems().size(), 3);
        assertEquals("SOUP", order.getLineItems().get(0).getProduct().getName());
        assertEquals(2, order.getLineItems().get(0).getQuantity());
        assertEquals("APPLE", order.getLineItems().get(1).getProduct().getName());
        assertEquals(1, order.getLineItems().get(1).getQuantity());
        assertEquals("BREAD", order.getLineItems().get(2).getProduct().getName());
        assertEquals(1, order.getLineItems().get(2).getQuantity());
    }

    @Test(expected = UnIdentifiedItemException.class)
    public void testCreateOrderThrowsException() throws UnIdentifiedItemException {

        OrderService orderService = new OrderServiceImpl(productRepository);
        orderService.createOrder(Arrays.asList("IncorrectItemName", "Soup", "Apple", "Bread"));

    }

}
