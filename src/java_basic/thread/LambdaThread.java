package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/13/2022
 * Time: 11:29 AM
 * Email: levinforward@163.com
 */
public class LambdaThread {

    static class Test1 implements Runnable{
        @Override
        public void run(){
            System.out.println("Test1");
        }
    }

    public static void main(String[] args) {
        Test1 t1 = new Test1();
        new Thread(new Test1()).start();

        class Test2 implements Runnable{
            @Override
            public void run(){
                System.out.println("Test2");
            }
        }

        new Thread(new Test2()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Test3");
            }
        }).start();


        // jdk简化lambda
        new Thread(()-> {
            System.out.println("Test4");
        }).start();
    }
}
