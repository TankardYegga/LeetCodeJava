package java_basic.thread;

/**
 * Description: Join: 插队线程，阻塞线程
 * Creator: levin
 * Date: 10/14/2022
 * Time: 3:51 PM
 * Email: levinforward@163.com
 */
public class BlockedJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(() -> {
            for(int i = 0; i < 50; i++){
                System.out.println("lambda:" + i);
            }
        });

        t.start();

        for(int k = 0; k < 50; k++){
            if(k == 10){
                t.join(); //main被阻塞
            }
            System.out.println("main:" + k);
        }
    }
}
