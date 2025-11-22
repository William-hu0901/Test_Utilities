package org.daodao;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo {

	public static void main(String[] args) throws InterruptedException {
		final CountDownLatch startLatch = new CountDownLatch(10);
		for (int i = 0; i < 10; i++) {
			
		    new Thread(() ->{
		    	System.out.println("reduce 1");
		        startLatch.countDown(); // 每个线程启动时减1
		    }).start();
		}
		startLatch.await(); // 等待所有线程启动
		System.out.println("All thread started");
    }
}
