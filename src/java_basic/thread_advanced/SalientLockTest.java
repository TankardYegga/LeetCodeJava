package java_basic.thread_advanced;

/**
 * Description: 不可重入的锁
 * Creator: levin
 * Date: 10/17/2022
 * Time: 11:31 PM
 * Email: levinforward@163.com
 */
public class SalientLockTest {
    public SalientLock sl = new SalientLock();

    public void a() throws InterruptedException {
        sl.lock();
        doSomething();
        sl.unlock();
    }

    public void doSomething() throws InterruptedException {
        sl.lock();
        System.out.println("doing something");
        sl.unlock();
    }

    public static void main(String[] args) throws InterruptedException {
//        new SalientLockTest().a();
        new SalientLockTest().doSomething();
    }
}

class SalientLock{

    private boolean isLocked = false;

    public synchronized void lock() throws InterruptedException {
        while (isLocked){
            wait();
        }

        isLocked = true;
    }

    public synchronized void unlock(){
        isLocked = false;
        notify();
    }
}
