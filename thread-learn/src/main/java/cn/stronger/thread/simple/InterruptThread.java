package cn.stronger.thread.simple;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @class InterruptThread.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc do what?
 */
public class InterruptThread extends Thread {
    public InterruptThread(String name) {
        super(name);
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            System.out.println(Thread.currentThread().getName() + "正在运行");
            try {
                // 休眠1秒
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // 当线程在阻塞状态下被中断时（如睡眠或等待），会抛出InterruptedException，需使用try-catch块处理。
                System.out.println(Thread.currentThread().getName() + "在睡眠中被中断");
                interrupt(); // 重新设置中断状态
            }
        }
        System.out.println(Thread.currentThread().getName() + "已结束");
    }

    public static void main(String[] args) {
        InterruptThread t1 = new InterruptThread("线程-1");
        t1.start();

        try {
            Thread.sleep(3000); // 主线程休眠3秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt(); // 中断线程1
    }
}
