package com.spring.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum OrderState {
        
        DONE       (),
        
        CANCELED   (),
        
        IN_PROGRSS ( CANCELED , DONE       ),
        
        NEW        ( CANCELED , IN_PROGRSS );
        
        private List<OrderState> permissions;
        
        private OrderState(OrderState...states) {
            permissions = new ArrayList<OrderState>(Arrays.asList(states));
        }
        
        public List<OrderState> getAvaliableStatesToChange() {
            return permissions;
        }
        
        public boolean isAvaliableStateToChange(OrderState stateToChange) {
            if(stateToChange == null) return false;
            return permissions.contains(stateToChange);
        }
        
    }
