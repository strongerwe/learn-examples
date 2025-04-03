package cn.stronger.thread.simple;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @class CreateThreadTest.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc do what?
 */
public class CreateThreadTest {

    public static void main(String[] args) {
        testMyThread();
        testMyRunnable();
        testLambdaRunnableExample();
        testLambdaThreadExample();
    }

    public static void testMyThread() {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    public static void testMyRunnable() {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        thread.start();
    }

    public static void testLambdaThreadExample() {
        Thread thread = new Thread(() -> {
            int n = 0;
            while (n < 5) {
                System.out.println("Lambda 表达式创建线程 Thread-" + n);
                try {
                    Thread.sleep(1000L * n);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                n++;
            }

        });
        thread.start();
    }

    public static void testLambdaRunnableExample() {
        Runnable runnable = () -> {
            int n = 0;
            while (n < 5) {
                System.out.println("Lambda 表达式创建线程 Runnable-" + n);
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                n++;
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}
