package java_basic.thread;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/12/2022
 * Time: 4:18 PM
 * Email: levinforward@163.com
 */
public class RacerGame implements Runnable{

    public String winner;

    @Override
    public void run() {
        for (int time = 1; time <= 100; time++) {
            if(Thread.currentThread().getName().equals("福贝") && time % 10 == 0){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println(Thread.currentThread().getName() + ":" + time);
            boolean flag = isGameEnding(time);
            //break会使得当前线程结束
            //也能让另外一个线程在没有跑完时就停止
            if(flag){
                break;
            }
        }
    }

    public boolean isGameEnding(int time){
        //当一个线程检测当另外一个线程已经使得winner不为空，
        // 或者说已经运行完了的时候，就可以停止运行该线程了
        if(winner != null){
            return true;
        }else {
            if(time == 100){
                winner = Thread.currentThread().getName();
                System.out.println("winner is:" + winner);
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        RacerGame rg = new RacerGame();
        new Thread(rg, "福娃").start();
        new Thread(rg, "福贝").start();
    }
}
