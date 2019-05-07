package com.example.spring.repository;

import com.example.spring.domain.Pizza;

public interface PizzaRepository {

    Pizza find(Integer id);
}
