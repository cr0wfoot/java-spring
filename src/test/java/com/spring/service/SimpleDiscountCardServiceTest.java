package com.spring.service;

import com.spring.domain.DiscountCard;
import com.spring.repository.DiscountCardRepository;

import static org.junit.Assert.assertEquals;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.Mockito.verify;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleDiscountCardServiceTest {

    private static final double DEFAULT_POINTS_ON_CARD = 10;
    private static final double NEW_POINTS = 4;
    private static final double DOUBLE_DELTA = 0.001;

    @Mock
    private DiscountCardRepository repository;

    @InjectMocks
    private SimpleDiscountCardService instance;

    private DiscountCard discountCard;

    @Before
    public void setUp() {
        discountCard = new DiscountCard();
        discountCard.setPoints(DEFAULT_POINTS_ON_CARD);
    }

    @Test
    public void shouldWithdrawPointsAndSaveCard() {
        instance.withdrawPoints(discountCard, NEW_POINTS);

        assertPointsOnCard(DEFAULT_POINTS_ON_CARD - NEW_POINTS);
        verify(repository).save(discountCard);
    }

    @Test
    public void shouldSetZeroPointsIfWithdrawMoreThanExistsAndSaveCard() {
        instance.withdrawPoints(discountCard, NEW_POINTS + DEFAULT_POINTS_ON_CARD);

        assertPointsOnCard(0);
        verify(repository).save(discountCard);
    }

    @Test
    public void shouldChargePointsAndSaveCard() {
        instance.chargePoints(discountCard, NEW_POINTS);

        assertPointsOnCard(DEFAULT_POINTS_ON_CARD + NEW_POINTS);
        verify(repository).save(discountCard);
    }

    private void assertPointsOnCard(double expectedPoints) {
        assertEquals(expectedPoints, discountCard.getPoints(), DOUBLE_DELTA);
    }
}
