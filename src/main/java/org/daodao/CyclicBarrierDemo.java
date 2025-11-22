package org.daodao;

import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierDemo {
    public static void main(String[] args) {
        CyclicBarrier barrier = new CyclicBarrier(4, () -> System.out.println("所有线程都已完成他们的任务！"));
        Thread t1 = new Thread(new Task(barrier, 1));
        Thread t2 = new Thread(new Task(barrier, 2));
        Thread t3 = new Thread(new Task(barrier, 3));
        Thread t4 = new Thread(new Task(barrier, 4));
        
        t1.start();
        t2.start();
        t3.start();
        t4.start();
    }
}



class Task implements Runnable {
    private final CyclicBarrier barrier;
    private final int taskNumber;
 
    public Task(CyclicBarrier barrier, int taskNumber) {
        this.barrier = barrier;
        this.taskNumber = taskNumber;
    }
 
    @Override
    public void run() {
        try {
            // 模拟任务处理时间
            System.out.println("线程 " + taskNumber + " 开始工作");
            Thread.sleep(1000); // 假设每个任务需要1秒完成
            System.out.println("线程 " + taskNumber + " 完成工作");
            barrier.await(); // 到达屏障点
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}