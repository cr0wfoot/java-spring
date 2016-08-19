package com.spring.trainings.service;

import com.spring.trainings.context.annotations.BenchMark;
import com.spring.trainings.repository.PizzaRepository;
import com.spring.trainings.domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimplePizzaService implements PizzaService {

    @Autowired
    private PizzaRepository pizzaRepository;

    @BenchMark
    public Pizza find(Integer id) {
        return pizzaRepository.find(id);
    }
    
}
