package com.example.spring.service;

import com.example.spring.annotations.BenchMark;
import com.example.spring.domain.*;
import com.example.spring.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleOrderService implements OrderService {

    private static final int MAX_ACCEPTABLE_AMOUNT_OF_PIZZAS = 10;
    private static final int MIN_ACCEPTABLE_AMOUNT_OF_PIZZAS = 1;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private DiscountCardService discountCardService;

    @BenchMark
    @Override
    public void placeNewOrder(Customer customer, Integer... pizzasId) {
        if (customer != null && pizzasId != null) {
            List<Pizza> pizzas = getPizzasByIds(pizzasId);
            checkIfAmountOfPizzasCorrect(pizzas.size());
            createOrder(customer, pizzas);
        }
    }

    private void createOrder(Customer customer, List<Pizza> pizzas) {
        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setState(OrderState.NEW);
        newOrder.addPizzas(pizzas);
        newOrder.setCurrentPrice(calculatePriceWithDiscounts(newOrder));
        chargePointsOnDiscountCard(customer, newOrder.getCurrentPrice());
        insertOrder(newOrder);
    }

    private double calculatePriceWithDiscounts(Order order) {
        double totalDiscount = new DiscountManager().getTotalDiscount(order);
        return order.getTotalPrice() - totalDiscount;
    }

    private void chargePointsOnDiscountCard(Customer customer, Double pointsToCharge) {
        if (customer.isDiscountCardExists())
            discountCardService.chargePoints(customer.getDiscountCard(), pointsToCharge);
    }

    private void insertOrder(Order newOrder) {
        orderRepository.insert(newOrder);
    }

    private List<Pizza> getPizzasByIds(Integer... pizzasId) {
        List<Pizza> pizzas = new ArrayList<>();
        for (Integer id : pizzasId)
            addPizzaIfFound(pizzas, id);
        return pizzas;
    }

    private void addPizzaIfFound(List<Pizza> pizzas, Integer id) {
        Pizza pizza = pizzaService.find(id);
        if (pizza != null)
            pizzas.add(pizza);
    }

    private void checkIfAmountOfPizzasCorrect(int pizzasAmount) {
        if (pizzasAmount < MIN_ACCEPTABLE_AMOUNT_OF_PIZZAS || pizzasAmount > MAX_ACCEPTABLE_AMOUNT_OF_PIZZAS)
            throw new IllegalArgumentException();
    }
}
