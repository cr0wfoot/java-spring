package com.spring.trainings.domain;

import java.util.Collections;
import java.util.List;

public class DiscountManager {
    
    private static final int PIZZAS_COUNT_FOR_DISCOUNTS = 4;
    private static final double DISCOUNT_FOR_MAX_PIZZA_COEFF = 0.3;
    private static final double DISCOUNT_FOR_CARD_POINTS_COEFF = 0.1;
    private static final double MAX_AVALIABLE_POINTS_DISCOUNT_COEFF = 0.3;
        
    public Double getTotalDiscount(Order order) {
        Double totalDiscount = 0.0;
        
        totalDiscount += calculateDiscountForTheBiggestPizza(order);
        totalDiscount += calculateDiscountForDiscountCard(order);
        
        return totalDiscount;
    }
    
    private Double calculateDiscountForTheBiggestPizza(Order order) {
        Double discount = 0.0;
        List<Pizza> pizzas = order.getPizzas();
        
        if(pizzas.size() <= PIZZAS_COUNT_FOR_DISCOUNTS)
            return 0.0;
        
        Collections.sort(pizzas, Pizza.compareByPrice());
        Double maxPrice = pizzas.get(0).getPrice();
        for(Pizza p : pizzas)
            if(p.getPrice() == maxPrice)
                 discount += maxPrice * DISCOUNT_FOR_MAX_PIZZA_COEFF;
        
        return discount;
    }
    
    private Double calculateDiscountForDiscountCard(Order order) {
        Double discount = 0.0;
        Double discountForPoints;
        
        if(order.getCustomer().isDiscountCardExists()) {
            discountForPoints = order.getCustomer().getDiscountCard().getPoints() * DISCOUNT_FOR_CARD_POINTS_COEFF;
            if(discountForPoints > order.getTotalPrice() * MAX_AVALIABLE_POINTS_DISCOUNT_COEFF)
                 discount = order.getTotalPrice() * MAX_AVALIABLE_POINTS_DISCOUNT_COEFF;
            else discount = discountForPoints;
        }
        
        return discount;
    }
        
}
