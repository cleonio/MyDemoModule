package com.jimmy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/**
 * @className: com.wt.task-> DynamicTask
 * @description:
 * @author: cleo
 * @createDate: 2021-07-12 17:09
 * @version: 1.0
 */
@Component
public class DynamicTask {

    @Autowired
    private ThreadPoolTaskScheduler threadPoolTaskScheduler;

    /**
     *
     * ThreadPoolTaskScheduler：线程池任务调度类，能够开启线程池进行任务调度。
     * ThreadPoolTaskScheduler.schedule()方法会创建一个定时计划ScheduledFuture，
     * 在这个方法需要添加两个参数，Runnable（线程接口类） 和CronTrigger（定时任务触发器）
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler threadPoolTaskScheduler() {
        return new ThreadPoolTaskScheduler();
    }


    /**
     * 在ScheduledFuture中有一个cancel可以停止定时任务。
     */
    private ScheduledFuture<?> future;

    public String startCron(Runnable task,long seconds) {
        String cron = String.format("*/%d * * * * ?", seconds);
        future = threadPoolTaskScheduler.schedule(task, new CronTrigger(cron));


        System.out.println("DynamicTaskController.startCron()");
        try{
            //get()方法用来获取执行结果，这个方法会产生阻塞，会一直等到任务执行完毕才返回；
            //future的get()设定超时时间
            Object o = future.get(3900, TimeUnit.MILLISECONDS);
            System.out.println("===>>>  "+o);
            Object o1 = future.get();
            System.out.println("===>>>  "+o1);
        }
        catch ( Exception e){
            System.out.println(e);
        }
        return "startTask";
    }


    public String stopCron() {
        if (future != null) {
            future.cancel(true);
        }
        System.out.println("DynamicTaskController.stopCron()");
        return "stopTask";
    }

    /**
     * 变更任务间隔，再次启动
     **/
    public String changeCron() {
        stopCron();// 先停止，在开启.
        future = threadPoolTaskScheduler.schedule(new SchedulingRunnable(), new CronTrigger("*/10 * * * * *"));
        System.out.println("DynamicTaskController.changeCron()");
        return "changeCron";
    }

}
