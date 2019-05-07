package com.example.spring.repository;

import com.example.spring.domain.Order;

public interface OrderRepository {

    Integer insert(Order newOrder);
}
