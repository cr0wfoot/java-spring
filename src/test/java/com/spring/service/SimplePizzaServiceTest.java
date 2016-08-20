package com.spring.service;

import com.spring.domain.Pizza;
import com.spring.repository.PizzaRepository;

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

    @Test
    public void shouldFindPizza() {
        int pizzaId = 1;
        Pizza pizza = new Pizza(pizzaId, "MeatPizza", 1.0, Pizza.PizzaType.MEAT);
        
        when(repository.find(pizzaId)).thenReturn(pizza);
        
        assertEquals(pizza, instance.find(pizzaId));
    }
}
