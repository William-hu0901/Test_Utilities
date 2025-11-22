package org.daodao;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
 
public class ThreadPoolDemo2 {
    public static void main(String[] args) {
        // 创建一个具有4个最大线程数的线程池
        int corePoolSize = 4;
        int maximumPoolSize = 4;
        long keepAliveTime = 1; // 非核心线程空闲存活时间，单位为秒
        TimeUnit unit = TimeUnit.SECONDS;
        BlockingQueue<Runnable> workQueue = new LinkedBlockingQueue<>(10); // 队列容量设置为10, 线程池容量+队列容量需大于任务数量，否则无法正常关闭线程池
        ThreadFactory threadFactory = Executors.defaultThreadFactory();
        RejectedExecutionHandler handler = new ThreadPoolExecutor.AbortPolicy(); // 拒绝策略
 
        ThreadPoolExecutor executor = new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler);
 
        // 创建10个任务
        List<Future<String>> results = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            final int taskId = i;
            Callable<String> task = () -> {
                // 模拟任务执行时间
                Thread.sleep(1000); // 休眠1秒
                return "Result of task " + taskId;
            };
            Future<String> future = executor.submit(task); // 提交任务并获取Future对象
            results.add(future);
        }
 
        // 关闭线程池，不再接受新任务，等待已提交的任务完成
        System.err.println("Close threadpool.");
        executor.shutdown();
        try {
            // 等待所有任务完成，最多等待30秒
            if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
                System.err.println("Tasks did not complete in time.");
            }
        } catch (InterruptedException e) {
            System.err.println("Task interrupted.");
        } finally {
            // 检查是否有未完成的任务，如果有，则尝试取消它们
            executor.shutdownNow(); // 中断正在执行的任务，停止接受新任务，并返回等待执行的任务列表
        }
 
        // 获取并打印所有任务的结果
        for (Future<String> result : results) {
            try {
                System.out.println(result.get()); // 获取并打印任务结果
            } catch (InterruptedException | ExecutionException e) {
                System.err.println("Exception in getting result: " + e.getMessage());
            }
        }
    }
}