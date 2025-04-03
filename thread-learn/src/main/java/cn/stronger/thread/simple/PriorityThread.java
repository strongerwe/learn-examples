package cn.stronger.thread.simple;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @class PriorityThread.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc do what?
 */
public class PriorityThread extends Thread {

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "优先级：" + getPriority());
    }
}
