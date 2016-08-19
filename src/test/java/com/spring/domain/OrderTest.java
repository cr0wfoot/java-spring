package com.spring.domain;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.*;

public class OrderTest {

    private static final double DOUBLE_DELTA = 0.001;

    private Order order = new Order();

    @Test
    public void shouldReturnZeroPriceFotEmptyOrder() {
        assertEquals(0, order.getTotalPrice(), DOUBLE_DELTA);
    }

    @Test
    public void shouldReturnCorrectPriceForNotEmptyOrder() {
        order.addPizzas(createListOfPizzas(3));

        assertEquals(6, order.getTotalPrice(), DOUBLE_DELTA);
    }

    private List<Pizza> createListOfPizzas(int amountOfPizzas) {
        List<Pizza> pizzas = new ArrayList<>();
        for(int pizzaNumber = 1; pizzaNumber <= amountOfPizzas; pizzaNumber++)
            pizzas.add(new Pizza(pizzaNumber, String.valueOf(pizzaNumber), Double.valueOf(pizzaNumber), Pizza.PizzaType.MEAT));
        return pizzas;
    }
}
