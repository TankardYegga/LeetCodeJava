package java_basic.thread_advanced;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/17/2022
 * Time: 5:01 PM
 * Email: levinforward@163.com
 */
public class VolatileTest {
    private volatile static int num = 0;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while (num == 0){

            }
        }).start();

        Thread.sleep(1000);
        num = 1;
    }
}
