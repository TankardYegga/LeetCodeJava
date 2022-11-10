package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/14/2022
 * Time: 10:48 PM
 * Email: levinforward@163.com
 */
public class ThreadInfo {

    public static void main(String[] args) {

        System.out.println(Thread.currentThread().isAlive());

        Info in = new Info("XinFu");
        Thread t = new Thread(in);
        t.setName("AGoodName");
        t.start();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        System.out.println(t.isAlive());
    }
}

class Info implements Runnable{
    private String innerName;

    public Info(String innerName){
        this.innerName = innerName;
    }

    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "->" + innerName);
    }
}