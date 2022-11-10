package java_basic.thread;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/17/2022
 * Time: 2:26 PM
 * Email: levinforward@163.com
 */
public class TimeTest1 {

    public static void main(String[] args) {
        Timer t = new Timer();
//        t.schedule(new MyTask(), 1000);
//        t.schedule(new MyTask(), 500, 1000); //从0.5s后每隔1s就运行1次
        Calendar cal = new GregorianCalendar(2099, 11, 18, 5, 26, 10);
        t.schedule(new MyTask(), cal.getTime(), 1000);
    }
}


class MyTask extends TimerTask{

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            System.out.println("Leetcode");
        }
        System.out.println("--end--");
    }
}
