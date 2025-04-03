package cn.stronger.thread.simple;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author qiang.w
 * @version release-1.0.0
 * @class RunnableWithExecutorExample.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc 结合Executor使用Runnable
 */
public class RunnableWithExecutorExample {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(2);
        MyRunnable run = new MyRunnable();
        executor.submit(run);
        executor.submit(run);
        /* 关闭线程池增加任务通道，将在执行线程执行完毕（包括队列中的线程） */
        executor.shutdown();
        /* TODO EXCEPTION: Task java.util.concurrent.FutureTask@1b6d3586 rejected from java.util.concurrent.ThreadPoolExecutor@4554617c[Shutting down, pool size = 2, active threads = 2, queued tasks = 0, completed tasks = 0] */
//        executor.submit(run);
        /* 关闭线程池增加任务通道，停止当前执行的现场，不再开启队列中的任务 */
//        executor.shutdownNow();
//        executor.submit(run);
    }
}
