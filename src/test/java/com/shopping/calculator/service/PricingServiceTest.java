package com.shopping.calculator.service;

import com.shopping.calculator.model.LineItem;
import com.shopping.calculator.model.Order;
import com.shopping.calculator.model.PricingInfo;
import com.shopping.calculator.model.Product;
import com.shopping.calculator.repository.DiscountRepositoryImpl;
import com.shopping.calculator.repository.DiscountRepository;
import com.shopping.calculator.repository.ProductRepository;
import com.shopping.calculator.repository.ProductRepositoryImpl;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PricingServiceTest {

    ProductRepository productRepository = new ProductRepositoryImpl();
    DiscountRepository discountRepository = new DiscountRepositoryImpl();

    @Before
    public void initializeRepositories() {
        productRepository.initializeProductRepository();
        discountRepository.initializeDiscountRepository(productRepository);
    }

    @Test
    public void testCalculateFinalPrice() {
        PricingService pricingService = new PricingServiceImpl(discountRepository);
        Product product = new Product();
        product.setName("Apple");
        product.setPrice(BigDecimal.valueOf(1));
        Order order = createOrderItem(product, 2);
        PricingInfo pricingInfo = pricingService.calculatePrice(order);

        // 10% discount on each Apple
        assertEquals(BigDecimal.valueOf(2.00).setScale(2, RoundingMode.HALF_EVEN), pricingInfo.getTotalPriceBeforeDiscount());
        assertEquals(BigDecimal.valueOf(1.80).setScale(2, RoundingMode.HALF_EVEN), pricingInfo.getTotalPriceAfterDiscount());
        assertEquals(BigDecimal.valueOf(0.20).setScale(2, RoundingMode.HALF_EVEN), pricingInfo.getTotalDiscount());

    }

    private Order createOrderItem(Product product, int quantity) {

        Order order = new Order();
        LineItem lineItem = new LineItem();

        lineItem.setProduct(product);
        lineItem.setQuantity(quantity);

        List<LineItem> lineItems = new ArrayList<>();

        lineItems.add(lineItem);
        order.setLineItems(lineItems);
        return order;

    }

}
