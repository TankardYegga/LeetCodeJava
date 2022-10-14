package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/14/2022
 * Time: 4:16 PM
 * Email: levinforward@163.com
 */
public class BlockedJoin2 {

    public static void main(String[] args) {
        new Thread(new Uncle()).start();
    }
}


class Uncle extends Thread{
    @Override
    public void run(){
        System.out.println("levin, get me a cup of star buck");
        System.out.println("This is the money");
        Thread levinBoy = new Thread(new Levin());
        levinBoy.start();

        try {
            levinBoy.join();
            System.out.println("Good job, boy");
            System.out.println("Give you a hug");
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("levin is missing");
        }
    }
}

class Levin extends Thread{
    @Override
    public void run(){
        System.out.println("Uncle, I know");
        System.out.println("I get out");
        System.out.println("I came across a nice book store");
        for(int i = 0; i < 10; i++){
            System.out.println("Read book for " + i + "minutes");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("I buy a bottle of coffee at the starbuck");
    }
}