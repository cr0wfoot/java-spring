package com.spring.trainings.service;

import com.spring.trainings.context.annotations.BenchMark;
import com.spring.trainings.domain.Pizza;

public interface PizzaService {
    
    @BenchMark
    Pizza find(Integer id);
    
}
