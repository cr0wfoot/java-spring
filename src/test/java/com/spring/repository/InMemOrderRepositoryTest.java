package com.spring.repository;

import com.spring.domain.Order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.*;

public class InMemOrderRepositoryTest {

    public static final double DOUBLE_DELTA = 0.001;

    private OrderRepository instance = new InMemOrderRepository();

    @Test
    public void shouldReturnNullIdIfInsertNull() {
        assertNull(instance.insert(null));
    }

    @Test
    public void shouldReturnIdIfInsertOrder() {
        int result = instance.insert(new Order());

        assertEquals(0, result);
    }

    @Test
    public void shouldReturnNullIfFindNull() {
        assertNull(instance.find(null));
    }

    @Test
    public void shouldReturnNullIdFindByInvalidId() {
        int invalidId = -1;

        assertNull(instance.find(invalidId));
    }

    @Test
    public void shouldReturnNullIfOrderNotExists() {
        Integer notExistsId = 10;

        assertNull(instance.find(notExistsId));
    }

    @Test
    public void shouldReturnOrderIfFind() {
        Order expectedOrder = new Order();
        instance.insert(expectedOrder);

        assertEquals(expectedOrder, instance.find(0));
    }

    @Test
    public void shouldUpdatePriceOfExistingOrderIfSave() {
        Order inputOrder = new Order();
        inputOrder.setCurrentPrice(10.0);
        instance.insert(inputOrder);

        double expectedPrice = 12;
        inputOrder.setId(0);
        inputOrder.setCurrentPrice(expectedPrice);
        instance.save(inputOrder);

        assertEquals(expectedPrice, instance.find(0).getCurrentPrice(), DOUBLE_DELTA);
    }

    @Test
    public void shouldNotThrowExceptionIfSaveNull() {
        instance.save(null);
    }
}
