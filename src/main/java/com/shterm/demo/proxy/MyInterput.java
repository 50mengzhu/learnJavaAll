package com.shterm.demo.proxy;

import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class MyInterput implements MethodInterceptor {
    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        before();
        Object o1 = methodProxy.invokeSuper(o, objects);
        after();
        return o1;
    }


    public void before() {
        System.out.println("before");
    }

    public void after() {
        System.out.println("after");
    }
}
