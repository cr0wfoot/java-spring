package com.spring.service;

import com.spring.domain.*;
import com.spring.repository.OrderRepository;
import com.spring.trainings.annotations.BenchMark;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Service;

@Service
public class SimpleOrderService implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private PizzaService pizzaService;

    @Autowired
    private DiscountCardService discountCardService;

    private static final int MAX_ACCEPTABLE_AMOUNT_OF_PIZZAS = 10;
    private static final int MIN_ACCEPTABLE_AMOUNT_OF_PIZZAS = 1;

    @Override
    public boolean changeStateOfOrder(Order order, OrderState newState) {
        if (order != null && order.getState().isAvailableStateToChange(newState)) {
            order.setState(newState);
            saveOrder(order);
            return true;
        }
        return false;
    }

    private void saveOrder(Order order) {
        orderRepository.save(order);
    }

    @BenchMark
    @Override
    public boolean placeNewOrder(Customer customer, Integer... pizzasId) {
        if (customer != null && pizzasId != null) {
            List<Pizza> pizzas = getPizzasByIds(pizzasId);
            checkIfAmountOfPizzasCorrect(pizzas.size());
            createOrder(customer, pizzas);
            return true;
        }
        return false;
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

    @Override
    public boolean addPizzasToOrder(Order order, Integer... pizzasId) {
        if (order != null && pizzasId != null) {
            List<Pizza> pizzas = getPizzasByIds(pizzasId);
            int totalAmountOfPizzas = pizzas.size() + order.getPizzas().size();
            checkIfAmountOfPizzasCorrect(totalAmountOfPizzas);
            handleOrder(order, pizzas);
            return true;
        }
        return false;
    }

    private void handleOrder(Order order, List<Pizza> pizzas) {
        double oldPrice = order.getCurrentPrice();
        order.addPizzas(pizzas);
        double currentPrice = calculatePriceWithDiscounts(order);
        order.setCurrentPrice(currentPrice);
        chargePointsOnDiscountCard(order.getCustomer(), currentPrice - oldPrice);
        saveOrder(order);
    }

    private List<Pizza> getPizzasByIds(Integer... pizzasId) {
        List<Pizza> pizzas = new ArrayList<Pizza>();
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

    @Lookup
    private Order createOrder() {
        return null;
    }
}
