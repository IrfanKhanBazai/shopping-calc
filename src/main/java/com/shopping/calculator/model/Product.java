package com.shopping.calculator.model;

import java.math.BigDecimal;
import java.util.Objects;
//
public class Product {
	
	private String name;
	private BigDecimal price;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	
	// making a simple implementation of equals based on name attribute
	@Override
	public boolean equals(Object obj) {
		return this.name.toUpperCase().equals( ((Product)obj).name.toUpperCase()); 
	}
	
	@Override
	public int hashCode(){
	    return Objects.hashCode(this.name);
	}


}
