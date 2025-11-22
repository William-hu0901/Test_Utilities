package org.daodao;

import java.util.concurrent.atomic.AtomicInteger;

public class CasExample {
    private static AtomicInteger value = new AtomicInteger(0);
 
    public static void main(String[] args) {
        // 尝试将值从0更新到10
        boolean updated = value.compareAndSet(0, 10); // 预期原值是0，新值是10
        System.out.println("Value updated: " + updated); // 输出更新结果
        System.out.println("Current value: " + value.get()); // 输出当前值
    }
}