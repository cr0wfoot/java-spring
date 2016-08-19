package com.spring.trainings.context.ioc.config;

public interface Config {

     public <T> Class<T> getImpl(String ifc);
    
}

