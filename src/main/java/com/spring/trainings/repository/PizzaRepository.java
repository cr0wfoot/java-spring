package com.spring.trainings.repository;

import com.spring.trainings.domain.Pizza;

public interface PizzaRepository {
    
    Pizza find(Integer id);
    
}
