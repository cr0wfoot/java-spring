package com.spring.service;

import com.spring.domain.*;
import com.spring.repository.OrderRepository;

import java.util.Arrays;

import static org.junit.Assert.*;

import org.junit.*;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;

import org.mockito.Mock;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleOrderServiceTest {

    private static final int PIZZA_ID = 1;
    private static final int PIZZA_NOT_EXISTS_ID = 10;
    private static final double PIZZA_PRICE = 10;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PizzaService pizzaService;

    @Mock
    private DiscountCardService discountCardService;

    @InjectMocks
    private SimpleOrderService instance;

    private Order order;
    private Pizza firstPizza;
    private Pizza secondPizza;
    private Customer customer;
    private DiscountCard customerCard;

    @Before
    public void setUp() {
        order = new Order();
        order.setState(OrderState.NEW);
        firstPizza = new Pizza(PIZZA_ID, "MeatPizza", PIZZA_PRICE, Pizza.PizzaType.MEAT);
        secondPizza = new Pizza(2, "SeaPizza", 8.0, Pizza.PizzaType.SEA);
        customer = new Customer();
        customerCard = new DiscountCard(1, customer, 0.0);
        customer.setDiscountCard(customerCard);
        order.setCustomer(customer);
    }

    @Test
    public void shouldReturnFalseWhenChangeOnNullState() {
        assertFalse(instance.changeStateOfOrder(order, null));
        verify(orderRepository, never()).save(order);
    }

    @Test
    public void shouldReturnFalseWhenChangeNullOrderState() {
        assertFalse(instance.changeStateOfOrder(null, OrderState.IN_PROGRESS));
        verify(orderRepository, never()).save(order);
    }

    @Test
    public void shouldReturnFalseWhenChangeOnUnacceptableState() {
        assertFalse(instance.changeStateOfOrder(order, OrderState.DONE));
        verify(orderRepository, never()).save(order);
    }

    @Test
    public void shouldChangeStateAndSaveOrderWhenChangeOnAcceptableState() {
        boolean orderStateChanged = instance.changeStateOfOrder(order, OrderState.IN_PROGRESS);

        assertTrue(orderStateChanged);
        assertEquals(OrderState.IN_PROGRESS, order.getState());
        verify(orderRepository).save(order);
    }

    @Test
    public void shouldInsertNewOrderAndChargePointsOnCard() {
        when(pizzaService.find(PIZZA_ID)).thenReturn(firstPizza);

        assertTrue(instance.placeNewOrder(customer, PIZZA_ID));
        verify(orderRepository).insert(any(Order.class));
        verify(discountCardService).chargePoints(customerCard, PIZZA_PRICE);
    }

    @Test
    public void shouldReturnFalseIfPlaceOrderWithCustomerNull() {
        assertFalse(instance.placeNewOrder(null, PIZZA_ID));
    }

    @Test
    public void shouldReturnFalseIfPlaceOrderWithPizzasIdNull() {
        assertFalse(instance.placeNewOrder(customer, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfPlaceOrderWithPizzasIdEmpty() {
        instance.placeNewOrder(customer, new Integer[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfPlaceOrderWithPizzasIdNotExist() {
        when(pizzaService.find(PIZZA_NOT_EXISTS_ID)).thenReturn(null);

        instance.placeNewOrder(customer, PIZZA_NOT_EXISTS_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionIfPlaceOrderWithMoreThanTenPizzas() {
        Integer[] pizzasId = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};

        when(pizzaService.find(any(Integer.class))).thenReturn(new Pizza());

        instance.placeNewOrder(customer, pizzasId);
    }

    @Test
    public void shouldAddPizzaToOrderWithDiscountForCard() {
        int secondPizzaId = secondPizza.getId();
        order.addPizzas(Arrays.asList(firstPizza));
        order.setCurrentPrice(PIZZA_PRICE);
        customerCard.setPoints(10.0);

        when(pizzaService.find(secondPizzaId)).thenReturn(secondPizza);

        assertTrue(instance.addPizzasToOrder(order, secondPizzaId));
        verify(orderRepository).save(order);
        verify(discountCardService).chargePoints(customerCard, secondPizza.getPrice() - 1);
    }

    @Test
    public void shouldNotAddPizzasToNullOrder() {
        assertFalse(instance.addPizzasToOrder(null, PIZZA_ID));
    }

    @Test
    public void shouldNotAddNullPizzasToOrder() {
        assertFalse(instance.addPizzasToOrder(order, null));
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddEmptyPizzasToOrder() {
        instance.addPizzasToOrder(order, new Integer[]{});
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenAddNotExistingPizzasToOrder() {
        when(pizzaService.find(PIZZA_NOT_EXISTS_ID)).thenReturn(null);

        instance.addPizzasToOrder(order, PIZZA_NOT_EXISTS_ID);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldThrowExceptionWhenTotalAmountOfPizzasInOrderBecomesMoreThanTen() {
        order.addPizzas(Arrays.asList(firstPizza, secondPizza));
        Integer[] newPizzasId = {1, 2, 3, 4, 5, 6, 7, 8, 9};

        when(pizzaService.find(any(Integer.class))).thenReturn(new Pizza());

        instance.addPizzasToOrder(order, newPizzasId);
    }
}
