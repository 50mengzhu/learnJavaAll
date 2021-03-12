package me.mica.learn.jdk.object;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class LearnWaitNotify {

    private void testNotify() {
        List<String> waitList = new ArrayList<>();
        List<String> notifyList = new ArrayList<>();

        Object lock = new Object();

        IntStream.range(0, 50).forEach(i -> {
            new Thread(() -> {
                synchronized (lock) {
                    String name = Thread.currentThread().getName();
                    System.out.println("name " + name + " is waiting");
                    waitList.add(name);
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("name" + name + " get lock");
                    notifyList.add(name);
                }
            }, String.valueOf(i)).start();
            try {
                TimeUnit.MILLISECONDS.sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        IntStream.range(0, 50).forEach(i -> {
            synchronized (lock) {
                lock.notify();
                try {
                    TimeUnit.MILLISECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("waitList: " + waitList);
        System.out.println("notifyList: " + notifyList);
    }

    public static void main(String[] args) {
        new LearnWaitNotify().testNotify();
    }
}
