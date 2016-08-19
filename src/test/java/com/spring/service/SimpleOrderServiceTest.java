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
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SimpleOrderServiceTest {
    
    @Mock
    private OrderRepository orderRepository;
    
    @Mock
    private PizzaService pizzaService;
    
    @Mock
    private DiscountCardService discountCardService;
    
    @InjectMocks
    private SimpleOrderService instance;
    
    private Order order;
    
    public SimpleOrderServiceTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() {
        order = new Order();
        order.setState(OrderState.NEW);
    }
    
    @After
    public void tearDown() {
        order = null;
    }

    /**
     * Test of changeStateOfOrder method, of class SimpleOrderService, change state of order, check result
     */
    @Test
    public void testChangeStateOfOrder_AvaliableStateToChange_CheckResult() {
        OrderState inputState = OrderState.IN_PROGRESS;

        boolean result = instance.changeStateOfOrder(order, inputState);
        
        assertTrue(result);
    }
    
    /**
     * Test of changeStateOfOrder method, of class SimpleOrderService, change state of order, check invocaton of method OrderRepository.save()
     */
    @Test
    public void testChangeStateOfOrder_AvaliableStateToChange_VerifySave() {
        OrderState inputState = OrderState.IN_PROGRESS;

        instance.changeStateOfOrder(order, inputState);
        
        verify(orderRepository).save(order);
    }
    
    /**
     * Test of changeStateOfOrder method, of class SimpleOrderService, change state of order, check changed state
     */
    @Test
    public void testChangeStateOfOrder_AvaliableStateToChange_ChangeState() {
        OrderState inputState = OrderState.IN_PROGRESS;

        instance.changeStateOfOrder(order, inputState);
        
        assertEquals(inputState, order.getState());
    }
    
    /**
     * Test of changeStateOfOrder method, of class SimpleOrderService, null instead of OrderState
     */
    @Test
    public void testChangeStateOfOrder_NullState() {
        OrderState inputState = null;

        boolean result = instance.changeStateOfOrder(order, inputState);
        
        assertFalse(result);
    }
    
    /**
     * Test of changeStateOfOrder method, of class SimpleOrderService, null instead of Order
     */
    @Test
    public void testChangeStateOfOrder_NullOrder() {
        OrderState inputState = OrderState.IN_PROGRESS;
        Order inputOrder = null;
        
        boolean result = instance.changeStateOfOrder(inputOrder, inputState);

        assertFalse(result);
    }
    
    /**
     * Test of changeStateOfOrder method, of class SimpleOrderService, unavaliable state to change
     */
    @Test
    public void testChangeStateOfOrder_UnavaliableStateToChange() {
        OrderState inputState = OrderState.DONE;
        
        boolean result = instance.changeStateOfOrder(order, inputState);

        assertFalse(result);
    }

    /**
     * Test of placeNewOrder method, of class SimpleOrderService, place new order, check result
     */
    @Test
    public void testPlaceNewOrder_ValidArguments_CheckResult() {
        Pizza inputPizza = new Pizza(1, "P1", 10.0, Pizza.PizzaType.MEAT);
        Customer inputCustomer = new Customer();
        DiscountCard inputDiscountCard = new DiscountCard(1, inputCustomer, 0.0);
        inputCustomer.setDiscountCard(inputDiscountCard);
        Integer[] inputPizzasId = {1};

        when(pizzaService.find(1)).thenReturn(inputPizza);
        boolean result = instance.placeNewOrder(inputCustomer, inputPizzasId);
        
        assertTrue(result);
    }
    
    /**
     * Test of placeNewOrder method, of class SimpleOrderService, place new order, check invocation of method OrderRepository.insert()
     */
    @Test
    public void testPlaceNewOrder_ValidArguments_VerifyInsert() {
        Pizza inputPizza = new Pizza(1, "P1", 10.0, Pizza.PizzaType.MEAT);
        Customer inputCustomer = new Customer();
        DiscountCard inputDiscountCard = new DiscountCard(1, inputCustomer, 0.0);
        inputCustomer.setDiscountCard(inputDiscountCard);
        Integer[] inputPizzasId = {1};

        when(pizzaService.find(1)).thenReturn(inputPizza);
        instance.placeNewOrder(inputCustomer, inputPizzasId);
        
        verify(orderRepository).insert(any(Order.class));
    }
    
    /**
     * Test of placeNewOrder method, of class SimpleOrderService, place new order, check invocation of method DiscountCardService.chargePoints()
     */
    @Test
    public void testPlaceNewOrder_ValidArguments_VerifyChargePoints() {
        Pizza inputPizza = new Pizza(1, "P1", 10.0, Pizza.PizzaType.MEAT);
        Customer inputCustomer = new Customer();
        DiscountCard inputDiscountCard = new DiscountCard(1, inputCustomer, 0.0);
        inputCustomer.setDiscountCard(inputDiscountCard);
        Integer[] inputPizzasId = {1};

        when(pizzaService.find(1)).thenReturn(inputPizza);
        boolean result = instance.placeNewOrder(inputCustomer, inputPizzasId);

        verify(discountCardService).chargePoints(inputDiscountCard, 10.0);
    }
    
    /**
     * Test of placeNewOrder method, of class SimpleOrderService, null instead of Customer
     */
    @Test
    public void testPlaceNewOrder_CustomerNull() {
        Customer inputCistomer = null;
        Integer[] inputPizzaId = {1};
        
        boolean result = instance.placeNewOrder(inputCistomer, inputPizzaId);
        
        assertFalse(result);
    }
    
    /**
     * Test of placeNewOrder method, of class SimpleOrderService, null instead of pizzas id
     */
    @Test
    public void testPlaceNewOrder_IdNull() {
        Customer inputCistomer = new Customer();
        Integer[] inputPizzaId = null;
        
        boolean result = instance.placeNewOrder(inputCistomer, inputPizzaId);
        
        assertFalse(result);
    }
    
    /**
     * Test of placeNewOrder method, of class SimpleOrderService, empty pizzas id
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlaceNewOrder_IdEmpty() {
        Customer inputCistomer = new Customer();
        Integer[] inputPizzaId = {};
        
        instance.placeNewOrder(inputCistomer, inputPizzaId);
    }
    
    /**
     * Test of placeNewOrder method, of class SimpleOrderService, pizzas id which is not exist
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlaceNewOrder_IdNotExist() {
        Customer inputCustomer = new Customer();
        Integer[] inputPizzaId = {1};
        
        when(pizzaService.find(1)).thenReturn(null);
        
        instance.placeNewOrder(inputCustomer, inputPizzaId);
    }
    
    /**
     * Test of placeNewOrder method, of class SimpleOrderService, restriction for pizzas, more than 10 pizzas - impossible
     */
    @Test(expected = IllegalArgumentException.class)
    public void testPlaceNewOrder_MoreThan10Pizzas() {
        Customer inputCustomer = new Customer();
        Integer[] inputPizzaId = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
        
        when(pizzaService.find(any(Integer.class))).thenReturn(new Pizza());
        
        instance.placeNewOrder(inputCustomer, inputPizzaId);
    }

    /**
     * Test of addPizzasToOrder method, of class SimpleOrderService, add pizzas to order, check result
     */
    @Test
    public void testAddPizzasToOrder_ValidArguments_CheckResult() {
        Pizza inputPizzaOld = new Pizza(1, "P1", 10.0, Pizza.PizzaType.MEAT),
              inputPizzaNew = new Pizza(2, "P2", 8.0, Pizza.PizzaType.MEAT);
        Customer inputCustomer = new Customer();
        DiscountCard inputDiscountCard = new DiscountCard(1, inputCustomer, 10.0);
        inputCustomer.setDiscountCard(inputDiscountCard);
        Integer[] inputPizzasId = {2}; 
        order.setCustomer(inputCustomer);
        order.addPizzas(Arrays.asList(inputPizzaOld));
        order.setCurrentPrice(10.0);
        
        when(pizzaService.find(2)).thenReturn(inputPizzaNew);
        boolean result = instance.addPizzasToOrder(order, inputPizzasId);
        
        assertTrue(result);
    }
    
    /**
     * Test of addPizzasToOrder method, of class SimpleOrderService, add pizzas to order, check invocation of method OrderRepository.save()
     */
    @Test
    public void testAddPizzasToOrder_ValidArguments_VerifySave() {
        Pizza inputPizzaOld = new Pizza(1, "P1", 10.0, Pizza.PizzaType.MEAT),
              inputPizzaNew = new Pizza(2, "P2", 8.0, Pizza.PizzaType.MEAT);
        Customer inputCustomer = new Customer();
        DiscountCard inputDiscountCard = new DiscountCard(1, inputCustomer, 10.0);
        inputCustomer.setDiscountCard(inputDiscountCard);
        Integer[] inputPizzasId = {2}; 
        order.setCustomer(inputCustomer);
        order.addPizzas(Arrays.asList(inputPizzaOld));
        order.setCurrentPrice(10.0);
        
        when(pizzaService.find(2)).thenReturn(inputPizzaNew);
        instance.addPizzasToOrder(order, inputPizzasId);
        
        order.addPizzas(Arrays.asList(inputPizzaNew));
        verify(orderRepository).save(order);
    }
    
    /**
     * Test of addPizzasToOrder method, of class SimpleOrderService, add pizzas to order, check invocation of method DiscountCardService.chargePoints()
     */
    @Test
    public void testAddPizzasToOrder_ValidArguments_VerifyChargePoints() {
        Pizza inputPizzaOld = new Pizza(1, "P1", 10.0, Pizza.PizzaType.MEAT),
              inputPizzaNew = new Pizza(2, "P2", 8.0, Pizza.PizzaType.MEAT);
        Customer inputCustomer = new Customer();
        DiscountCard inputDiscountCard = new DiscountCard(1, inputCustomer, 10.0);
        inputCustomer.setDiscountCard(inputDiscountCard);
        Integer[] inputPizzasId = {2}; 
        order.setCustomer(inputCustomer);
        order.addPizzas(Arrays.asList(inputPizzaOld));
        order.setCurrentPrice(10.0);
        
        when(pizzaService.find(2)).thenReturn(inputPizzaNew);
        instance.addPizzasToOrder(order, inputPizzasId);
        
        verify(discountCardService).chargePoints(inputDiscountCard, 7.0);
    }
    
    /**
     * Test of addPizzasToOrder method, of class SimpleOrderService, null instead of order
     */
    @Test
    public void testAddPizzasToOrder_OrderNull() {
        Order inputOrder = null;
        Integer[] inputPizzaId = {1};
        
        boolean result = instance.addPizzasToOrder(inputOrder, inputPizzaId);
        
        assertFalse(result);
    }
    
    /**
     * Test of addPizzasToOrder method, of class SimpleOrderService, null instead of id
     */
    @Test
    public void testAddPizzasToOrder_IdNull() {
        Order inputOrder = new Order();
        Integer[] inputPizzaId = null;
        
        boolean result = instance.addPizzasToOrder(inputOrder, inputPizzaId);
        
        assertFalse(result);
    }
    
    /**
     * Test of addPizzasToOrder method, of class SimpleOrderService, pizzas id is empty
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddPizzasToOrder_IdEmpty() {
        Order inputOrder = new Order();
        Integer[] inputPizzaId = {};
        
        instance.addPizzasToOrder(inputOrder, inputPizzaId);
    }
    
    /**
     * Test of addPizzasToOrder method, of class SimpleOrderService, pizzas not exist
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddPizzasToOrder_IdNotExist() {
        Order inputOrder = new Order();
        Integer[] inputPizzaId = {1};
        
        when(pizzaService.find(1)).thenReturn(null);
        
        instance.addPizzasToOrder(inputOrder, inputPizzaId);
    }
    
    /**
     * Test of addPizzasToOrder method, of class SimpleOrderService, applied number of pizzas as argument, but together with pizzas in order give more than 10
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddPizzasToOrder_MoreThan10PizzasTogetherWithCurrentPizzasInOrder() {
        Order inputOrder = new Order();
        inputOrder.addPizzas(Arrays.asList(new Pizza()));
        Integer[] inputPizzaId = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        
        when(pizzaService.find(anyInt())).thenReturn(new Pizza());
        
        instance.addPizzasToOrder(inputOrder, inputPizzaId);
    }
    
}
