package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/14/2022
 * Time: 12:08 PM
 * Email: levinforward@163.com
 */
public class TerminateThread implements Runnable{
    private boolean flag = true;

    private String name;

    public TerminateThread(String name){
        this.name = name;
    }

    @Override
    public void run() {
        int i = 0;
        while (flag){
            System.out.println(name + i++);
        }
    }

    public void terminate(){
        this.flag = false;
    }

    public static void main(String[] args) {
        TerminateThread tt = new TerminateThread("Ada");
        new Thread(tt).start();
        for(int i = 0; i < 99; i++){
            if(i == 88){
                tt.terminate();
                System.out.println("Game over");
            }
            System.out.println("main" + i);
        }
    }
}
