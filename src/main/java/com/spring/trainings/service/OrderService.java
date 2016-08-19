package com.spring.trainings.service;

import com.spring.trainings.context.annotations.BenchMark;
import com.spring.trainings.domain.Customer;
import com.spring.trainings.domain.Order;
import com.spring.trainings.domain.OrderState;

public interface OrderService {

    boolean changeStateOfOrder(Order order, OrderState newState);
    
    @BenchMark
    boolean placeNewOrder(Customer customer, Integer... pizzasID);
    
    boolean addPizzasToOrder(Order order, Integer ... pizzasId);
    
}
