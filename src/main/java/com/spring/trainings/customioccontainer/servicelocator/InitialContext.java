package com.spring.trainings.customioccontainer.servicelocator;

import com.spring.trainings.customioccontainer.config.Config;
import com.spring.trainings.customioccontainer.config.JavaConfig;
import java.util.logging.Level;
import java.util.logging.Logger;

public class InitialContext {
    
    private volatile static InitialContext instance;
    private Config config = new JavaConfig();

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
            return clazz.newInstance();
        } catch (InstantiationException ex) {
            Logger.getLogger(InitialContext.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(InitialContext.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
