package com.spring;

public class InitBinderSample {
    /*
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Pizza.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String id) {
                Pizza pizza;
                if(id == null || id.isEmpty()) {
                    pizza = new Pizza();
                } else {
                    pizza = pizzaService.find(Long.valueOf(id));
                }
                setValue(pizza);
            }
        });
    }*/
}
