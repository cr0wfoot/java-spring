package com.example.spring;

import com.example.spring.ioccontainer.ApplicationContext;
import com.example.spring.ioccontainer.JavaConfigApplicationContext;
import com.example.spring.repository.OrderRepository;
import com.example.spring.repository.PizzaRepository;
import com.example.spring.service.OrderService;
import com.example.spring.service.PizzaService;
import com.example.spring.ioccontainer.config.JavaConfig;

public class CustomIoCRun {

    public static void main( String[] args ) throws Exception {
        ApplicationContext context = new JavaConfigApplicationContext(new JavaConfig());
        OrderRepository orderRepository = (OrderRepository) context.getBean("orderRepository");
        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository");
        PizzaService pizzaService = (PizzaService) context.getBean("pizzaService");
        OrderService orderService = (OrderService) context.getBean("orderService");
        System.out.println(pizzaRepository.find(1));
    }
}
