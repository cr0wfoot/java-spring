package com.spring.trainings.customioccontainer.config;

public interface Config {

     public <T> Class<T> getImpl(String ifc);
    
}

