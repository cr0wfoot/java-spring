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
    
    @Mock
    private DiscountCardRepository repository;
    
    @InjectMocks
    private SimpleDiscountCardService instance;
    
    private DiscountCard discountCard;
    
    public SimpleDiscountCardServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        discountCard = new DiscountCard();
        discountCard.setPoints(10.0);
    }
    
    @After
    public void tearDown() {
        discountCard = null;
    }

    /**
     * Test of withdrawPoints method, of class SimpleDiscountCardService, withdraw points from discount card, check result points on card
     */
    @Test
    public void testWithdrawPoints_PointsValue() {
        Double expectedPointsOnCard = 6.0,
               inputPoints = 4.0;
        
        instance.withdrawPoints(discountCard, inputPoints);
        
        assertEquals(expectedPointsOnCard, discountCard.getPoints());
    }
    
    /**
     * Test of withdrawPoints method, of class SimpleDiscountCardService, withdraw points from discount card, test invocation of method DiscountCardRepository.save() 
     */
    @Test
    public void testWithdrawPoints_VerifySave() {
        Double inputPoints = 4.0;
        
        instance.withdrawPoints(discountCard, inputPoints);
        
        verify(repository).save(discountCard);
    }
    
    /**
     * Test of withdrawPoints method, of class SimpleDiscountCardService, not enough points on card to withdraw, check result points on card
     */
    @Test
    public void testWithdrawPoints_NotEnoughPointsOnCardToWithdraw_PointsValue() {
        Double expectedPointsOnCard = 0.0,
               inputPoints = 15.0;
        
        instance.withdrawPoints(discountCard, inputPoints);
        
        assertEquals(expectedPointsOnCard, discountCard.getPoints());
    }
    
    /**
     * Test of withdrawPoints method, of class SimpleDiscountCardService, not enough points on card to withdraw, test invocation of method DiscountCardRepository.save() 
     */
    @Test
    public void testWithdrawPoints_NotEnoughPointsOnCardToWithdraw_VerifySave() {
        Double inputPoints = 15.0;
        
        instance.withdrawPoints(discountCard, inputPoints);
        
        verify(repository).save(discountCard);
    }

    /**
     * Test of chargePoints method, of class SimpleDiscountCardService, charge points on discount card, check result points on card
     */
    @Test
    public void testChargePoints_PointsValue() {
        Double expectedPointsOnCard = 15.0,
               inputPoints = 5.0;
        
        instance.chargePoints(discountCard, inputPoints);
        
        assertEquals(expectedPointsOnCard, discountCard.getPoints());
    }
    
    /**
     * Test of chargePoints method, of class SimpleDiscountCardService, charge points on discount card, test invocation of method DiscountCardRepository.save()
     */
    @Test
    public void testChargePoints_VarifySave() {
        Double inputPoints = 5.0;
        
        instance.chargePoints(discountCard, inputPoints);
        
        verify(repository).save(discountCard);
    }
    
}
