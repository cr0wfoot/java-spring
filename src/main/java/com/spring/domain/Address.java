package com.spring.domain;

public class Address {

    private String country;
    private String city;
    private String street;
    private String appartment;

    public Address() {
    }
    
    public Address(String city) {
        this.city = city;
    }

    /**
     * Get the value of appartment
     *
     * @return the value of appartment
     */
    public String getAppartment() {
        return appartment;
    }

    /**
     * Set the value of appartment
     *
     * @param appartment new value of appartment
     */
    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    /**
     * Get the value of street
     *
     * @return the value of street
     */
    public String getStreet() {
        return street;
    }

    /**
     * Set the value of street
     *
     * @param street new value of street
     */
    public void setStreet(String street) {
        this.street = street;
    }

    /**
     * Get the value of city
     *
     * @return the value of city
     */
    public String getCity() {
        return city;
    }

    /**
     * Set the value of city
     *
     * @param city new value of city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Get the value of country
     *
     * @return the value of country
     */
    public String getCountry() {
        return country;
    }

    /**
     * Set the value of country
     *
     * @param country new value of country
     */
    public void setCountry(String country) {
        this.country = country;
    }
    
    @Override
    public String toString() {
        return "Address";
    }

}
