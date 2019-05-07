package com.example.spring.repository;

import com.example.spring.domain.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class InMemOrderRepository implements OrderRepository {

    private final Map<Integer, Order> orders = new HashMap<>();

    @Autowired
    public Integer insert(Order newOrder) {
        if (newOrder == null) {
            return null;
        }
        Integer generatedId = generateId();
        orders.put(generatedId, newOrder);
        return generatedId;
    }

    private Integer generateId() {
        return orders.size();
    }
}
