package com.example.spring.repository;

import com.example.spring.domain.DiscountCard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class InMemDiscountCardRepository implements DiscountCardRepository {

    @Autowired
    public void save(DiscountCard card) {
    }
}
