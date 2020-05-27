package com.shopping.calculator.app;

import com.shopping.calculator.exceptions.NoShoppingItemFoundException;
import com.shopping.calculator.exceptions.UnIdentifiedItemException;
import com.shopping.calculator.model.Order;
import com.shopping.calculator.model.PricingInfo;
import com.shopping.calculator.repository.DiscountRepository;
import com.shopping.calculator.repository.IDiscountRepository;
import com.shopping.calculator.repository.IProductRepository;
import com.shopping.calculator.repository.ProductRepository;
import com.shopping.calculator.service.IOrderService;
import com.shopping.calculator.service.IPricingService;
import com.shopping.calculator.service.OrderService;
import com.shopping.calculator.service.PricingService;

import java.util.Arrays;

public class ShoppingCalculatorMain {

    public static void main(String[] args) throws NoShoppingItemFoundException, UnIdentifiedItemException {

        try {

            //Initializing repositories- this will be not needed if proper persistence layer is used like Spring repositories.
            IProductRepository productRepository = new ProductRepository();
            IDiscountRepository discountRepository = new DiscountRepository();

            productRepository.initializeProductRepository();
            discountRepository.initializeDiscountRepository(productRepository);


            IOrderService orderService = new OrderService(productRepository);
            IPricingService pricingService = new PricingService(discountRepository);


            if (args == null || args.length == 0) {
                throw new NoShoppingItemFoundException("No items found to calculate pricing");
            }

            System.out.print("Items: ");
            Arrays.asList(args).forEach(x -> System.out.print(x + " "));
            System.out.println();

            Order order = orderService.createOrder(Arrays.asList(args));
            PricingInfo pricingInfo = pricingService.calculatePrice(order);

            printPricingDetails(pricingInfo);
            printDiscountDetails(order);

        } catch (NoShoppingItemFoundException exc) {
            System.out.println("No items found in the basket");
            throw exc;
        } catch (UnIdentifiedItemException exc) {
            System.out.println("At least one item not recognized in the basket");
            throw exc;
        }
    }

    private static void printPricingDetails(PricingInfo pricingInfo) {
        System.out.println("Price: GBP " + pricingInfo.getTotalPriceBeforeDiscount());
        System.out.println("Discount: GBP " + pricingInfo.getTotalDiscount());
        System.out.println("Total: GBP " + pricingInfo.getTotalPriceAfterDiscount());
    }

    private static void printDiscountDetails(Order order) {
        System.out.println();
        System.out.println("Discount Details: ");
        if (order.getDiscountDetails().isEmpty()) {
            System.out.println("(no offers available)");
        } else {
            order.getDiscountDetails().stream().forEach(System.out::println);
        }
    }
}
