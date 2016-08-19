package com.spring.trainings.domain;

import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.*;

public class OrderTest {
    
    private Order order;
    
    public OrderTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        order = new Order();
    }
    
    @After
    public void tearDown() {
        order = null;
    }

    /**
     * Fills order with pizzas, where each next pizza has params according to pizza number
     * @param numberOfPizzas number of pizzas to create
     */
    private List<Pizza> createListOfPizzas(int numberOfPizzas) {
        List<Pizza> pizzas = new ArrayList<Pizza>();
        for(int param = 1; param <= numberOfPizzas; param++)
            pizzas.add(new Pizza(param, String.valueOf(param), Double.valueOf(param), Pizza.PizzaType.MEAT));
        return pizzas;
    }

    /**
     * Test of getTotalPrice method, of class Order, get total price of empty order
     */
    @Test
    public void testGetTotalPrice_OfEmptyOrder() {
        Double expectedPrice = 0.0;
        
        Double resultPrice = order.getTotalPrice();
        
        assertEquals(expectedPrice, resultPrice);
    }
    
    /**
     * Test of getTotalPrice method, of class Order, get total price of order with pizzas
     */
    @Test
    public void testGetTotalPrice_OfOrderWithPizzas() {
        order.addPizzas(createListOfPizzas(3));
        Double expectedPrice = 6.0;
        
        Double resultPrice = order.getTotalPrice();
        
        assertEquals(expectedPrice, resultPrice);
    }
    
}
