package org.daodao;


public class VolatileDemo {
    // 使用volatile关键字确保多线程环境下的可见性和有序性
    private volatile int count = 0;
 
    // 增加计数器的值
    public void increment() {
        count++;
    }
 
    // 获取计数器的当前值
    public int getCount() {
        return count;
    }
 
    public static void main(String[] args) {
    	VolatileDemo counter = new VolatileDemo();
 
        // 创建并启动10个线程来增加计数器的值
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                	counter.increment();
                }
            }).start();
        }
 
        // 等待所有线程完成
        try {
            Thread.sleep(1000); // 简单延时以确保所有线程完成
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt(); // 恢复中断状态
        }
 
        // 输出最终的计数器值
        System.out.println("Final count: " + counter.getCount());
    }
}