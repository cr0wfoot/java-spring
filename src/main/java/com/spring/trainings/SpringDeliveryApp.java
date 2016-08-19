package com.spring.trainings;

import com.spring.trainings.service.OrderService;
import com.spring.trainings.domain.Customer;
import com.spring.trainings.domain.DiscountCard;
import com.spring.trainings.service.PizzaService;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SpringDeliveryApp {
    
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
        
        //appContext.publishEvent(new ApplicationEvent(appContext){});
        appContext.close();
        repContext.close();        
    }

    
}
