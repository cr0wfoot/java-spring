package com.spring.trainings.repository;

import com.spring.trainings.domain.Order;

public interface OrderRepository {
    
    Integer insert(Order newOrder);
    
    Order find(Integer id);
    
    void save(Order order);
    
}
