package com.spring.trainings.service;

import com.spring.trainings.domain.DiscountCard;

public interface DiscountCardService {
    
    void withdrawPoints(DiscountCard card, Double points);
    
    void chargePoints(DiscountCard card, Double points);
    
}
