package com.example.spring.repository;

import com.example.spring.domain.Pizza;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static com.example.spring.domain.Pizza.PizzaType.*;

@Repository
public class InMemPizzaRepository implements PizzaRepository {

    private Map<Integer, Pizza> pizzas = new HashMap<>();

    @PostConstruct
    public void init() {
        pizzas.put(4, new Pizza(4, "VegaPizza", 1.0, VEGETARIAN));
        pizzas.put(5, new Pizza(5, "SeaPizza", 2.0, SEA));
        pizzas.put(6, new Pizza(6, "MeatPizza", 3.0, MEAT));
    }

    @Autowired
    public Pizza find(Integer id) {
        if (id != null && id >= 0) {
            return pizzas.get(id);
        } else {
            return null;
        }
    }
}
