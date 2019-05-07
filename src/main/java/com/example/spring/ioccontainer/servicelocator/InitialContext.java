package com.example.spring.ioccontainer.servicelocator;

import com.example.spring.ioccontainer.config.Config;
import com.example.spring.ioccontainer.config.JavaConfig;

public class InitialContext {

    private volatile static InitialContext instance;
    private Config config = new JavaConfig();

    public static InitialContext getInstance() {
        InitialContext localInstance = instance;
        if (localInstance == null) {
            synchronized (InitialContext.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new InitialContext();
                }
            }
        }
        return localInstance;
    }

    public <T> T lookup(String ifc) {
        try {
            Class<T> clazz = config.getImpl(ifc);
            return clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
