package com.spring.trainings.customioccontainer.config;

import com.spring.repository.InMemOrderRepository;
import com.spring.repository.InMemPizzaRepository;
import com.spring.service.SimpleOrderService;
import com.spring.service.SimplePizzaService;

import java.util.HashMap;
import java.util.Map;

public class JavaConfig implements Config {
    
    private Map<String,Class<?>> ifc2Class = new HashMap<String,Class<?>>();

    public JavaConfig() {
        ifc2Class.put("pizzaRepository", InMemPizzaRepository.class);
        ifc2Class.put("orderRepository", InMemOrderRepository.class);
        ifc2Class.put("pizzaService", SimplePizzaService.class);
        ifc2Class.put("orderService", SimpleOrderService.class);
    }

    
    public <T> Class<T> getImpl(String ifc) {
        return (Class<T>) ifc2Class.get(ifc);
    }
    
}

