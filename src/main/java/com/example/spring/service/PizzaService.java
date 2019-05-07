package com.example.spring.service;

import com.example.spring.annotations.BenchMark;
import com.example.spring.domain.Pizza;

public interface PizzaService {

    @BenchMark
    Pizza find(Integer id);
}
