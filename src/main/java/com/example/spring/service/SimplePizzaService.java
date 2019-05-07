package com.example.spring.service;

import com.example.spring.annotations.BenchMark;
import com.example.spring.domain.Pizza;
import com.example.spring.repository.PizzaRepository;
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
