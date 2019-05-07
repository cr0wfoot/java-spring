package com.example.spring.domain;

public class Customer {

    private Integer id;
    private DiscountCard discountCard;

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isDiscountCardExists() {
        return discountCard != null;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", discountCard=" + discountCard +
                '}';
    }
}
