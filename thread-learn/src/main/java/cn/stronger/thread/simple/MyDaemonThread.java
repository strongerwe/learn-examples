package cn.stronger.thread.simple;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class MyDaemonThread.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc do what?
 */
public class MyDaemonThread extends Thread {
    @Override
    public void run() {
        while (true) {
            System.out.println("守护线程运行中");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        MyDaemonThread daemon = new MyDaemonThread();
        // 设置为守护线程
        daemon.setDaemon(true);
        daemon.start();
        try {
            // 主线程休眠3秒
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("主线程退出");
    }
}
