package cn.stronger.thread.simple;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @class ThreadLocalExample.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc do what?
 */
public class ThreadLocalExample {

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(100);
        System.out.println("主线程值: " + threadLocal.get());

        new Thread(() -> {
            threadLocal.set(200);
            System.out.println("线程1值: " + threadLocal.get());
        }).start();
    }
}
