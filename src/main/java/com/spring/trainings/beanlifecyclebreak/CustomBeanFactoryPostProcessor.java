package com.spring.trainings.beanlifecyclebreak;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.ConstructorArgumentValues;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    public void postProcessBeanFactory(ConfigurableListableBeanFactory clbf) throws BeansException {
        BeanDefinition bd = clbf.getBeanDefinition("customer");
        ConstructorArgumentValues v = bd.getConstructorArgumentValues();
        v.getArgumentValue(0, null).setValue("Name2");
    }
    
}
