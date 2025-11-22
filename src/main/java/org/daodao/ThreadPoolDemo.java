package org.daodao;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    public static void main(String[] args) {
        // 创建线程池（核心线程2，最大线程4，队列容量2），线程池容量+队列容量需大于任务数量，否则无法正常关闭线程池
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
            2, 4, 5, TimeUnit.SECONDS,
            new ArrayBlockingQueue<>(2), 
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy()
        );
        
//        默认是 ThreadPoolExecutor.AbortPolicy，直接抛异常
//        ThreadPoolExecutor.CallerRunsPolicy，调用execute方法的线程自己执行该任务。
//        ThreadPoolExecutor.DiscardPolicy ，直接扔掉任务。
//        ThreadPoolExecutor.DiscardOldestPolicy ，扔掉队列的第一个任务，重新尝试。（可能仍会失败）

        // 提交10个任务
        int taskCount = 7;
        for (int i = 0; i < taskCount; i++) {
            final int taskId = i;
            if (executor.getActiveCount() >= executor.getMaximumPoolSize()) { // 如果活动线程数达到最大值
                System.out.println("达到最大线程数，尝试调整线程池大小为："+taskCount);
                executor.setMaximumPoolSize(taskCount); // 调整最大容量
            }
            executor.execute(() -> {
                System.out.println(Thread.currentThread().getName() 
                    + " 执行任务 " + taskId);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
        
        executor.shutdown(); //关闭空闲的线程
//        executor.shutdownNow();  //关闭所有线程
        try {
        	//确保合理地处理中断异常，以避免在等待过程中丢失中断信号
            if (!executor.awaitTermination(10, TimeUnit.SECONDS)) {
                System.err.println("线程池没有在指定时间内终止");
            }
        } catch (InterruptedException e) {
            executor.shutdownNow(); // 当前线程被中断，尝试立即关闭线程池
            Thread.currentThread().interrupt(); // 重新设置中断状态
        }
    }
}
