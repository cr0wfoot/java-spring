package com.example.spring.ioccontainer.config;

public interface Config {

    <T> Class<T> getImpl(String ifc);
}

