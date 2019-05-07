package com.example.spring.ioccontainer;

public interface ApplicationContext {

    Object getBean(String beanName) throws Exception;
}
