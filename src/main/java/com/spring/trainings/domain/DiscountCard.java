package com.spring.trainings.domain;

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

    /**
     * Get the value of points
     *
     * @return the value of points
     */
    public Double getPoints() {
        return points;
    }

    /**
     * Set the value of points
     *
     * @param points new value of points
     */
    public void setPoints(Double points) {
        this.points = points;
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

}
