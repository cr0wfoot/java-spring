package com.example.spring.beanlifecyclebreak;

import com.example.spring.domain.Pizza;
import org.springframework.beans.factory.FactoryBean;

public class PizzaFactoryBean implements FactoryBean<Pizza> {

    private Integer id;
    private String name;
    private Pizza.PizzaType type;
    private Double price;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Pizza.PizzaType getType() {
        return type;
    }

    public void setType(Pizza.PizzaType type) {
        this.type = type;
    }

    public Pizza getObject() {
        return new Pizza(id, name, price, type);
    }

    public Class<?> getObjectType() {
        return Pizza.class;
    }

    public boolean isSingleton() {
        return true;
    }
}
