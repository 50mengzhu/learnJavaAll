package com.learn.timerframework.quartz;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Quartz框架中的一个实体 Job任务
 *
 * @author daixiao
 */
@DisallowConcurrentExecution
public class MyJob implements Job {

    public static AtomicInteger count = new AtomicInteger(0);

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Thread-" + count.getAndIncrement());
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(new Date());
    }
}
