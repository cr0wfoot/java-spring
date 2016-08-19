package com.spring.trainings.service;

import com.spring.trainings.domain.Pizza;
import com.spring.trainings.repository.PizzaRepository;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimplePizzaServiceTest {
    
    @Mock
    private PizzaRepository repository;
    
    @InjectMocks
    private SimplePizzaService instance;
    
    public SimplePizzaServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of fing method, of class SimplePizzaService, find Pizza by id
     */
    @Test
    public void testFind() {
        Integer inputId = 1;
        Pizza fromRepository = new Pizza(1, "Pizza1", 1.0, Pizza.PizzaType.MEAT);
        
        when(repository.find(inputId)).thenReturn(fromRepository);
        
        Assert.assertEquals(fromRepository, instance.find(inputId));
    }
    
}
