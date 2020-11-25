package com.learn.concurrent.lock;

import java.util.concurrent.CountDownLatch;
import java.util.stream.IntStream;

public class TestCountdownLatch {

    public static void main(String[] args) {

        CountDownLatch latch = new CountDownLatch(3);
        IntStream.range(0, 3).forEach(index -> {
            new Thread(new Work(latch)).start();
        });
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main finish!");
    }

    static class Work implements Runnable {

        private CountDownLatch latch;

        Work(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            try {
                System.out.println(Thread.currentThread().getName() + " is coming now!");
                Thread.sleep(5 * 1000);
                latch.countDown();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
