package com.spring.service;

import com.spring.trainings.annotations.BenchMark;
import com.spring.domain.Order;
import com.spring.domain.Customer;
import com.spring.domain.OrderState;

public interface OrderService {

    boolean changeStateOfOrder(Order order, OrderState newState);
    
    @BenchMark
    boolean placeNewOrder(Customer customer, Integer... pizzasID);
    
    boolean addPizzasToOrder(Order order, Integer ... pizzasId);
    
}
