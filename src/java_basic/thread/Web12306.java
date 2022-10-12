package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/11/2022
 * Time: 8:23 PM
 * Email: levinforward@163.com
 */
public class Web12306 implements Runnable{
    public int ticketsNum = 10;

    @Override
    public void run(){
        while (true){
            if(ticketsNum < 0){
                break;
            }
            System.out.println(Thread.currentThread().getName() + ticketsNum--);
        }
    }

    public static void main(String[] args) {
        Web12306 wb = new Web12306();
        System.out.println(Thread.currentThread().getName());
        new Thread(wb, "uncle").start();
        new Thread(wb, "aunt").start();
        new Thread(wb, "baby").start();
    }
}
