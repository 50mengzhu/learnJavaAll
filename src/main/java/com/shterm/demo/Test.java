package com.shterm.demo;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

public class Test {

    public static final Integer _1Mb = 1 << 20;

    public static void main(String[] args) throws InterruptedException {

        byte[] big = new byte[_1Mb];
        WeakReference<Byte[]> ref = new WeakReference(big);
        System.out.println("before: ");
        System.out.println(ref.get());
        big = null;
        System.gc();
        TimeUnit.SECONDS.sleep(5);
        System.out.println(ref.get());
        System.out.println(big);
    }

    private void newA() {
        ThreadLocal threadLocal = new ThreadLocal();
    }
}
