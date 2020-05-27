package com.shopping.calculator.service;

import com.shopping.calculator.exceptions.UnIdentifiedItemException;
import com.shopping.calculator.model.Order;
import com.shopping.calculator.model.Product;
import com.shopping.calculator.repository.ProductRepository;

import java.util.List;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private ProductRepository productRepository;

    public OrderServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Order createOrder(List<String> items) throws UnIdentifiedItemException {

        Order order = new Order();

        for (String item : items) {

            Optional<Product> product = productRepository.findProductByName(item.toUpperCase());
            if (!product.isPresent()) {
                throw new UnIdentifiedItemException("Item not recognized");
            }

            //to avoid adding same product twice
            if (order.productAlreadyExist(product.get())) {
                continue;
            }

            // count the quantity of a particular product in a given Order
            long productCount = items.stream().filter(t -> t.equalsIgnoreCase(item)).count();

            order.addItem(product.get(), productCount);

        }
        return order;
    }
}
