package java_basic.thread_advanced;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/17/2022
 * Time: 11:18 PM
 * Email: levinforward@163.com
 */
public class ReentrantLockTest {

    public void test() throws InterruptedException {
        synchronized (this){
            while (true){
                synchronized (this){
                    System.out.println("reentrant lock");
                }

                Thread.sleep(1000);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new ReentrantLockTest().test();
    }
}
