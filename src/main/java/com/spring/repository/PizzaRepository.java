package com.spring.repository;

import com.spring.domain.Pizza;

public interface PizzaRepository {
    
    Pizza find(Integer id);
    
}
