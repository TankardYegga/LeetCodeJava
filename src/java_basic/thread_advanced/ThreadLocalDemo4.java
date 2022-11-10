package java_basic.thread_advanced;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/17/2022
 * Time: 11:05 PM
 * Email: levinforward@163.com
 */
public class ThreadLocalDemo4 {

    private static ThreadLocal<Integer> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) {
        threadLocal.set(555);
        System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());

        new Thread(()->{
            //该线程是由main线程开辟的，所以在本线程没有主动赋值的情况下，会拷贝一份main线程对应的值，
            //但不是拷贝对应的引用
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
            threadLocal.set(9995);
            System.out.println(Thread.currentThread().getName() + ":" + threadLocal.get());
        }).start();
    }

}
