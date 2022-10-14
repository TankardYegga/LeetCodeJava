package java_basic.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/14/2022
 * Time: 2:34 PM
 * Email: levinforward@163.com
 */
public class BlockedSleep {

    public static void main(String[] args) throws InterruptedException {
//        int num = 20;
//        while (true){
//            Thread.sleep(1000);
//            System.out.println(num--);
//        }

        //倒计时功能
        //获取当前时间 + 10s
        Date currentTime = new Date(System.currentTimeMillis());
        System.out.println(new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss").format(currentTime));

        Date endTime = new Date(System.currentTimeMillis() + 1000 * 10);
        long time = endTime.getTime();
        System.out.println("time is :" + time);
        while (true){
            System.out.println(new SimpleDateFormat("yyyy:MM:dd:hh:mm:ss").format(endTime));
            Thread.sleep(1000);
            endTime = new Date(endTime.getTime() - 1000);

            if((time - 10 * 1000) > endTime.getTime()){
                break;
            }
        }
    }
}
