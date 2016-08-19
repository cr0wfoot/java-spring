package com.spring.trainings.context.ioc.proxy;

import com.spring.trainings.context.annotations.BenchMark;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProxyForBenchMark {
    
    public Object createProxy(Object o) {
        Method[] methods = o.getClass().getMethods();
        for(Method m: methods) {
            if(m.isAnnotationPresent(BenchMark.class)) {
                o = createProxyObject(o);
            }
        }
        return o;
    }

    private Object createProxyObject(final Object o) {
        final Class<?> type = o.getClass();
        return Proxy.newProxyInstance(
                type.getClassLoader(), 
                getAllInterfaces(type), 
                new InvocationHandler() {

                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        if(method.isAnnotationPresent(BenchMark.class)) {
                            System.out.println();
                            System.out.println("Method: " + method.getName());
                            long start = System.nanoTime();
                            Object obj = method.invoke(o, args);
                            long time = System.nanoTime() - start;
                            System.out.println("Invokation time of " + method.getName() + ": " + time);
                            return obj;
                        } else {
                            return method.invoke(o, args);
                        }
                    }
                    
                });
    }
    
    private Class<?>[] getAllInterfaces(Class<?> type) {
        List<Class<?>> listOfAllInterfaces = getAllInterfacesHelper(type);
        Class<?>[] interfaces = new Class<?>[listOfAllInterfaces.size()];
        int i = 0;
        for(Class<?> c : listOfAllInterfaces) {
            interfaces[i++] = c;
        }
        return interfaces;
    }
    
    private List<Class<?>> getAllInterfacesHelper(Class<?> type) {
        if(type == null) return new ArrayList<Class<?>>();
        List<Class<?>> l = new ArrayList<Class<?>>(Arrays.asList(type.getInterfaces()));
        l.addAll(getAllInterfacesHelper(type.getSuperclass()));
        return l;
    }
    
}
