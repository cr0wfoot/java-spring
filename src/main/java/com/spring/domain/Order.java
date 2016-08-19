package com.spring.domain;

import com.spring.trainings.annotations.MyComponent;

import java.util.ArrayList;
import java.util.List;

@MyComponent
public class Order {
    
    private Integer id;
    private Customer customer;
    private Double currentPrice;
    private OrderState state;
    private List<Pizza> pizzas = new ArrayList<Pizza>();

    public Order() {
   
    }
    
    public Order(Customer customer, OrderState state, List<Pizza> pizzas) {
        this.customer = customer;
        this.state = state;
        this.pizzas = pizzas;
    }

    public Order(Integer id, Customer customer, OrderState state, List<Pizza> pizzas) {
        this.id = id;
        this.customer = customer;
        this.state = state;
        this.pizzas = pizzas;
    }
    
    public Double getTotalPrice() {
        Double totalPrice = 0.0;
        for(Pizza p : pizzas)
            totalPrice += p.getPrice();
        return totalPrice;
    }

    public Double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(Double price) {
        this.currentPrice = price;
    }
    
    public OrderState getState() {
        return state;
    }
    
    public void setState(OrderState stateToChange) {
        this.state = stateToChange;
    }
    
    /**
     * Get the value of pizzas
     *
     * @return the value of pizzas
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }
    
    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }
    
    public void addPizzas(List<Pizza> pizzas) {
        this.pizzas.addAll(pizzas);
    }

    /**
     * Get the value of customer
     *
     * @return the value of customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Set the value of customer
     *
     * @param customer new value of customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Get the value of id
     *
     * @return the value of id
     */
    public Integer getId() {
        return id;
    }

    /**
     * Set the value of id
     *
     * @param id new value of id
     */
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
        if (id != or.id)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        String pizzaz = "[";
        for(Pizza p : pizzas)
            pizzaz += "{" + p + "},";
        return "id=" + id + " customer=" + customer + " state" + state + " pizzas=" + pizzaz + "]";
    }
    
}
