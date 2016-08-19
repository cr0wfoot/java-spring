package com.spring.trainings.context.ioc;

import com.spring.trainings.context.ioc.config.Config;
import com.spring.trainings.context.annotations.PostCreate;
import com.spring.trainings.context.ioc.proxy.ProxyForBenchMark;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class JavaConfigApplicationContext implements ApplicationContext {
    
    private Config config;
    private Map<String, Object> beans = new HashMap<String, Object>();

    public JavaConfigApplicationContext(Config config) {
        this.config = config;
    }

    public Object getBean(String beanName) throws Exception {
        Class<?> type = config.getImpl(beanName);
        Object bean = beans.get(beanName);
        if(bean != null)
            return bean;
        
        BeanBuilder builder = new BeanBuilder(type);
        builder.construct();
        builder.callPostCreateMethod();
        builder.callInitMethod();
        builder.createProxy();
        bean = builder.build();

        beans.put(beanName,bean);
            
        return bean;
    }
    
    class BeanBuilder {
        
        Class<?> type;
        Object bean;
        
        public BeanBuilder(Class<?> type) {
            this.type = type;
        }
        
        public void construct() throws Exception {
            Constructor<?> constructor = type.getConstructors()[0];
            if(constructor.getParameterTypes().length == 0) 
                bean = type.newInstance();
            else {
                bean = newParamsInstance(constructor);
            }
        }
        
        private Object newParamsInstance(Constructor<?> constructor) throws Exception {
            Class<?>[] paramTypes = constructor.getParameterTypes();
            Object[] params = new Object[paramTypes.length];
            for (int i = 0; i < params.length; i++) {
                String beanName = paramTypes[i].getSimpleName();
                params[i] = getBean(Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1));
            }
            return constructor.newInstance(params);
        }

        public void callPostCreateMethod() throws Exception {
            Class<?> clazz = bean.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                if(m.isAnnotationPresent(PostCreate.class)) {
                    m.invoke(bean);
                }
            }
        }
        
        public void callInitMethod() throws Exception {
            Class<?> clazz = bean.getClass();
            Method[] methods = clazz.getDeclaredMethods();
            for (Method m : methods) {
                if(m.getName().equals("init") && !m.isAnnotationPresent(PostCreate.class)) {
                    m.invoke(bean);
                    break;
                }
            }
        }
        
        public void createProxy() {
            bean = new ProxyForBenchMark().createProxy(bean);
        }
        
        public Object build() {
            return bean;
        }
        
    }
    
}
