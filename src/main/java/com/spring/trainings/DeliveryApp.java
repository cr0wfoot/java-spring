package com.spring.trainings;

import com.spring.trainings.context.ioc.ApplicationContext;
import com.spring.trainings.context.ioc.JavaConfigApplicationContext;
import com.spring.trainings.domain.Customer;
import com.spring.trainings.repository.PizzaRepository;
import com.spring.trainings.service.OrderService;
import com.spring.trainings.service.PizzaService;
import com.spring.trainings.context.ioc.config.JavaConfig;
import com.spring.trainings.repository.OrderRepository;

public class DeliveryApp {
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
