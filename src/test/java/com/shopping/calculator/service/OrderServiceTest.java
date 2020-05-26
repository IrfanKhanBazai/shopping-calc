package com.shopping.calculator.service;

import org.junit.Before;
import org.junit.Test;
import com.shopping.calculator.exceptions.UnIdentifiedItemException;
import com.shopping.calculator.model.Order;
import com.shopping.calculator.repository.DiscountRepository;
import com.shopping.calculator.repository.IDiscountRepository;
import com.shopping.calculator.repository.IProductRepository;
import com.shopping.calculator.repository.ProductRepository;
import org.mockito.Mock;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class OrderServiceTest {

	IProductRepository productRepository = new ProductRepository();
	IDiscountRepository discountRepository  = new DiscountRepository();
	
	@Before
	public void initializeRepositories() {

		//when(productRepository.initializeProductRepository()).thenReturn()
		productRepository.initializeProductRepository();
		discountRepository.initializeDiscountRepository(productRepository);
	}
	
	@Test
	public void testCreateOrder() throws UnIdentifiedItemException {
		
		IOrderService orderService = new OrderService(productRepository);
		
		Order order = orderService.createOrder(Arrays.asList("Soup","soup","Apples", "Bread"));
		
		assertEquals(order.getLineItems().size(),3);
		assertEquals("SOUP",order.getLineItems().get(0).getProduct().getName());
		assertEquals(2, order.getLineItems().get(0).getQuantity());
		assertEquals("APPLES", order.getLineItems().get(1).getProduct().getName());
		assertEquals(1, order.getLineItems().get(1).getQuantity());
		assertEquals("BREAD", order.getLineItems().get(2).getProduct().getName());
		assertEquals(1, order.getLineItems().get(2).getQuantity());
	}
	
	@Test(expected = UnIdentifiedItemException.class) 
	public void testCreateOrderThrowsException() throws UnIdentifiedItemException {

		IOrderService orderService = new OrderService(productRepository);
		orderService.createOrder(Arrays.asList("IncorrectItemName","Soup","Apples", "Bread"));

	}

}
