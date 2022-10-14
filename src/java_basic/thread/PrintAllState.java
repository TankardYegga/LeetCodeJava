package java_basic.thread;
import java.lang.Thread.State;
/**
 * Description:
 * Creator: levin
 * Date: 10/14/2022
 * Time: 5:03 PM
 * Email: levinforward@163.com
 */
public class PrintAllState {

    public static void main(String[] args)  {
        Thread t = new Thread(()->{
            for(int i = 0; i < 10; i++){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("hhh" + i);
            }
        });

        State s = t.getState();
        System.out.println(s);

        t.start();
        s = t.getState();
        System.out.println(s);

//        while (s != State.TERMINATED){
//            try {
//                Thread.sleep(100);
//            }catch (InterruptedException e){
//                e.printStackTrace();
//            }
//            s = t.getState();
//            System.out.println("cur state:" + s);
//        }

        while (true){
            int num = Thread.activeCount();//计算活动线程数
            System.out.println("num is:" + num) ;
            if(num == 2){
                break;
            }
            try {
                Thread.sleep(1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }

            s = t.getState();
            System.out.println("cur state:" + s);
        }
    }
}
