package com.spring1.context.servicelocator;

import com.spring1.context.ioc.config.Config;
import com.spring1.context.ioc.config.JavaConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitialContext {
    
    private volatile static InitialContext instance;
    private Config config;
    
    private InitialContext() {
        this.config = new JavaConfig();
    }
    
    public static InitialContext getInstance() {
        InitialContext localInstance = instance;
        if (localInstance == null)
            synchronized (InitialContext.class) {
                localInstance = instance;
                if (localInstance == null)
                    instance = localInstance = new InitialContext();
            }
        return localInstance;
    }
    
    public <T> T lookup(String ifc) {
        try {
            Class<T> clazz = config.getImpl(ifc);
            T object = clazz.newInstance();
            return object;
        } catch (InstantiationException ex) {
            Logger.getLogger(InitialContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InitialContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
