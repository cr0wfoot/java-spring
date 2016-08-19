package com.spring.repository;

import com.spring.domain.Order;

import static org.junit.Assert.assertEquals;
import org.junit.*;

public class InMemOrderRepositoryTest {
    
    private OrderRepository instance;
    
    public InMemOrderRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        instance = new InMemOrderRepository();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of insert method, of class InMemOrderRepository, null instead of order
     */
    @Test
    public void testInsert_Null() {
        Order inputOrder = null;
        Integer expectedId = null;
        
        Integer result = instance.insert(inputOrder);
        
        assertEquals(expectedId, result);
    }
    
    /**
     * Test of insert method, of class InMemOrderRepository, put new order
     */
    @Test
    public void testInsert() {
        Order inputOrder = new Order();
        Integer expectedId = 0;
        
        Integer result = instance.insert(inputOrder);
        
        assertEquals(expectedId, result);
    }

    /**
     * Test of find method, of class InMemOrderRepository, null instead of id
     */
    @Test
    public void testFind_Null() {
        Integer inputId = null;
        Order expectedOrder = null;
        
        Order result = instance.find(inputId);
        
        assertEquals(expectedOrder, result);
    }
    
    /**
     * Test of find method, of class InMemOrderRepository, id below zero
     */
    @Test
    public void testFind_BelowZero() {
        Integer inputId = -1;
        Order expectedOrder = null;
        
        Order result = instance.find(inputId);
        
        assertEquals(expectedOrder, result);
    }
    
    /**
     * Test of find method, of class InMemOrderRepository, order is not exist
     */
    @Test
    public void testFind_NotExist() {
        Integer inputId = 10;
        Order expectedOrder = null;
        
        Order result = instance.find(inputId);
        
        assertEquals(expectedOrder, result);
    }
    
    /**
     * Test of find method, of class InMemOrderRepository, get order
     */
    @Test
    public void testFind() {
        Order inputOrder = new Order(),
              expectedOrder = inputOrder;
        Integer inputId = 0;
        instance.insert(inputOrder);
        
        Order result = instance.find(inputId);
        
        assertEquals(expectedOrder, result);
    }

    /**
     * Test of save method, of class InMemOrderRepository, update order
     */
    @Test
    public void testSave() {
        Order expectedOrder = new Order(),
              inputOrder = new Order();
        Double expectedPrice = 12.0;
        expectedOrder.setCurrentPrice(10.0);
        inputOrder.setId(0);
        inputOrder.setCurrentPrice(expectedPrice);
        instance.insert(expectedOrder);
        
        instance.save(inputOrder);
        
        Assert.assertEquals(expectedPrice, instance.find(0).getCurrentPrice());
    }
    
    /**
     * Test of save method, of class InMemOrderRepository, null instead of order
     */
    @Test
    public void testSave_Null() {
        Order inputOrder = null;

        instance.save(inputOrder);
    }
    
}
