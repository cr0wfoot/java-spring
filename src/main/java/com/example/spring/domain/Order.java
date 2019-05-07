package com.example.spring.domain;

import com.example.spring.annotations.MyComponent;

import java.util.ArrayList;
import java.util.List;

@MyComponent
public class Order {

    private Integer id;
    private Customer customer;
    private Double currentPrice;
    private OrderState state;
    private List<Pizza> pizzas = new ArrayList<>();

    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        for (Pizza p : pizzas) {
            totalPrice += p.getPrice();
        }
        return totalPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double price) {
        this.currentPrice = price;
    }

    public void setState(OrderState stateToChange) {
        this.state = stateToChange;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizzas(List<Pizza> pizzas) {
        this.pizzas.addAll(pizzas);
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null)
            return false;
        if (getClass() != o.getClass())
            return false;
        Order or = (Order) o;
        return id.equals(or.id);
    }

    @Override
    public String toString() {
        StringBuilder pizzasAsString = new StringBuilder("[");
        for (Pizza p : pizzas) {
            pizzasAsString.append("{").append(p).append("},");
        }
        return "id=" + id + " customer=" + customer + " state" + state + " pizzas=" + pizzasAsString + "]";
    }
}
