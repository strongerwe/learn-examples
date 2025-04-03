# 多线程知识点

## 一、基础知识

### 1. 线程Thread的核心方法

> 1. start()：启动线程执行
> 2. run()：包含线程执行的代码
> 3. sleep(long millis)：使线程休眠指定时间
> 4. join()：等待线程结束
> 5. interrupt()：中断线程
> 6. isAlive()：检查线程是否存活
> 7. getName()：获取线程名称
> 8. setName(String name)：设置线程名称
> 9. getPriority()：获取线程优先级
> 10. setPriority(int priority)：设置线程优先级

### 2. 线程的生命周期

> 1. **新建（New）**：线程已创建但未启动
> 2. **可运行（Runnable）**：线程准备运行，等待CPU调度
> 3. **运行（Running）**：线程正在执行
> 4. **阻塞（Blocked）**：线程等待监视器锁
> 5. **等待（Waiting）**：线程无限期等待其他线程操作
> 6. **限时等待（Timed Waiting）**：线程在指定时间内等待其他线程操作
> 7. **终止（Terminated）**：线程执行完毕

### 3. `wait()`、`notify()`、`notifyAll()`的区别

> `wait()`、`notify()` 和 `notifyAll()` 是 Java 中用于线程间通信的核心方法，均定义在 Object 类中。它们的作用是协调多个线程对共享资源的访问，通过 “等待-唤醒” 机制实现线程协作。以下是它们的作用和区别：

---

#### **1. wait()**

* **作用**：
  使当前线程进入 **等待状态 **（释放对象锁），直到以下情况发生：
  1. 其他线程调用该对象的 `notify()` 或 `notifyAll()` 方法；
  2. 其他线程调用 `interrupt()` 中断当前线程；
  3. 设置了超时时间的 `wait(long timeout)` 超时。
* **关键点**：
  * **释放锁**：调用 `wait()` 后，当前线程会立即释放持有的对象锁。
  * **需要在同步块/方法中使用 **：必须在 `synchronized` 块或方法内调用，否则会抛出 `IllegalMonitorStateException`。

---

#### **2. notify()**

* **作用**：
  唤醒 **一个**正在等待该对象锁的线程（如果有多个线程在等待，会随机选择一个）。
* **关键点**：
  * **不释放锁~~~~**：调用 `notify()` 后，当前线程不会立即释放锁，而是继续执行完当前同步代码块后才释放锁。
  * **唤醒单个线程**：适用于只有一个线程需要被唤醒的场景，但无法精确控制唤醒哪一个线程。

---

#### **3. notifyAll()**

* **作用**：
  唤醒 **所有**正在等待该对象锁的线程。
* **关键点**：
  * **公平竞争**：所有被唤醒的线程会竞争对象锁，但只有一个线程能成功获取锁继续执行，其他线程会再次进入等待状态。
  * **避免死锁**：适用于需要唤醒所有等待线程的场景（如条件变化需要多个线程重新检查状态）。

---

#### **核心区别**


| **方法**      | **唤醒线程数量** | **锁释放行为**                 | **适用场景**                         |
| ------------- | ---------------- | ------------------------------ | ------------------------------------ |
| `wait()`      | 当前线程进入等待 | 释放锁                         | 等待某个条件满足                     |
| `notify()`    | 唤醒一个线程     | 不释放锁（执行完同步块后释放） | 只需唤醒单个线程（如生产者-消费者）  |
| `notifyAll()` | 唤醒所有线程     | 不释放锁（执行完同步块后释放） | 需唤醒所有线程重新竞争（如资源更新） |

---

#### **使用示例（生产者-消费者模式）**

```java
// 共享资源
class SharedResource {
    private int count = 0;
    private final Object lock = new Object();

    public void produce() {
        synchronized (lock) {
            while (count >= 10) { // 使用 while 防止虚假唤醒
                try {
                    lock.wait(); // 等待消费者消费
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            count++;
            lock.notifyAll(); // 通知消费者可以消费
        }
    }

    public void consume() {
        synchronized (lock) {
            while (count <= 0) {
                try {
                    lock.wait(); // 等待生产者生产
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
            count--;
            lock.notifyAll(); // 通知生产者可以生产
        }
    }
}
```

---

#### **注意事项**

1. **必须在同步块/方法中使用 **：
   `wait()`、`notify()`、`notifyAll()` 必须在 `synchronized` 上下文中调用，否则会抛出异常。
2. **虚假唤醒（Spurious Wakeup） **：
   线程可能在未被 `notify()` 或 `notifyAll()` 的情况下被唤醒，因此需用 `while` 循环检查条件，而非 `if` 语句。
3. **锁的归属 **：
   `wait()` 会释放锁，但 `notify()`/`notifyAll()` 不会立即释放锁，需等待当前同步块执行完毕。
4. **选择 `notify()` 还是 `notifyAll()` **：
   * 若只有一个线程需要被唤醒（如一对一生产者-消费者），用 `notify()`。
   * 若多个线程可能需要响应条件变化（如线程池任务队列更新），用 `notifyAll()`。

---

#### **总结**

* `wait()`：让线程等待并释放锁。
* `notify()`：唤醒单个线程，但不保证公平性。
* `notifyAll()`：唤醒所有线程，确保所有等待线程重新竞争锁。

正确使用这些方法可以实现高效的线程协作，避免死锁和资源竞争。

### 4. 什么是守护线程，作用是什么？有哪些应用场景

> 在 Java 中，**守护线程（Daemon Thread） **是一种特殊的线程，它为其他用户线程（非守护线程）提供后台支持服务。当所有用户线程结束时，守护线程会自动终止，JVM 也会随之退出。

---

#### **守护线程的作用**

1. **后台服务**
   守护线程在后台运行，执行辅助性任务（如垃圾回收、资源清理、监控等），但不会阻止程序终止。
2. **资源释放**
   当所有用户线程结束时，JVM 会强制终止所有守护线程，确保程序快速退出。
3. **避免阻塞**
   守护线程通常用于执行无需长期运行的任务，避免因线程未结束导致程序无法终止。

---

#### **应用场景**

1. **垃圾回收（GC）**
   JVM 的垃圾回收线程是经典的守护线程，负责在后台回收不再使用的对象内存。
2. **日志记录与监控**
   后台记录日志、监控系统状态或性能指标（如 CPU、内存使用率）。
3. **定时任务**
   后台执行周期性任务（如心跳检测、缓存清理），但无需保证任务必须完成。
4. **GUI 应用**
   在图形界面程序中，后台加载数据或处理事件，避免阻塞主线程（如界面刷新）。
5. **网络服务**
   后台监听连接或处理请求（如 Socket 服务器中的空闲连接检测）。

---

#### **守护线程 vs 用户线程**


| **特性**         | **守护线程**                       | **用户线程**                 |
| ---------------- | ---------------------------------- | ---------------------------- |
| **生命周期**     | 依赖用户线程，随用户线程结束而终止 | 独立运行，JVM 等待其执行完毕 |
| **典型用途**     | 后台服务、辅助任务                 | 核心业务逻辑、关键任务       |
| **JVM 退出条件** | 所有用户线程结束时自动退出         | 需主动结束或等待其完成       |

---

#### **如何设置守护线程**

在 Java 中，通过 `Thread.setDaemon(true)` 方法将线程标记为守护线程，且必须在 `start()` 之前调用：

```java
Thread daemonThread = new Thread(() -> {
    while (true) {
        // 后台任务（如监控）
    }
});
daemonThread.setDaemon(true); // 设置为守护线程
daemonThread.start();
```

#### **注意事项**

1. **避免资源泄漏**
   守护线程可能被突然终止，需确保其操作是原子的或能正确释放资源（如关闭文件、数据库连接）。
2. **不适用于关键任务**
   守护线程可能随时被终止，不适合执行必须完成的任务（如写入文件、事务提交）。
3. **子线程继承守护状态**
   守护线程创建的子线程默认也是守护线程，需显式修改为用户线程（`setDaemon(false)`）。

---

#### **示例场景**

```java
public class DaemonExample {
    public static void main(String[] args) {
        // 用户线程：模拟主任务
        Thread userThread = new Thread(() -> {
            try {
                Thread.sleep(3000); // 模拟主任务运行3秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("用户线程结束");
        });

        // 守护线程：模拟后台监控
        Thread daemonThread = new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(500);
                    System.out.println("守护线程：监控中...");
                } catch (InterruptedException e) {
                    break; // 被中断时退出
                }
            }
        });
        daemonThread.setDaemon(true); // 设置为守护线程

        userThread.start();
        daemonThread.start();
    }
}
```


#### **总结**

* **使用场景**：后台辅助任务（监控、日志、缓存）、无需长期运行的周期性操作。
* **风险控制**：确保守护线程的任务是可中断的，且不会因突然终止导致数据不一致。
* **设计原则**：将关键业务逻辑放在用户线程，非核心功能放在守护线程。

### 5.详细说说Thread中的`join()`方法的实现原理，以及使用场景
```java
// `join()`核心代码
public final synchronized void join(long millis) throws InterruptedException {
    long base = System.currentTimeMillis();
    long now = 0;

    if (millis < 0) {
        throw new IllegalArgumentException("timeout value is negative");
    }

    if (millis == 0) {
        while (isAlive()) {
            /* 调用的是wait(0)方法 */
            wait(0);
        }
    } else {
        while (isAlive()) {
            long delay = millis - now;
            if (delay <= 0) {
                break;
            }
            wait(delay);
            now = System.currentTimeMillis() - base;
        }
    }
}
```

### 6.详细说说Thread 中断`interrupt()`方法的实现原理，以及使用场景