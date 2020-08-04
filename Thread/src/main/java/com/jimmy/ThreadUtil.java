package com.jimmy;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;

/**
 * @author xiongyang
 * @date 2020/3/17 15:17
 * @Description: 线程工具类 采用线程池方式创建线程
 */
public class ThreadUtil {

    //private static ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * corePoolSize - 线程池核心池的大小。
     * maximumPoolSize - 线程池的最大线程数。
     * keepAliveTime - 当线程数大于核心时，此为终止前多余的空闲线程等待新任务的最长时间。
     * unit - keepAliveTime 的时间单位。
     * workQueue - 用来储存等待执行任务的队列。
     * threadFactory - 线程工厂。
     * handler - 拒绝策略。
     */
    private static ThreadPoolExecutor threadPool = new ThreadPoolExecutor(
            5,5,10, TimeUnit.SECONDS,
            new ArrayBlockingQueue<Runnable>(1),
            new ThreadPoolExecutor.DiscardOldestPolicy());


    public ThreadUtil() {
    }

    public static void execute(Runnable runnable) throws Exception {
        threadPool.execute(runnable);
    }

}
