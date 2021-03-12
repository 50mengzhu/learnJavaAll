package com.learn.thread;

public class Test {

    static synchronized void func1() {
        System.out.println("hello");
    }

    synchronized void func2() {
        System.out.println("hello again");
    }

    void func3() {
        synchronized (this) {
            System.out.println("hello again ans again");
        }
    }

    void func4() {
        func1();
    }

}
