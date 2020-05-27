package com.shopping.calculator.model;

import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<LineItem> lineItems = new ArrayList<LineItem>();

    private List<String> discountDetails = new ArrayList<String>();

    public void addItem(Product product, long quantity) {
        lineItems.add(new LineItem(product, quantity));
    }

    public List<String> getDiscountDetails() {
        return discountDetails;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }


    public boolean productAlreadyExist(Product product) {
        return lineItems.stream().anyMatch(t -> t.getProduct().equals(product));
    }

    public void addDiscountDetails(String details) {
        discountDetails.add(details);
    }


}
