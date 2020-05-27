package com.shopping.calculator.service;

import com.shopping.calculator.model.LineItem;
import com.shopping.calculator.model.Order;
import com.shopping.calculator.model.PricingInfo;
import com.shopping.calculator.model.Product;
import com.shopping.calculator.repository.DiscountRepository;
import com.shopping.calculator.repository.IDiscountRepository;
import com.shopping.calculator.repository.IProductRepository;
import com.shopping.calculator.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PricingServiceTest {

    IProductRepository productRepository = new ProductRepository();
    IDiscountRepository discountRepository = new DiscountRepository();

    @Before
    public void initializeRepositories() {
        productRepository.initializeProductRepository();
        discountRepository.initializeDiscountRepository(productRepository);
    }

    @Test
    public void testCalculateFinalPrice() {
        IPricingService pricingService = new PricingService(discountRepository);
        Product product = new Product();
        product.setName("Apple");
        product.setPrice(new BigDecimal(1));
        Order order = createOrderItem(product, 2);
        PricingInfo pricingInfo = pricingService.calculatePrice(order);

        // 10% discount on each Apple
        assertEquals(new BigDecimal(2.00).setScale(2, RoundingMode.HALF_EVEN), pricingInfo.getTotalPriceBeforeDiscount());
        assertEquals(new BigDecimal(1.80).setScale(2, RoundingMode.HALF_EVEN), pricingInfo.getTotalPriceAfterDiscount());
        assertEquals(new BigDecimal(0.20).setScale(2, RoundingMode.HALF_EVEN), pricingInfo.getTotalDiscount());

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
