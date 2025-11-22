package org.daodao;

public class InheritableThreadLocalDemo {
    private static final InheritableThreadLocal<String> inheritableThreadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        // 父线程设置值
        inheritableThreadLocal.set("ParentValue");
        System.out.println("父线程值: " + inheritableThreadLocal.get());

        // 创建子线程
        Thread childThread = new Thread(() -> {
            // 子线程读取继承的值
            System.out.println("子线程继承值: " + inheritableThreadLocal.get());
            
            // 子线程修改值（不影响父线程）
            inheritableThreadLocal.set("ChildValue");
            System.out.println("子线程修改后值: " + inheritableThreadLocal.get());
        });

        childThread.start();
        
        // 确保子线程执行完毕
        try {
            childThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // 父线程值保持不变
        System.out.println("父线程最终值: " + inheritableThreadLocal.get());
    }
}