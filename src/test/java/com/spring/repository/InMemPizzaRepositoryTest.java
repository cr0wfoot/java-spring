package com.spring.repository;

import org.junit.*;
import static org.junit.Assert.*;

public class InMemPizzaRepositoryTest {
    
    private PizzaRepository instance = new InMemPizzaRepository();

    @Test
    public void shouldReturnNullIfFindNull() {
        assertNull(instance.find(null));
    }

    @Test
    public void shouldReturnNullIfNotExists() {
        Integer notExistsId = 10;

        assertNull(instance.find(notExistsId));
    }

    @Test
    public void shouldReturnNullIfIdInvalid() {
        Integer invalidId = -1;

        assertNull(instance.find(invalidId));
    }
}
