package org.daodao;


public class ThreadLocalDemo {
    private static final ThreadLocal<Integer> counter = ThreadLocal.withInitial(() -> 0);
//    private static final ThreadLocal<Integer> counter = new ThreadLocal<Integer>();

    public static void main(String[] args) {
        new Thread(() -> {
            counter.set(1);
            System.out.println("Thread-1 counter: " + counter.get()); // 输出1
        }).start();

        new Thread(() -> {
            System.out.println("Thread-2 counter: " + counter.get()); // 输出0
        }).start();
        
        //任务结束时手动移除,防止OOM
        counter.remove();
    }
}
