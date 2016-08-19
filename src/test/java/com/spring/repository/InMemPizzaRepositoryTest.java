package com.spring.repository;

import com.spring.domain.Pizza;
import org.junit.*;
import static org.junit.Assert.*;

public class InMemPizzaRepositoryTest {
    
    private PizzaRepository instance;
    
    public InMemPizzaRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        instance = new InMemPizzaRepository();
    }
    
    @After
    public void tearDown() {
        instance = null;
    }

    /**
     * Test of find method, of class InMemPizzaRepository, null instead of id
     */
    @Test
    public void testFind_Null() {
        Integer inputId = null;
        Pizza expected = null;
        
        Pizza result = instance.find(inputId);
        
        assertEquals(expected, result);
    }
    
    /**
     * Test of find method, of class InMemPizzaRepository, pizza is not exist
     */
    @Test
    public void testFind_NotExists() {
        Integer inputId = 10;
        Pizza expected = null;
        
        Pizza result = instance.find(inputId);
        
        assertEquals(expected, result);
    }
    
    /**
     * Test of find method, of class InMemPizzaRepository, id below 0 
     */
    @Test
    public void testFind_IdBelowZero() {
        Integer inputId = -1;
        Pizza expected = null;
        
        Pizza result = instance.find(inputId);
        
        assertEquals(expected, result);
    }
    
}
