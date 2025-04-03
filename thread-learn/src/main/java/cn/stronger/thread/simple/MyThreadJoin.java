package cn.stronger.thread.simple;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @class MyThreadJoin.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc do what?
 * Java的join方法用于控制线程执行流程。通过join()、join(long millis)和join(long millis, int nanos)方法，
 * 可以确保某个线程完成后再继续其他线程的执行。这有助于协调任务，确保程序在必要条件满足后再继续运行。
 */
public class MyThreadJoin extends Thread {
    public MyThreadJoin(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + "正在运行");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyThreadJoin t1 = new MyThreadJoin("线程-1");
        t1.start();
        MyThreadJoin t2 = new MyThreadJoin("线程-2");
        t2.start();
        try {
            // 等待2秒500毫秒或线程完成
//            t1.join(2000, 500000);

            t2.join(2000L);
            System.out.println("t2线程存货状态：" + t2.isAlive());
            // 等待t1线程完成
            t1.join();
            System.out.println("t1线程存货状态：" + t1.isAlive());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程继续执行");
    }
}
