package com.spring.domain;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.*;

public class DiscountManagerTest {

    private static final double DOUBLE_DELTA = 0.001;

    private DiscountManager discountManager = new DiscountManager();
    private Customer customer = new Customer();
    private Order order;
    
    @Before
    public void setUp() {
        order = new Order(customer, OrderState.NEW, new ArrayList()); 
    }

    @Test
    public void shouldReturnZeroIfNoDiscount() {
        fillOrderWithPizzas(4);

        assertEquals(0, discountManager.getTotalDiscount(order), DOUBLE_DELTA);
    }

    @Test
    public void shouldReturnCorrectTotalDiscountForAllDiscounts() {
        fillOrderWithPizzas(5);
        customer.setDiscountCard(createDiscountCardWithPoints(20));

        assertEquals(3.5, discountManager.getTotalDiscount(order), DOUBLE_DELTA);
    }

    @Test
    public void shouldReturnMaxDiscountValueWhenCalculatedDiscountMoreThanMax() {
        fillOrderWithPizzas(4);
        customer.setDiscountCard(createDiscountCardWithPoints(50));

        assertEquals(3, discountManager.getTotalDiscount(order), DOUBLE_DELTA);
    }

    @Test
    public void shouldReturnCorrectDiscountForCard() {
        fillOrderWithPizzas(4);
        customer.setDiscountCard(createDiscountCardWithPoints(20));

        assertEquals(2, discountManager.getTotalDiscount(order), DOUBLE_DELTA);
    }

    @Test
    public void shouldReturnCorrectDiscountForBiggestPizza() {
        fillOrderWithPizzas(5);

        assertEquals(1.5, discountManager.getTotalDiscount(order), DOUBLE_DELTA);
    }

    private void fillOrderWithPizzas(int numberOfPizzas) {
        List<Pizza> pizzas = new ArrayList<>();
        for(int pizzaNumber = 1; pizzaNumber <= numberOfPizzas; pizzaNumber++)
            pizzas.add(new Pizza(pizzaNumber, String.valueOf(pizzaNumber), Double.valueOf(pizzaNumber), Pizza.PizzaType.MEAT));
        order.addPizzas(pizzas);
        order.setCurrentPrice(order.getTotalPrice());
    }

    private DiscountCard createDiscountCardWithPoints(double points) {
        return new DiscountCard(1, customer, points);
    }
}
