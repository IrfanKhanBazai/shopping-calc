package com.shopping.calculator.discountengine;


import com.shopping.calculator.model.LineItem;
import com.shopping.calculator.model.Order;
import com.shopping.calculator.model.Product;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class PercentageDiscountTest {

    @Test
    public void testApplyDiscountSingleItem() {

        Product product = new Product();
        product.setName("Apple");
        product.setPrice(BigDecimal.valueOf(1));

        Order order = createOrderWithSingleItem(product, 1);

        Discounts percDiscount = new PercentageDiscount("10 % Apple Discount", "Apple 10% off:", BigDecimal.valueOf(0.10), LocalDate.now(), LocalDate.of(2019, 01, 30), product);

        percDiscount.applyDiscount(order);
        LineItem lineItem = order.getLineItems().get(0);
        BigDecimal expectedDiscount = BigDecimal.valueOf(.10).setScale(2, RoundingMode.HALF_EVEN);
        assertEquals(expectedDiscount, lineItem.getDiscountAmount().setScale(2, RoundingMode.HALF_EVEN));
        assertEquals(1, order.getDiscountDetails().size());
        assertEquals("Apple 10% off: -10p", order.getDiscountDetails().get(0));

    }

    @Test
    public void testApplyDiscountMultipleItems() {

        Product product1 = new Product();
        product1.setName("Apple");
        product1.setPrice(BigDecimal.valueOf(1));

        Product product2 = new Product();
        product2.setName("Orange");
        product2.setPrice(BigDecimal.valueOf(1));

        Order order = createOrderWithMultipleItems(product1, 1, product2, 1);

        Discounts percDiscount1 = new PercentageDiscount("20 % Apple Discount", "Apple 20% off:", BigDecimal.valueOf(0.20), LocalDate.now(), LocalDate.of(2025, 10, 30), product1);
        Discounts percDiscount2 = new PercentageDiscount("5 % Orange Discount", "Orange 5% off:", BigDecimal.valueOf(0.05), LocalDate.now(), LocalDate.of(2025, 10, 30), product2);


        percDiscount1.applyDiscount(order);
        percDiscount2.applyDiscount(order);

        LineItem lineItem1 = order.getLineItems().get(0);
        LineItem lineItem2 = order.getLineItems().get(1);
        assertEquals(BigDecimal.valueOf(.20).setScale(2, RoundingMode.HALF_EVEN), lineItem1.getDiscountAmount());
        assertEquals(BigDecimal.valueOf(.05).setScale(2, RoundingMode.HALF_EVEN), lineItem2.getDiscountAmount());
        assertEquals(2, order.getDiscountDetails().size());
        assertEquals("Apple 20% off: -20p", order.getDiscountDetails().get(0));
        assertEquals("Orange 5% off: -5p", order.getDiscountDetails().get(1));

    }

    private Order createOrderWithSingleItem(Product product, int quantity) {

        Order order = new Order();
        LineItem lineItem = new LineItem();

        lineItem.setProduct(product);
        lineItem.setQuantity(quantity);

        List<LineItem> lineItems = new ArrayList<>();

        lineItems.add(lineItem);
        order.setLineItems(lineItems);
        return order;

    }

    private Order createOrderWithMultipleItems(Product product1, int quantity1, Product product2, int quantity2) {

        Order order = new Order();

        LineItem lineItem1 = new LineItem();
        lineItem1.setProduct(product1);
        lineItem1.setQuantity(quantity1);

        LineItem lineItem2 = new LineItem();

        lineItem2.setProduct(product2);
        lineItem2.setQuantity(quantity2);

        List<LineItem> lineItems = new ArrayList<>();

        lineItems.add(lineItem1);
        lineItems.add(lineItem2);
        order.setLineItems(lineItems);
        return order;

    }

}
