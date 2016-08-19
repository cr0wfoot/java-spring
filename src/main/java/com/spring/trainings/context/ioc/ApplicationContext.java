package com.spring.trainings.context.ioc;

public interface ApplicationContext {

    public Object getBean(String beanName) throws Exception;    
    
}
