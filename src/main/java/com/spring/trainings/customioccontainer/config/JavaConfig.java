package com.spring.trainings.customioccontainer.config;

import com.spring.repository.InMemOrderRepository;
import com.spring.repository.InMemPizzaRepository;
import com.spring.service.SimpleOrderService;
import com.spring.service.SimplePizzaService;

import java.util.HashMap;
import java.util.Map;

public class JavaConfig implements Config {
    
    private Map<String,Class<?>> container = new HashMap<String,Class<?>>();

    public JavaConfig() {
        container.put("pizzaRepository", InMemPizzaRepository.class);
        container.put("orderRepository", InMemOrderRepository.class);
        container.put("pizzaService", SimplePizzaService.class);
        container.put("orderService", SimpleOrderService.class);
    }

    public <T> Class<T> getImpl(String ifc) {
        return (Class<T>) container.get(ifc);
    }
}

