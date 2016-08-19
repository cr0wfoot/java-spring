package com.spring.repository;

import com.spring.domain.Order;

import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Repository;

@Repository
public class InMemOrderRepository implements OrderRepository {

    private final Map<Integer, Order> orders = new HashMap<Integer, Order>();
    
    public Integer insert(Order newOrder) {
        if(newOrder == null) return null;
        Integer id = tempGenerateId();
        orders.put(id, newOrder);
        return id;
    }

    public Order find(Integer id) {
        if(id == null || id < 0)
            return null;
        return orders.get(id);
    }

    public void save(Order order) {
        if(order == null) return;
        orders.put(order.getId(), order);
    }
    
    private Integer tempGenerateId() {
        return orders.size();
    }
    
}
