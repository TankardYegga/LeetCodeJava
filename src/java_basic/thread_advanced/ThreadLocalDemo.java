package java_basic.thread_advanced;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/17/2022
 * Time: 10:00 PM
 * Email: levinforward@163.com
 */
public class ThreadLocalDemo {

//    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<>();

    //匿名内部类
//    private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>(){
//        protected Integer initialValue(){
//            return 200;
//        };
//    };

//    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->{
//        return 200;
//    });

    private static ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(()->200);


    public static void main(String[] args) {
//        System.out.println(threadLocal.get());
        threadLocal.set(300);
        System.out.println(Thread.currentThread().getName() + "->" + threadLocal.get());


        new Thread(new MyRunClass()).start();

        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "->" + threadLocal.get());
    }

    public static class MyRunClass implements Runnable{
        @Override
        public void run(){
            threadLocal.set((int)(Math.random() * 50) + 1);
            System.out.println(Thread.currentThread().getName() + "->" + threadLocal.get());
        }
    };
}
