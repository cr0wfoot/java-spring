package com.spring.service;

import com.spring.domain.Pizza;
import com.spring.repository.PizzaRepository;
import com.spring.trainings.annotations.BenchMark;
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
