package com.example.spring.ioccontainer.config;

import com.example.spring.repository.InMemOrderRepository;
import com.example.spring.repository.InMemPizzaRepository;
import com.example.spring.service.SimpleOrderService;
import com.example.spring.service.SimplePizzaService;

import java.util.HashMap;
import java.util.Map;

public class JavaConfig implements Config {

    private Map<String, Class<?>> container = new HashMap<>();

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

