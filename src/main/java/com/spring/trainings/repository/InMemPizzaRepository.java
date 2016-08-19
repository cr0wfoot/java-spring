package com.spring.trainings.repository;

import com.spring.trainings.domain.Pizza;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Repository;

@Repository
public class InMemPizzaRepository implements PizzaRepository {

    private Map<Integer, Pizza> pizzas = new HashMap<Integer, Pizza>();

    public Map<Integer, Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(Map<Integer, Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    @PostConstruct
    public void init() {
        pizzas.put(4, new Pizza(4, "haski", 1.0, Pizza.PizzaType.VEGETARIAN));
        pizzas.put(5, new Pizza(5, "carbonara", 2.0, Pizza.PizzaType.SEA));
        pizzas.put(6, new Pizza(6, "paperoni", 3.0, Pizza.PizzaType.MEAT));
    }

    public Pizza find(Integer id) {
        if(id == null || id < 1)
            return null;
        return pizzas.get(id);
    }

}
