package java_basic.thread;

/**
 * Description: 取值范围是1-10
 *
 * Creator: levin
 * Date: 10/14/2022
 * Time: 8:58 PM
 * Email: levinforward@163.com
 */
public class PriorityTask {

    public static void main(String[] args) {
        System.out.println(Thread.MIN_PRIORITY);
        System.out.println(Thread.MAX_PRIORITY);
        System.out.println(Thread.NORM_PRIORITY);
        System.out.println(Thread.currentThread().getPriority());

        MyPriority mp = new MyPriority();

        Thread th1 = new Thread(mp, "puma");
        Thread th2 = new Thread(mp, "回力");
        Thread th3 = new Thread(mp, "Nike");
        Thread th4 = new Thread(mp, "鸿星尔克");
        Thread th5 = new Thread(mp, "adidas");
        Thread th6 = new Thread(mp, "科比");

        //优先级需要在线程启动之前进行设置
        th1.setPriority(Thread.MAX_PRIORITY);
        th2.setPriority(Thread.MIN_PRIORITY);
        th3.setPriority(Thread.NORM_PRIORITY);
        th4.setPriority(7);
        th5.setPriority(8);
        th6.setPriority(3);
        th1.start();
        th2.start();
        th3.start();
        th4.start();
        th5.start();
        th6.start();
    }
}


class MyPriority implements Runnable{

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + ":" +
                Thread.currentThread().getPriority());
    }
}

