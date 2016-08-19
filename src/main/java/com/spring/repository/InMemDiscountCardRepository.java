package com.spring.repository;

import com.spring.domain.DiscountCard;
import org.springframework.stereotype.Repository;

@Repository
public class InMemDiscountCardRepository implements DiscountCardRepository {
    
    public void save(DiscountCard card) { }
}
