package com.spring.service;

import com.spring.domain.Pizza;
import com.spring.trainings.annotations.BenchMark;

public interface PizzaService {
    
    @BenchMark
    Pizza find(Integer id);
    
}
