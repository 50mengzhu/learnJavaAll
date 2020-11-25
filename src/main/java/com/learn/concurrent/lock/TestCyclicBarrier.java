package com.learn.concurrent.lock;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

public class TestCyclicBarrier {

    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(3);

        IntStream.range(0, 3).forEach(index -> {
            new Thread(new Work(barrier)).start();
        });
        System.out.println("main finish!");
    }

    static class Work implements Runnable {

        private CyclicBarrier barrier;

        Work(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            IntStream.range(0, 3).forEach(index -> {
                try {
                    Thread.sleep(5 * 1000);
                    System.out.println(Thread.currentThread().getName() + index + " is arriving!");
                    barrier.await();
                } catch (InterruptedException | BrokenBarrierException e) {
                    e.printStackTrace();
                }
            });
        }
    }
}
