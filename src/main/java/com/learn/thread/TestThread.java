package com.learn.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.stream.IntStream;

public class TestThread {


    /**
     * 通过实现 Runnable接口实现对线程的创建
     * 本质上还是会使用 Thread进行线程启动
     */
    public void createThreadUsingRunnable() {

        class UseRunnable implements Runnable {

            @Override
            public void run() {
                System.out.println("first way to create thread!");
            }
        }

        new Thread(new UseRunnable()).start();
    }


    /**
     * 方法二，通过继承 Thread类实现对线程的创建
     * 只需要重写 Thread的 run方法就可以完成线程重写
     */
    public void createThreadUsingThread() {
        class UseThread extends Thread {
            @Override
            public void run() {
                System.out.println("second way to create thread!");
            }
        }
        new UseThread().start();
    }


    /**
     * 实现方法三：是通过实现 Callable接口，然后实现 call方法
     * 此方法不单单是实现 Callable这个接口，而是讲这个接口配合 FutureTask使用
     * 然后向 Thread中传入 FutureTask实体创建线程
     */
    public void createThreadUsingCallable() {
        class UseCallable implements Callable<Boolean> {

            @Override
            public Boolean call() throws Exception {
                Thread.sleep(10 * 1000);
                System.out.println("third way to create thread!");
                return true;
            }
        }

        UseCallable useCallable = new UseCallable();
        FutureTask<Boolean> futureTask = new FutureTask<>(useCallable);
        new Thread(futureTask).start();
        try {
            System.out.println("waiting for thread result!");
            Boolean aBoolean = futureTask.get();
            System.out.println(aBoolean);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.createThreadUsingRunnable();
        testThread.createThreadUsingThread();
        testThread.createThreadUsingCallable();
    }

}
