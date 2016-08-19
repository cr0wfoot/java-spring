package com.spring.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum OrderState {

    DONE(),

    CANCELED(),

    IN_PROGRESS(CANCELED, DONE),

    NEW(CANCELED, IN_PROGRESS);

    private List<OrderState> permissions;

    OrderState(OrderState... states) {
        permissions = new ArrayList<>(Arrays.asList(states));
    }

    public boolean isAvailableStateToChange(OrderState stateToChange) {
        if (stateToChange == null) return false;
        return permissions.contains(stateToChange);
    }
}
