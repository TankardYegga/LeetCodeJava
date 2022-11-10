package java_basic.thread_advanced;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Description:
 * Creator: levin
 * Date: 10/18/2022
 * Time: 12:00 PM
 * Email: levinforward@163.com
 */
public class ReentrantLockLibraryImpl {

    ReentrantLock rl = new ReentrantLock();

    public void b(){
        rl.lock();
        doSth();
        rl.unlock();
    }

    public void doSth(){
        rl.lock();
        System.out.println("do the unknown");
        rl.unlock();
    }

    public static void main(String[] args) {
        ReentrantLockLibraryImpl rll = new ReentrantLockLibraryImpl();
        rll.b();
    }
}
