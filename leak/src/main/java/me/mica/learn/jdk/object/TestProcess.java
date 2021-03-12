package me.mica.learn.jdk.object;

import java.io.IOException;
import java.util.Date;

public class TestProcess {

    public static void main(String[] args) {
        new Thread(() -> {
            func();
        }).start();
    }

    private static void func() {
        try {
            System.out.println(new Date());
            Process exec = Runtime.getRuntime().exec("telnet 10.66.0.17 29443");
            exec.waitFor();
            System.out.println(new Date());
            System.out.println("hello !");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
