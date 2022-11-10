package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/14/2022
 * Time: 9:24 PM
 * Email: levinforward@163.com
 */
class God extends Thread {
    @Override
    public void run(){
        while (true){
            System.out.println("bless me");
        }
    }
}

class OrdinaryPeople extends Thread{
    @Override
    public void run(){
        System.out.println("happy day...");
    }
}

public class DaemonThread {

    public static void main(String[] args) {
        God g = new God();
        g.setDaemon(true); //需要在start之前进行设置
        g.start();

        OrdinaryPeople op = new OrdinaryPeople();
        op.start();

    }

}

