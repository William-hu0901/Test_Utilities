package org.daodao;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test")
public class ReentrantLockDemo {
	// 创建锁重入对象
    private static ReentrantLock lock = new ReentrantLock();
    
	public static void main(String[] args) throws InterruptedException {
	    //可重入
//		reenterLock();
		
		//可打断
		lockInterruptibly();
//		
		//存在其他线程竞争（等待一段时间）：尝试等待1s，1s内若主线程还未释放锁再结束
//		lockTimeout();
	        
	}
	    
	 public static void reenterLock() {
		 
		// 加锁
	        lock.lock();
	        try {
	            log.debug("enter  main");
	            m1();
	        } finally {
	            // 解锁
	        	log.debug("unlock in  main");
	            lock.unlock();
	        }
	  }
	   public static void m1() {
	        // 加锁
	        lock.lock();
	        try {
	            log.debug("enter  m1");
	            m2();
	        } finally {
	            // 解锁
	        	log.debug("unlock in m1");
	            lock.unlock();
	        }
	    }
	    
	   public static void m2() {
	        // 加锁
	        lock.lock();
	        try {
	            log.debug("enter  m2");
	        } finally {
	            // 解锁
	        	log.debug("unlock in m2");
	            lock.unlock();
	        }
	    }

	   
	   public static void lockInterruptibly() {
		   
		   Thread t1=new Thread(()->{
	            try {
	                // 尝试获取锁，但可以被打断(如果没有别的线程竞争锁,此方法就会获取lock对象上的锁)
	                /*若有竞争进入阻塞队列等待*/
	                log.debug("尝试获得锁");
	                lock.lockInterruptibly();
	            } catch (InterruptedException e) {
	                e.printStackTrace();
	                log.debug("没有获取锁,返回");
	                return;

	            }
	            try {
	                log.debug("获取到锁");
	            }finally {
	                // 将锁释放掉
	                lock.unlock();
	            }
	        },"t1");

	      // 主线程先对其进行加锁后，t1线程才启动
	      lock.lock();
	      t1.start();
	      // 主线程睡眠1s后打断t1
	      try {
	    	log.debug("主线程睡眠1s");
			Thread.sleep(1);
		  } catch (InterruptedException e) {
			log.debug("睡眠被打断");
		  }
	      log.debug("打断线程1");
	      t1.interrupt();
	   }
	   
	   
	   public static void lockTimeout() {
		   Thread t1 = new Thread(() -> {
	            log.debug("尝试获得锁");
	            // 尝试获取锁,返回值为布尔型  【成功：获取锁   失败：不可获得锁,不会进入阻塞队列等待】
	            try {
	            	if (!lock.tryLock(1, TimeUnit.SECONDS)) {
					    log.debug("获取锁失败");    // false
					    return;
					}
					
					 // 执行临界区代码
	                log.debug("线程成功获取锁");
	                
				} catch (InterruptedException e) {
					
					e.printStackTrace();
				} finally {
	                lock.unlock();     // 释放锁
	            }
	        });

	        // 主线程先对lock对象加锁
	       lock.lock();
	        log.debug("主线程成功获取锁");
	        t1.start();
	   }
    
    
}
