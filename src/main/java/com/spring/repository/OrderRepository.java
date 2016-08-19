package com.spring.repository;

import com.spring.domain.Order;

public interface OrderRepository {
    
    Integer insert(Order newOrder);
    
    Order find(Integer id);
    
    void save(Order order);
    
}
