package com.spring.trainings.beanlifecyclebreak;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition("customer");
        ConstructorArgumentValues argumentValues = beanDefinition.getConstructorArgumentValues();
        argumentValues.getArgumentValue(0, null).setValue("New Customer Name");
    }
}
