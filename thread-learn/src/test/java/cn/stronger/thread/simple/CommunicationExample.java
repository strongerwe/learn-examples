package cn.stronger.thread.simple;


/**
 * @author qiang.w
 * @version release-1.0.0
 * @class CommunicationExample.class
 * @department Platform R&D
 * @date 2025/4/3
 * @desc do what?
 */
public class CommunicationExample {

    public static void main(String[] args) {
        SharedResource sharedResource = new SharedResource();

        /* 生产 */
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    sharedResource.produce(i);
                    System.out.println("生产：" + i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        /* 消费 */
        new Thread(() -> {
            for (int i = 1; i <= 5; i++) {
                try {
                    int val = sharedResource.consume();
                    System.out.println("消费：" + val);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
