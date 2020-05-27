package com.shopping.calculator.discountengine;

import com.shopping.calculator.model.Order;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

public abstract class Discounts {

    protected String name;
    protected String description;
    protected LocalDate validFrom;
    protected LocalDate expiryDate;
    //discount rates are in fractions e.g. .40 represents 40%
    protected BigDecimal discountRate;

    public Discounts(String name,
                     String description,
                     BigDecimal discountRate,
                     LocalDate validFrom,
                     LocalDate expiryDate) {
        this.name = name;
        this.description = description;
        this.discountRate = discountRate;
        this.validFrom = validFrom;
        this.expiryDate = expiryDate;
    }

    public abstract void applyDiscount(Order order);

    public boolean isDiscountApplicable() {
        return (validFrom.isBefore((LocalDate.now()))
                || validFrom.isEqual((LocalDate.now())))
                && (expiryDate.isEqual((LocalDate.now()))
                || expiryDate.isAfter((LocalDate.now())));
    }

    public String createDiscountDetails(BigDecimal discountPrice) {
        BigDecimal discountPerItem = discountRate.multiply(discountPrice);
        StringBuilder discountDetails = new StringBuilder();

        if (discountPerItem.compareTo(BigDecimal.ONE) == -1) {
            BigDecimal discountPerItemInPence = (discountPerItem.multiply(BigDecimal.valueOf(100))).setScale(0, RoundingMode.HALF_EVEN);
            discountDetails.append(" -");
            discountDetails.append(discountPerItemInPence);
            discountDetails.append("p");
        } else {
            discountDetails.append(" -");
            discountDetails.append("£");
            discountDetails.append(discountPerItem.setScale(2, RoundingMode.HALF_EVEN));
        }
        return this.description + discountDetails.toString();
    }


}
