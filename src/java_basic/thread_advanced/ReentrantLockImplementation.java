package java_basic.thread_advanced;

/**
 * Description:
 * Creator: levin
 * Date: 10/18/2022
 * Time: 8:36 AM
 * Email: levinforward@163.com
 */
public class ReentrantLockImplementation {
    MyReentrantLock rl = new MyReentrantLock();

    public void a() throws InterruptedException {
        rl.lock();
        System.out.println(rl.getLockedCount());
        doSomething();
        rl.unlock();
        System.out.println(rl.getLockedCount());
    }

    public void doSomething() throws InterruptedException {
        rl.lock();
        System.out.println(rl.getLockedCount());
        System.out.println("doing sth");;
        rl.unlock();
        System.out.println(rl.getLockedCount());
    }

    public static void main(String[] args) throws InterruptedException {
        ReentrantLockImplementation rli = new ReentrantLockImplementation();
        rli.a();
//        rli.doSomething();

        Thread.sleep(1000);
        System.out.println(rli.rl.getLockedCount());
    }

}


class MyReentrantLock {
    private boolean isLocked = false;

    //记录当前锁对象所锁住的线程
    Thread lockedThread = null;

    //记录当前锁对象所锁住的线程数目
    private int lockedCount = 0;

    public MyReentrantLock(){

    }

    public synchronized void lock() throws InterruptedException {
        //判断当前锁是否已经在锁住某个线程
        // 如果已经锁住某个线程，那就必须等待该线程释放当前的锁对象
        // 但是为了实现可重入锁，
        // 需要判断本次进行上锁的对象也就是调用lock方法的当前进程 是否是同一个进程
        // 如果是同一个进程，则可以释放当前锁，不然就要继续等待锁被释放
        Thread t = Thread.currentThread();//获取调用lock方法的进程
        System.out.println(t.getName() + " is getting locked");
        while (isLocked && lockedThread != t){
            wait();
        }

        isLocked = true;
        lockedCount += 1;

        lockedThread = t;
    }

    public synchronized void unlock(){
        //调用unlock方法的线程试图释放当前锁
        //************************
        // 正常情况下的操作：
        // 如果调用unlock的进程恰好是被锁住的进程，
        // 则将锁的标记状态更改为false，表示当前锁已经不再被任何进程占用
        // 如果调用unlock的入口进程不是被锁住的进程，
        // 则当前的unlock方法调用无效
        // 【增加这一条件判断是为了避免调用该锁的地方出现逻辑上的错误，提高了锁机制的鲁棒性】
        //***********************************************
        // 在可重入锁情况下：只有同一个进程对该锁的多次锁操作都被释放，该锁才被真正释放
        // 每一次调用unlock，lockedCount减小1，直到lockedCount=0该锁才能被释放
        System.out.println(Thread.currentThread().getName() + " is getting unlocked");
        if (Thread.currentThread() == lockedThread){
            lockedCount--;
            if(lockedCount == 0){
                isLocked = false;
                notify();
                lockedThread = null;
            }
        }
    }

    public int getLockedCount(){
        return lockedCount;
    }

    public void setLockedCount(int lockedCount){
        this.lockedCount = lockedCount;
    }
}