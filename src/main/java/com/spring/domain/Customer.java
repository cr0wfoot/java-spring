package com.spring.domain;

public class Customer {
    
    private Integer id;
    private String name;
    private Address address;
    private DiscountCard discountCard;

    public Customer() {
    }
    
    public Customer(String name) {
        this.name = name;
    }
    
    public Customer(Integer id, String name, Address address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    
    public Customer(Integer id, String name, Address address, DiscountCard discountCard) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.discountCard = discountCard;
    }

    public DiscountCard getDiscountCard() {
        return discountCard;
    }

    public void setDiscountCard(DiscountCard discountCard) {
        this.discountCard = discountCard;
    }
    
    /**
     * Get the value of address
     *
     * @return the value of address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Set the value of address
     *
     * @param address new value of address
     */
    public void setAddress(Address address) {
        this.address = address;
    }
    
    /**
     * Get the value of name
     *
     * @return the value of name
     */
    public String getName() {
        return name;
    }

    /**
     * Set the value of name
     *
     * @param name new value of name
     */
    public void setName(String name) {
        this.name = name;
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
    
    public boolean isDiscountCardExists() {
        return discountCard != null;
    }
    
    @Override
    public String toString() {
        return "Customer: id=" + id + " addr=" + address + " card=" + discountCard + " name=" + name;
    }

}
