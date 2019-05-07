package com.example.spring.service;

import com.example.spring.domain.DiscountCard;
import com.example.spring.repository.DiscountCardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleDiscountCardService implements DiscountCardService {

    @Autowired
    private DiscountCardRepository discountCardRepository;

    @Override
    public void chargePoints(DiscountCard card, Double points) {
        card.setPoints(card.getPoints() + points);
        saveChanges(card);
    }

    private void saveChanges(DiscountCard card) {
        discountCardRepository.save(card);
    }
}
