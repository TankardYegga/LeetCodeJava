package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/11/2022
 * Time: 9:03 AM
 * Email: levinforward@163.com
 */
public class StartThread extends Thread{

    @Override
    public void run(){
        int[] numbers = {0, 1, 2, 3, 4, 5};
        for(Integer num: numbers){
            System.out.println("I am coding");
        }
    }

    public static void main(String[] args) {
        StartThread st = new StartThread();
//        st.run(); // 普通方法执行

        st.start(); //启动线程

        for(int i = 0; i < 20; i++){
            System.out.println("I am listening to music");
        }
    }
}
