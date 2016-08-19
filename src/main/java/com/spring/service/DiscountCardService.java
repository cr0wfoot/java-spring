package com.spring.service;

import com.spring.domain.DiscountCard;

public interface DiscountCardService {
    
    void withdrawPoints(DiscountCard card, Double points);
    
    void chargePoints(DiscountCard card, Double points);
    
}
