package com.spring;

import com.spring.trainings.customioccontainer.ApplicationContext;
import com.spring.repository.OrderRepository;
import com.spring.repository.PizzaRepository;
import com.spring.service.PizzaService;
import com.spring.trainings.customioccontainer.JavaConfigApplicationContext;
import com.spring.domain.Customer;
import com.spring.service.OrderService;
import com.spring.trainings.customioccontainer.config.JavaConfig;

public class AppRun {

    public static void main( String[] args ) throws Exception {
        Customer customer = new Customer();
        ApplicationContext context = new JavaConfigApplicationContext(new JavaConfig());
        OrderRepository orderRepository = (OrderRepository) context.getBean("orderRepository");
        PizzaRepository pizzaRepository = (PizzaRepository) context.getBean("pizzaRepository");
        PizzaService pizzaService = (PizzaService) context.getBean("pizzaService");
        OrderService orderService = (OrderService) context.getBean("orderService");
        System.out.println(pizzaRepository.find(1));
    }
}
