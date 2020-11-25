package com.learn.gc;

public class OomTest {

    private static final int _2MB = 1 << 20;

    // -Xms20M  -Xmx20M : heap size
    // -Xmn10M : young gen



    public static void main(String[] args) {
        byte[] a1, a2, a3, a4;
        a1 = new byte[7 * _2MB];
    }
}
