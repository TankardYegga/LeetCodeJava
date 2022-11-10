package java_basic.thread_advanced;

/**
 * Description: threadlocal是从哪个线程进入的，就对应哪个线程
 * Creator: levin
 * Date: 10/17/2022
 * Time: 10:55 PM
 * Email: levinforward@163.com
 */
public class ThreadLocalDemo3 {

    public static void main(String[] args) {
        threadLocal.set(99);
        new Thread(new MyRun()).start();
    }

    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
        protected Integer initialValue(){
            return 20;
        }
    };

    public static class MyRun implements Runnable{

        public MyRun(){
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }

        @Override
        public void run(){
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }
    }
}
