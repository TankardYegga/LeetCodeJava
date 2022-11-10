package java_basic.thread_advanced;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/17/2022
 * Time: 4:28 PM
 * Email: levinforward@163.com
 */
public class HappenBefore {

    private static int a = 10;
    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {

        int times = 10;
        while (times-- >= 0){
            Thread t1 = new Thread(()->{
                a = 5;
                flag = true;
            });

            Thread t2 = new Thread(()->{
                if(flag){
                    a += 5;
                }
                if(a == 10){
                    System.out.println("a is:" + a);
                }
            });

            t1.join();
            t2.join();

            t1.start();
            t2.start();
        }



    }
}
