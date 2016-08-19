package com.spring.trainings.service;

import com.spring.trainings.context.annotations.BenchMark;
import com.spring.trainings.domain.*;
import com.spring.trainings.repository.OrderRepository;
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
    
    private static final int MAX_ACCEPTABLE_NUMBER_OF_PIZZAS = 10;
    private static final int MIN_ACCEPTABLE_NUMBER_OF_PIZZAS = 1;
    
    @Override
    public boolean changeStateOfOrder(Order order, OrderState newState) {
        if(order == null || !order.getState().isAvaliableStateToChange(newState))
            return false;
        order.setState(newState);
        saveOrder(order);
        return true;
    }
    
    @Override
    @BenchMark
    public boolean placeNewOrder(Customer customer, Integer ... pizzasId) {
        if(customer == null || pizzasId == null) return false;
        List<Pizza> pizzas = getPizzasByIds(pizzasId);
        
        checkRestrictionsForPizzaNumber(pizzas.size());
                
        Order newOrder = new Order();
        newOrder.setCustomer(customer);
        newOrder.setState(OrderState.NEW);
        newOrder.addPizzas(pizzas);
        newOrder.setCurrentPrice(getPriceWithDiscounts(newOrder));
        
        chargePointsOnDiscountCard(customer, newOrder.getCurrentPrice());
             
        insertOrder(newOrder); 
        return true;
    }
    
    @Override
    public boolean addPizzasToOrder(Order order, Integer ... pizzasId) {
        if(order == null || pizzasId == null) return false;
        List<Pizza> pizzas = getPizzasByIds(pizzasId);
        Double oldPrice = order.getCurrentPrice();
        
        checkRestrictionsForPizzaNumber(pizzas.size() + order.getPizzas().size());
        order.addPizzas(pizzas);
        order.setCurrentPrice(getPriceWithDiscounts(order));
        chargePointsOnDiscountCard(order.getCustomer(), order.getCurrentPrice() - oldPrice);
        
        saveOrder(order);
        return true;
    }
    
    @Lookup
    private Order createOrder() {
        return null;
    }
    
    private void chargePointsOnDiscountCard(Customer customer, Double pointsToCharge) {
        if (customer.isDiscountCardExists()) {
            discountCardService.chargePoints(customer.getDiscountCard(), pointsToCharge);
        }
    }
    
    private Double getPriceWithDiscounts(Order order) {
        DiscountManager discountManager = new DiscountManager();
        Double totalDiscount = discountManager.getTotalDiscount(order);
        return order.getTotalPrice() - totalDiscount;
    }
    
    private void checkRestrictionsForPizzaNumber(int number) {
        if(number < MIN_ACCEPTABLE_NUMBER_OF_PIZZAS)
            throw new IllegalArgumentException();
        if(number > MAX_ACCEPTABLE_NUMBER_OF_PIZZAS)
            throw new IllegalArgumentException();
    }

    private void insertOrder(Order newOrder) {
        orderRepository.insert(newOrder);
    }
    
    private void saveOrder(Order order) {
        orderRepository.save(order);
    }
    
    private List<Pizza> getPizzasByIds(Integer...pizzasId) {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        Pizza pizza;
        for(Integer id : pizzasId)
            if((pizza = pizzaService.find(id)) != null)
                pizzas.add(pizza);
        return pizzas;
    }
 
}
