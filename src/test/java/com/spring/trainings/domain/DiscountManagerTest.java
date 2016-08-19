package com.spring.trainings.domain;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.*;

public class DiscountManagerTest {
    
    private DiscountManager discountManager;
    private Customer customer;
    private Order order;
    
    public DiscountManagerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        discountManager = new DiscountManager();
        customer = new Customer();
        order = new Order(customer, OrderState.NEW, new ArrayList()); 
    }
    
    @After
    public void tearDown() {
        discountManager = null;
        customer = null;
        order = null; 
    }

    /**
     * Fills order with pizzas, where each next pizza has params according to pizza number
     * @param numberOfPizzas number of pizzas to create
     */
    private void fillOrderWithPizzas(int numberOfPizzas) {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        for(int param = 1; param <= numberOfPizzas; param++)
            pizzas.add(new Pizza(param, String.valueOf(param), Double.valueOf(param), Pizza.PizzaType.MEAT));
        order.addPizzas(pizzas);
        order.setCurrentPrice(order.getTotalPrice());
    }
    
    /**
     * Creates discount card for customer
     * @param points points on discount card
     */
    private DiscountCard createDiscountCard(Double points) {
        return new DiscountCard(1, customer, points);
    }

    /**
     * Test of getTotalDiscount method, of class DiscountManager, no discount should be
     */
    @Test
    public void testGetTotalDiscount_NoDiscounts() {
        Double expectedDiscount = 0.0;
        fillOrderWithPizzas(4);
        
        Double discount = discountManager.getTotalDiscount(order);
        
        assertEquals(expectedDiscount, discount);
    }
    
    /**
     * Test of getTotalDiscount method, of class DiscountManager, all discounts should be
     */
    @Test
    public void testGetTotalDiscount_AllDiscounts() {
        Double expectedDiscount = 3.5;
        fillOrderWithPizzas(5);
        customer.setDiscountCard(createDiscountCard(20.0));
        
        Double discount = discountManager.getTotalDiscount(order);
        
        assertEquals(expectedDiscount, discount);
    }
    
    /**
     * Test of getTotalDiscount method, of class DiscountManager, discount for discount card should be
     */
    @Test
    public void testGetTotalDiscount_DiscountForDiscountCardMoreThan30PercentFromTotalPrice() {
        Double expectedDiscount = 3.0;
        fillOrderWithPizzas(4);
        customer.setDiscountCard(createDiscountCard(50.0));
        
        Double discount = discountManager.getTotalDiscount(order);
        
        assertEquals(expectedDiscount, discount);
    }
    
    /**
     * Test of getTotalDiscount method, of class DiscountManager, discount for discount card should be
     */
    @Test
    public void testGetTotalDiscount_DiscountForDiscountCard() {
        Double expectedDiscount = 2.0;
        fillOrderWithPizzas(4);
        customer.setDiscountCard(createDiscountCard(20.0));
        
        Double discount = discountManager.getTotalDiscount(order);
        
        assertEquals(expectedDiscount, discount);
    }
    
    /**
     * Test of getTotalDiscount method, of class DiscountManager, discount for large order should be
     */
    @Test
    public void testGetTotalDiscount_DiscountForLargeOrder() {
        Double expectedDiscount = 1.5;
        fillOrderWithPizzas(5);
        
        Double discount = discountManager.getTotalDiscount(order);
        
        assertEquals(expectedDiscount, discount);
    }
    
}
