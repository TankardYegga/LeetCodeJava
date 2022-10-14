package java_basic.thread;

/**
 * Description: main 线程进行礼让
 * Creator: levin
 * Date: 10/14/2022
 * Time: 3:39 PM
 * Email: levinforward@163.com
 */
public class MainYieldThread {

    public static void main(String[] args) {
        new Thread(() -> {
            for(int i = 0; i < 50; i++){
                System.out.println(Thread.currentThread().getName() + i);
            }
        }).start();

        for(int j = 0; j < 100; j++){
            if(j % 5 == 0){
                Thread.yield();
            }
            System.out.println("main" + j);
        }
    }
}
