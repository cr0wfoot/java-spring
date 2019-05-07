package com.example.spring.beanlifecyclebreak;

import com.example.spring.annotations.BenchMark;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProxyForBenchMark {

    public Object createProxy(Object o) {
        Method[] methods = o.getClass().getMethods();
        for (Method m : methods) {
            o = createProxyForBenchMark(o, m);
        }
        return o;
    }

    private Object createProxyForBenchMark(Object o, Method m) {
        if (m.isAnnotationPresent(BenchMark.class)) {
            return createProxyObject(o);
        }
        return o;
    }

    private Object createProxyObject(final Object o) {
        final Class<?> type = o.getClass();
        return Proxy.newProxyInstance(type.getClassLoader(), getAllInterfaces(type), handleMethodInvocation(o));
    }

    private InvocationHandler handleMethodInvocation(final Object o) {
        return new InvocationHandler() {

            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.isAnnotationPresent(BenchMark.class)) {
                    printStartOfMessage(method.getName());
                    long start = System.nanoTime();
                    Object obj = method.invoke(o, args);
                    long time = System.nanoTime() - start;
                    printEndOfMessage(method.getName(), time);
                    return obj;
                } else {
                    return method.invoke(o, args);
                }
            }

            private void printStartOfMessage(String methodName) {
                System.out.println("\nMethod: " + methodName);
            }

            private void printEndOfMessage(String methodName, long executionTime) {
                System.out.println("Invocation time of " + methodName + ": " + executionTime);
            }
        };
    }


    private Class<?>[] getAllInterfaces(Class<?> type) {
        List<Class<?>> listOfAllInterfaces = getListOfInterfaces(type);
        return convertListOfInterfacesToArray(listOfAllInterfaces);
    }

    private List<Class<?>> getListOfInterfaces(Class<?> type) {
        if (type == null) return new ArrayList<>();
        List<Class<?>> listOfInterfaces = new ArrayList<>(Arrays.asList(type.getInterfaces()));
        listOfInterfaces.addAll(getListOfInterfaces(type.getSuperclass()));
        return listOfInterfaces;
    }

    private Class<?>[] convertListOfInterfacesToArray(List<Class<?>> listOfAllInterfaces) {
        Class<?>[] interfaces = new Class<?>[listOfAllInterfaces.size()];
        int i = 0;
        for (Class<?> c : listOfAllInterfaces) {
            interfaces[i++] = c;
        }
        return interfaces;
    }
}
