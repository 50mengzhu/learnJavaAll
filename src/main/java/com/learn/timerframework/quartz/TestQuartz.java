package com.learn.timerframework.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

public class TestQuartz {

    public static void main(String[] args) throws SchedulerException {
        // 1. 需要创建一个Scheduler
        StdSchedulerFactory stdSchedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = stdSchedulerFactory.getScheduler();

        // 2. 创建JobDetail的示例
        JobDetail jobDetail = JobBuilder.newJob(MyJob.class).withIdentity("job1").build();
        jobDetail.getJobDataMap().put("task", jobDetail);

        // 3. Trigger 定义任务执行的间隔
        SimpleTrigger build = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group")
                .startNow()
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(1).repeatForever())
                .build();

        scheduler.scheduleJob(jobDetail, build);
        scheduler.start();

    }

}
