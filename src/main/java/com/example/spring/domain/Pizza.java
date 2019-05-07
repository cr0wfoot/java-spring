package com.example.spring.domain;

import java.util.Comparator;

public class Pizza {

    private Integer id;
    private String name;
    private Double price;
    private PizzaType type;

    public enum PizzaType {
        VEGETARIAN,
        SEA,
        MEAT
    }

    public Pizza() {
    }

    public Pizza(Integer id, String name, Double price, PizzaType type) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.type = type;
    }

    public Double getPrice() {
        return price;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static Comparator<Pizza> compareByPrice() {
        return (o1, o2) -> Double.compare(o1.getPrice(), o2.getPrice()) * -1;
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
        Pizza p = (Pizza) o;
        return id.equals(p.id);
    }

    @Override
    public String toString() {
        return "id=" + id + " name=" + name + " price=" + price + " type=" + type.toString();
    }
}
