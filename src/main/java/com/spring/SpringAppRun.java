package com.spring;

import com.spring.domain.DiscountCard;
import com.spring.service.PizzaService;
import com.spring.service.OrderService;
import com.spring.domain.Customer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringAppRun {

    public static void main( String[] args ) {
        
        ConfigurableApplicationContext repContext = new ClassPathXmlApplicationContext("repositoryConfig.xml");
        ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext(new String[] {"appConfig.xml"}, repContext);
        System.out.println("\n======================================\n");
        
        //test find
        PizzaService pizzaService = appContext.getBean("simplePizzaService", PizzaService.class);
        System.out.println(pizzaService.find(2));
        
        //test placeNewOrder
        OrderService orderService = appContext.getBean("simpleOrderService", OrderService.class);
        Customer customer = new Customer();
        customer.setDiscountCard(new DiscountCard(1, customer, 15.0));
        orderService.placeNewOrder(customer, 1);
        System.out.println(customer.getDiscountCard().getPoints());
        System.out.println("\n======================================\n");
        
        appContext.close();
        repContext.close();        
    }
}
