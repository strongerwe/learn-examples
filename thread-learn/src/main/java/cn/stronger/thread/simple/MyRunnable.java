package cn.stronger.thread.simple;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @class MyRunnable.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc do what?
 */
public class MyRunnable implements Runnable {
    /**
     * 实现Runnable接口，重写run方法创建的线程
     * 使用Runnable的优势:
     *      任务与执行解耦：任务逻辑与执行线程分离。
     *      设计灵活性：类可在实现Runnable的同时继承其他类。
     *      支持线程池：可与Executor框架结合，优化线程管理。
     *      代码简洁性：结合Lambda表达式减少冗余代码。
     * Runnable接口是Java多线程编程的核心工具，通过实现该接口，可定义能被线程或线程池执行的任务。结合Java 8的Lambda表达式，代码更简洁高效。掌握Runnable接口的使用对编写并发应用至关重要。
     */
    @Override
    public void run() {
        int n = 0;
        while (n < 5) {
            System.out.println(Thread.currentThread().getName() + "| 实现Runnable接口，重写run方法创建的线程：MyRunnable：" + n);
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            n++;
        }
    }
}
