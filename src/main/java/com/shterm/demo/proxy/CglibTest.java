package com.shterm.demo.proxy;

import net.sf.cglib.proxy.Enhancer;

public class CglibTest {

    public static void main(String[] args) {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(RealClass.class);
        enhancer.setCallback(new MyInterput());

        RealClass o = (RealClass) enhancer.create();
        o.doSomeThing();
    }
}
