package cn.stronger.thread.simple;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class ThreadPriorityTest.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc 测试设置线程优先级
 */
public class ThreadPriorityTest {

    public static void main(String[] args) {
        PriorityThread t1 = new PriorityThread();
        PriorityThread t2 = new PriorityThread();
        t1.setPriority(Thread.MAX_PRIORITY);
        t2.setPriority(Thread.MIN_PRIORITY);
        t1.start();
        t2.start();
    }
}
