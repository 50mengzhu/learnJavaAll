package com.learn.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class ThreadPoolExecutorTest {

    ThreadPoolExecutor threadPoolExecutor;

    private void testRejectException(int policy) {
        if (policy > 4 || policy <= 0) {
            throw new IllegalArgumentException("no such policy!");
        }

        Map<Integer, RejectedExecutionHandler> rejectMap = new HashMap<>();
        rejectMap.put(1, new ThreadPoolExecutor.AbortPolicy());
        rejectMap.put(2, new ThreadPoolExecutor.CallerRunsPolicy());
        rejectMap.put(3, new ThreadPoolExecutor.DiscardPolicy());
        rejectMap.put(4, new ThreadPoolExecutor.DiscardOldestPolicy());


        // int coreCount = Runtime.getRuntime().availableProcessors() * 2;
        int coreCount = 4;
        int maxCount = 2 * coreCount;


        System.out.println(String.format("coreCount:%d, maxCount:%d", coreCount, maxCount));

        threadPoolExecutor = new ThreadPoolExecutor(coreCount, maxCount, 5, TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(5), rejectMap.get(policy));
    }

    public void test(int policy) {
        testRejectException(policy);

        IntStream.range(1, 200).forEach(i -> {
            Thread thread = new Thread(() -> {
                try {
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(String.format("i am thread:%s I print %d ", Thread.currentThread().getName(), i));
            });
            threadPoolExecutor.execute(thread);
        });
    }

    public static void main(String[] args) {
        new ThreadPoolExecutorTest().test(1);
    }
}
