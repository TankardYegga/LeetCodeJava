package java_basic.thread_advanced;

/**
 * Description: 多个线程之间相互不干扰
 * Creator: levin
 * Date: 10/17/2022
 * Time: 10:48 PM
 * Email: levinforward@163.com
 */
public class ThreadLocalDemo2 {

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->20);

    public static void main(String[] args) {
        new Thread(new MyLocal(), "A").start();
        new Thread(new MyLocal(), "B").start();
        new Thread(new MyLocal(), "C").start();
    }

    public static class MyLocal implements Runnable{
        @Override
        public void run(){
            Integer val = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + ":" + val);
            threadLocal.set(val - 1);
            val = threadLocal.get();
            System.out.println(Thread.currentThread().getName() + ":" + val);
        }
    }
}
