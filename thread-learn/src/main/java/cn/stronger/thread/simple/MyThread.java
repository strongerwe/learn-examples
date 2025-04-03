package cn.stronger.thread.simple;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @class MyThread.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc do what?
 */
public class MyThread extends Thread {

    /**
     * 继承Thread 类，重写run方法
     */
    @Override
    public void run() {
        int n = 0;
        while (n < 5) {
            System.out.println(Thread.currentThread().getName() + "| 继承Thread类，重写run方法创建的线程：MyThread：" + n);
            try {
                Thread.sleep(1000L * n);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            n++;
        }
    }

    /* Thread类的核心方法：
   *   start()：启动线程执行
       run()：包含线程执行的代码
       sleep(long millis)：使线程休眠指定时间
       join()：等待线程结束
       interrupt()：中断线程
       isAlive()：检查线程是否存活
       getName()：获取线程名称
       setName(String name)：设置线程名称
       getPriority()：获取线程优先级
       setPriority(int priority)：设置线程优先级
   *   */
    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread();
        /* 启动线程执行 */
        myThread.start();
        /* 线程执行的代码 */
        myThread.run();
        /* 线程休眠  throws InterruptedException*/
        myThread.sleep(1000L);
        /* 等待线程结束 */
        myThread.join();
        /* 中断线程 */
        myThread.interrupt();
        /* 检查线程是否存活 */
        boolean alive = myThread.isAlive();
        System.out.println(myThread.getName() + (alive ? "线程属于存活状态" : "线程属于非存活状态"));
        /* 获取线程名称 */
        String threadName = myThread.getName();
        System.out.println("线程名称为：" + threadName);
        /* 获取线程优先级 值越大优先级越高 */
        /* 在大多数情况下，不推荐过分依赖线程的优先级来控制程序的执行流程。更好的方法是使用同步机制（如锁、信号量等）和适当的线程间通信（如wait()和notify()方法），或者使用更高级的并发工具如ExecutorService、Future等来管理线程的执行顺序和资源。 */
        int priority = myThread.getPriority();
        /* 设置线程优先级 介于1到10之间的整数作为参数 */
        myThread.setPriority(Thread.MAX_PRIORITY);
    }

    /**
     * The minimum priority that a thread can have.
     */
    public final static int MIN_PRIORITY = 1;

    /**
     * The default priority that is assigned to a thread.
     */
    public final static int NORM_PRIORITY = 5;

    /**
     * The maximum priority that a thread can have.
     */
    public final static int MAX_PRIORITY = 10;


}
