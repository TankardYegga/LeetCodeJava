package java_basic.thread;

/**
 * Description: yield 礼让线程也就是暂停线程，让线程重新进入就绪态
 * Creator: levin
 * Date: 10/14/2022
 * Time: 3:22 PM
 * Email: levinforward@163.com
 */
public class YieldThread implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "->start");
        Thread.yield();
        System.out.println(Thread.currentThread().getName() + "->end");
    }

    public static void main(String[] args) {
        YieldThread yt = new YieldThread();
        yt.run();
        new Thread(yt).run();
        new Thread(yt).start();
        new Thread(yt).start();

        new Thread(yt, "levin").start();
        new Thread(yt, "lucas").start();
    }
}
