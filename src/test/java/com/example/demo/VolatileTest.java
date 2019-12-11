package com.example.demo;

import org.junit.Test;

public class VolatileTest {
    public static volatile int n = 0;
    class JoinThread extends Thread {
        public void run() {
            for (int i = 0; i < 10; i++)
                try {
                    n = n + 1;
                    sleep(3);  //  为了使运行结果更随机，延迟3毫秒
                } catch (Exception e) {
                }
        }
    }

    @Test
    public void test() throws InterruptedException {
        Thread threads[] = new Thread[100];
        for (int i = 0; i < threads.length; i++)
            threads[i] = new JoinThread();
        for (int i = 0; i < threads.length; i++)
            threads[i].start();
        for (int i = 0; i < threads.length; i++)
            threads[i].join();
        System.out.println(" n= " + n);
    }
}
