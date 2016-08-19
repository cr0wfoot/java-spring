package com.spring.trainings.customioccontainer;

public interface ApplicationContext {

    public Object getBean(String beanName) throws Exception;    
    
}
