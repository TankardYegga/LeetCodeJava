package java_basic.thread;

/**
 * Description: 线程不安全，出现负数和重复
 * Creator: levin
 * Date: 10/15/2022
 * Time: 4:24 PM
 * Email: levinforward@163.com
 */
public class UnsafeThread {

    public static void main(String[] args) {

        UnsafeWeb12306 w = new UnsafeWeb12306();

        new Thread(w, "赵丽颖").start();
        new Thread(w, "杨幂").start();
        new Thread(w, "刘诗诗").start();
    }
}


class UnsafeWeb12306 implements Runnable{

    private int ticketNum = 30;
    private boolean flag = true;

    @Override
    public void run() {
        while (flag){
//          test();
//            testWithSync();
//            testWithSyncBlock();
//            testWithSyncBlock2(); //会出现重复值
            testWithSyncBlock3();
        }
    }

    public void test(){
        if(ticketNum <= 0){
            flag = false;
            return;
        }

        //模拟延时
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "-" + ticketNum--);
    }

    //此处方法锁住的是this
    public synchronized void testWithSync(){
        if(ticketNum <= 0){
            flag = false;
            return; //此处的return不可省略，return表示main进程结束，所以某个进程中ticketNum小于等于0时，
        }

        //模拟延时
        try {
            Thread.sleep(100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }

        System.out.println(Thread.currentThread().getName() + "-" + ticketNum--);
    }

    //锁住当前这个不变的对象，改变的只是它的属性
    public void testWithSyncBlock(){
        synchronized (this){
            if(ticketNum <= 0){
                flag = false;
                return;
            }

            //模拟延时
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "-" + ticketNum--);
        }
    }

    //锁住的是ticketNum这个对象，但是这个对象一直在变，
    //也就是ticketNum的地址一直在变
    public void testWithSyncBlock2(){
        synchronized ((Integer)ticketNum){
            if(ticketNum <= 0){
                flag = false;
                return;
            }

            //模拟延时
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "-" + ticketNum--);
        }
    }

    //double-check
    public void testWithSyncBlock3(){
        if(ticketNum <= 0){
            flag = false;
            return;
        }

        synchronized (this){
            if(ticketNum <= 0){
                flag = false;
                return;
            }

            //模拟延时
            try {
                Thread.sleep(100);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            System.out.println(Thread.currentThread().getName() + "-" + ticketNum--);
        }
    }
}
