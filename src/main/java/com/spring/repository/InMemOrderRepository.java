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
        Integer generatedId = generateId();
        orders.put(generatedId, newOrder);
        return generatedId;
    }

    public Order find(Integer id) {
        if(id != null && id >= 0)
            return orders.get(id);
        return null;
    }

    public void save(Order order) {
        if(order != null)
            orders.put(order.getId(), order);
    }
    
    private Integer generateId() {
        return orders.size();
    }
}
