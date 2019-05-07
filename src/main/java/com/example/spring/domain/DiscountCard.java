package com.example.spring.domain;

public class DiscountCard {

    private Customer customer;
    private Integer id;
    private Double points;

    public DiscountCard() {
    }

    public DiscountCard(Integer id, Customer customer, Double points) {
        this.customer = customer;
        this.id = id;
        this.points = points;
    }

    public Double getPoints() {
        return points;
    }

    public void setPoints(Double points) {
        this.points = points;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
