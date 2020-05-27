package com.shopping.calculator.model;

import java.math.BigDecimal;

public class LineItem {

    private Product product;
    private long quantity;
    private BigDecimal discountAmount = BigDecimal.ZERO;

    public LineItem() {
    }

    public LineItem(Product product, long quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }
}
