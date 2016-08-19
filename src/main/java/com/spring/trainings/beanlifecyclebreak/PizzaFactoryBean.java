package com.spring.trainings.beanlifecyclebreak;

import com.spring.trainings.domain.Pizza;
import com.spring.trainings.domain.Pizza.PizzaType;
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

    public PizzaType getType() {
        return type;
    }

    public void setType(PizzaType type) {
        this.type = type;
    }
    
    public Pizza getObject() throws Exception {
        return new Pizza(id, name, price, type);
    }

    public Class<?> getObjectType() {
        return Pizza.class;
    }

    public boolean isSingleton() {
        return true;
    }
    
}
