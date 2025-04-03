package cn.stronger.thread.simple;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @class SyncExample.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc do what?
 */
public class SyncExample {

    public static void main(String[] args) throws InterruptedException {
        ThreadSynchronization counter = new ThreadSynchronization();
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) counter.increment();
        };

        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start();
        t2.start();
        t1.join();
        t2.join();
        System.out.println("最终计数：" + counter.getCount()); // 输出2000
    }
}
