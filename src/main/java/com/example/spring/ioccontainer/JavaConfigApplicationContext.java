package com.example.spring.ioccontainer;

import com.example.spring.annotations.PostCreate;
import com.example.spring.beanlifecyclebreak.ProxyForBenchMark;
import com.example.spring.ioccontainer.config.Config;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class JavaConfigApplicationContext implements ApplicationContext {

    private Config config;
    private Map<String, Object> beans = new HashMap<>();

    public JavaConfigApplicationContext(Config config) {
        this.config = config;
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        Class<?> type = config.getImpl(beanName);
        Object bean = beans.get(beanName);
        if (bean == null) {
            bean = buildBean(type);
            beans.put(beanName, bean);
        }
        return bean;
    }

    private Object buildBean(Class<?> type) throws Exception {
        BeanBuilder builder = new BeanBuilder(type);
        builder.construct();
        builder.callPostCreateMethod();
        builder.callInitMethod();
        builder.createProxy();
        return builder.build();
    }

    class BeanBuilder {

        Class<?> type;
        Object bean;

        public BeanBuilder(Class<?> type) {
            this.type = type;
        }

        public void construct() throws Exception {
            Constructor<?> constructor = type.getConstructors()[0];
            if (constructor.getParameterTypes().length == 0) {
                bean = type.newInstance();
            } else {
                bean = newParamsInstance(constructor);
            }
        }

        private Object newParamsInstance(Constructor<?> constructor) throws Exception {
            Class<?>[] paramTypes = constructor.getParameterTypes();
            Object[] params = new Object[paramTypes.length];
            for (int i = 0; i < params.length; i++) {
                String beanName = paramTypes[i].getSimpleName();
                params[i] = getBean(toLowerCaseFirstLetterOfBeanName(beanName));
            }
            return constructor.newInstance(params);
        }

        private String toLowerCaseFirstLetterOfBeanName(String beanName) {
            return Character.toLowerCase(beanName.charAt(0)) + beanName.substring(1);
        }

        public void callPostCreateMethod() throws Exception {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method m : methods) {
                if (m.isAnnotationPresent(PostCreate.class)) {
                    m.invoke(bean);
                }
            }
        }

        public void callInitMethod() throws Exception {
            Method[] methods = bean.getClass().getDeclaredMethods();
            for (Method m : methods) {
                if (isInitMethodAndNotPostCreate(m)) {
                    m.invoke(bean);
                    break;
                }
            }
        }

        private boolean isInitMethodAndNotPostCreate(Method m) {
            return m.getName().equals("init") && !m.isAnnotationPresent(PostCreate.class);
        }

        public void createProxy() {
            bean = new ProxyForBenchMark().createProxy(bean);
        }

        public Object build() {
            return bean;
        }
    }
}
