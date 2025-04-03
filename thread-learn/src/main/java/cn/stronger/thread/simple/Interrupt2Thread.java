package cn.stronger.thread.simple;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @class Interrupt2Thread.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc Java的interrupt方法用于控制线程执行流程。通过interrupt、isInterrupted和interrupted方法，可以优雅地处理线程中断。
 *          正确处理InterruptedException能确保线程在各种条件下可控地响应中断，使应用程序行为符合预期。
 */
public class Interrupt2Thread extends Thread {
    public Interrupt2Thread(String name) {
        super(name);
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            if (isInterrupted()) {
                System.out.println(Thread.currentThread().getName() + "被中断");
                break;
            }
            System.out.println(Thread.currentThread().getName() + "运行中，迭代：" + i);
            try {
                Thread.sleep(1000); // 休眠1秒
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getName() + "在睡眠中被中断");
                break;
            }
        }
        System.out.println(Thread.currentThread().getName() + "已结束");
    }

    public static void main(String[] args) {
        Interrupt2Thread t1 = new Interrupt2Thread("线程-1");
        t1.start();

        try {
            Thread.sleep(3000); // 主线程休眠3秒
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        t1.interrupt(); // 中断线程1
        t1.isAlive();
    }
}
