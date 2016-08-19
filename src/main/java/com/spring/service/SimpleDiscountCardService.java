package com.spring.service;

import com.spring.domain.DiscountCard;
import com.spring.repository.DiscountCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleDiscountCardService implements DiscountCardService {

    @Autowired
    private DiscountCardRepository discountCardRepository;

    public void withdrawPoints(DiscountCard card, Double points) {
        if(card.getPoints() < points)
             card.setPoints(0.0);
        else card.setPoints(card.getPoints() - points);
                
        saveChanges(card);
    }

    public void chargePoints(DiscountCard card, Double points) {
        card.setPoints(card.getPoints() + points);
        saveChanges(card);
    }
    
    private void saveChanges(DiscountCard card) {
        discountCardRepository.save(card);
    }
    
}
