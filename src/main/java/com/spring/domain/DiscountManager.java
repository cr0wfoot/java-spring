package com.spring.domain;

import java.util.Collections;
import java.util.List;

public class DiscountManager {

    private static final int PIZZAS_AMOUNT_FOR_DISCOUNTS = 4;
    private static final double DISCOUNT_FOR_MAX_PIZZA = 0.3;
    private static final double DISCOUNT_FOR_CARD = 0.1;
    private static final double MAX_DISCOUNT = 0.3;

    public Double getTotalDiscount(Order order) {
        Double totalDiscount = 0.0;

        totalDiscount += calculateDiscountForTheBiggestPizza(order);
        totalDiscount += calculateDiscountForDiscountCard(order);

        return totalDiscount;
    }

    private double calculateDiscountForTheBiggestPizza(Order order) {
        List<Pizza> pizzas = order.getPizzas();
        if (pizzas.size() > PIZZAS_AMOUNT_FOR_DISCOUNTS) {
            Collections.sort(pizzas, Pizza.compareByPrice());
            return getDiscountForAllBiggestPizzas(pizzas);
        }
        return 0;
    }

    private double getDiscountForAllBiggestPizzas(List<Pizza> pizzas) {
        double discount = 0;
        double maxPrice = pizzas.get(0).getPrice();
        for (Pizza p : pizzas)
            if (p.getPrice() == maxPrice)
                discount += maxPrice * DISCOUNT_FOR_MAX_PIZZA;
        return discount;
    }

    private double calculateDiscountForDiscountCard(Order order) {
        if (order.getCustomer().isDiscountCardExists())
            return Math.min(order.getTotalPrice() * MAX_DISCOUNT, getPointsOnUserCard(order) * DISCOUNT_FOR_CARD);
        return 0;
    }

    private double getPointsOnUserCard(Order order) {
        return order.getCustomer().getDiscountCard().getPoints();
    }
}
