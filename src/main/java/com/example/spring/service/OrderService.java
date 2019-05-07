package com.example.spring.service;

import com.example.spring.domain.Customer;
import com.example.spring.annotations.BenchMark;

public interface OrderService {

    @BenchMark
    void placeNewOrder(Customer customer, Integer... pizzasID);
}
