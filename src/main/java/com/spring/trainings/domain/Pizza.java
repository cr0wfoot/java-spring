package com.spring.trainings.domain;

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
    
    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public PizzaType getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(PizzaType type) {
        this.type = type;
    }


    /**
     * Get the value of price
     *
     * @return the value of price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * Set the value of price
     *
     * @param price new value of price
     */
    public void setPrice(Double price) {
        this.price = price;
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
    
    public static Comparator<Pizza> compareByPrice() {
	return new Comparator<Pizza>() {
            @Override
            public int compare(Pizza o1, Pizza o2) {
		return Double.compare(o1.getPrice(), o2.getPrice()) * -1;
            }
	};
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
        if (id != p.id)
            return false;
        return true;
    }
    
    @Override
    public String toString() {
        return "id=" + id + " name=" + name + " price=" + price + " type=" + type.toString();
    }

}
