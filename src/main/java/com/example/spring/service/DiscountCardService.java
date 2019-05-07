package com.example.spring.service;

import com.example.spring.domain.DiscountCard;

public interface DiscountCardService {

    void chargePoints(DiscountCard card, Double points);
}
