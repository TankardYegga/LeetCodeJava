package java_basic.thread;

import java.util.LinkedList;
import java.util.List;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/15/2022
 * Time: 9:06 PM
 * Email: levinforward@163.com
 */
public class UnsafeThread3 {

    public static void main(String[] args) throws InterruptedException {
        List<String> l = new LinkedList<String>();
        for(int i = 0; i < 20; i++){
            new Thread(()->{
                synchronized (l){
                if(l.contains(Thread.currentThread().getName())){
                    System.out.println("already exists");
                }
                l.add(Thread.currentThread().getName());
                System.out.println(Thread.currentThread().getName());
                }
            }).start();
        }

        Thread.sleep(10000);
        System.out.println("final size:" + l.size());
    }
}

