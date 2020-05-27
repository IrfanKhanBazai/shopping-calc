package com.shopping.calculator.repository;

import com.shopping.calculator.model.Product;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {

    List<Product> initializeProductRepository();

    Optional<Product> findProductByName(String name);
}
