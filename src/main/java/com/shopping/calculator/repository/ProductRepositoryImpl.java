package com.shopping.calculator.repository;

import com.shopping.calculator.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    List<Product> products = new ArrayList<Product>();

    @Override
    public List<Product> initializeProductRepository() {
        Product apple = new Product();
        apple.setName("APPLE");
        apple.setPrice(BigDecimal.valueOf(10));

        Product soup = new Product();
        soup.setName("SOUP");
        soup.setPrice(BigDecimal.valueOf(10));

        Product bread = new Product();
        bread.setName("BREAD");
        bread.setPrice(BigDecimal.valueOf(15));


        products.add(apple);
        products.add(soup);
        products.add(bread);

        return products;

    }

    @Override
    public Optional<Product> findProductByName(String name) {
        return products.stream().filter(t -> t.getName().equalsIgnoreCase((name))).findFirst();
    }


}
