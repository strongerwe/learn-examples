package cn.stronger.thread.simple;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @class ThreadSynchronization.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc 线程同步: 通过synchronized关键字控制共享资源访问
 */
public class ThreadSynchronization {
    private int count = 0;

    public synchronized void increment() {
        count++;
    }

    public int getCount() {
        return count;
    }
}
