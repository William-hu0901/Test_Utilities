package org.daodao;


import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j(topic = "c.Test24")
public class ReentrantLockDemo2 {

    static final Object room = new Object();

    static boolean hasCigarette = false;
    static boolean hasTakeout = false;

    static ReentrantLock ROOM = new ReentrantLock();
    // 等待烟的休息室（创建一个新的条件变量）
    static Condition waitCigaretteSet = ROOM.newCondition();
    // 等外卖的休息室（创建一个新的条件变量）
    static Condition waitTakeoutSet = ROOM.newCondition();

    public static void main(String[] args) {

        new Thread(() -> {
            // 尝试获取ReentrantLock
            ROOM.lock();
            try {
                log.debug("有烟没？[{}]", hasCigarette);
                while (!hasCigarette) {
                    log.debug("没烟，先歇会！");
                    try {
                        // 进入等烟休息室等待
                        waitCigaretteSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("可以开始干活了");
            } finally {
                // 解锁
                ROOM.unlock();
            }
        }, "小南").start();

        new Thread(() -> {
            ROOM.lock();
            try {
                log.debug("外卖送到没？[{}]", hasTakeout);
                while (!hasTakeout) {
                    log.debug("没外卖，先歇会！");
                    try {
                        waitTakeoutSet.await();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                log.debug("可以开始干活了");
            } finally {
                ROOM.unlock();
            }
        }, "小女").start();
        // 送外卖线程
        try {
	    	log.debug("主线程睡眠1s");
			Thread.sleep(1);
		  } catch (InterruptedException e) {
			log.debug("睡眠被打断");
		}
        
        new Thread(() -> {
            ROOM.lock();
            try {
                hasTakeout = true;
                // 唤醒线程
                log.debug("外卖到了");
                waitTakeoutSet.signal();
            } finally {
                ROOM.unlock();
            }
        }, "送外卖的").start();

        // 送烟线程
        try {
	    	log.debug("主线程再睡眠1s");
			Thread.sleep(1);
		  } catch (InterruptedException e) {
			log.debug("睡眠被打断");
		}
        
        new Thread(() -> {
            ROOM.lock();
            try {
                hasCigarette = true;
                // 唤醒线程
                log.debug("烟到了");
                waitCigaretteSet.signal();
            } finally {
                ROOM.unlock();
            }
        }, "送烟的").start();
    }
}
