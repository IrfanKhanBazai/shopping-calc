package com.shopping.calculator.discountengine;

import com.shopping.calculator.model.LineItem;
import com.shopping.calculator.model.Order;
import com.shopping.calculator.model.Product;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public class PercentageDiscount extends Discounts {

    private Product eligibleProduct;


    public PercentageDiscount(String name,
                              String description,
                              BigDecimal discountRate,
                              LocalDate validFrom,
                              LocalDate expiryDate,
                              Product prod) {
        super(name, description, discountRate, validFrom, expiryDate);
        this.eligibleProduct = prod;
    }

    public void applyDiscount(Order order) {
        order.getLineItems().stream().filter(item -> item.getProduct().equals(eligibleProduct))
                .forEach(item -> calculateDiscount(item, order));

    }

    private void calculateDiscount(LineItem lineItem, Order order) {

        lineItem.setDiscountAmount(calculateDiscountAmount(lineItem.getProduct().getPrice(), lineItem.getQuantity()));

        // Add discount details for each product (e.g. Apple) in a given Order
        for (int i = 0; i < lineItem.getQuantity(); i++) {
            order.addDiscountDetails(createDiscountDetails(lineItem.getProduct().getPrice()));
        }
    }

    private BigDecimal calculateDiscountAmount(BigDecimal price, long quantity) {
        return discountRate.multiply(new BigDecimal(quantity)).multiply(price).setScale(2, RoundingMode.HALF_EVEN);
    }

}
