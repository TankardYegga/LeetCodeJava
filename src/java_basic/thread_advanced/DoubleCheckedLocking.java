package java_basic.thread_advanced;

/**
 * Description: JVM
 * Creator: levin
 * Date: 10/17/2022
 * Time: 8:35 PM
 * Email: levinforward@163.com
 */
public class DoubleCheckedLocking implements Runnable{

    private static volatile DoubleCheckedLocking instance;

    //私有构造方法
    private DoubleCheckedLocking(){

    }

    //提供公共接口访问这个对象
    public static DoubleCheckedLocking getInstance(){
        if(null != instance){
            return instance;
        }
        synchronized (DoubleCheckedLocking.class){
            if(null == instance){
                instance = new DoubleCheckedLocking();
            }
        }
        return instance;
    }

    public static DoubleCheckedLocking getInstance2(long time){
        if(null == instance){
            try {
                Thread.sleep(time);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
            instance = new DoubleCheckedLocking();
        }
        return instance;
    }

    public static void main(String[] args) {
//        new Thread(DoubleCheckedLocking.getInstance()).start();

//        Thread t = new Thread(()->{
//            System.out.println(DoubleCheckedLocking.getInstance());
//        });
//        t.start();
//        System.out.println(DoubleCheckedLocking.getInstance());


        //如果不加入同步机制
        Thread t = new Thread(()->{
            System.out.println(DoubleCheckedLocking.getInstance2(500));
        });
        t.start();
        System.out.println(DoubleCheckedLocking.getInstance2(1000));
    }

    @Override
    public void run() {
        System.out.println("Run Run Run");
    }
}
