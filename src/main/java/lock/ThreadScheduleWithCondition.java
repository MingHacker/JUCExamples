package lock;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
class ShareResrouce {
    private int flag = 1;
    private final ReentrantLock lock = new ReentrantLock();
    private Condition condition1 = lock.newCondition();
    private Condition condition2 = lock.newCondition();
    private Condition condition3 = lock.newCondition();

    public void print5(int loop) throws InterruptedException {
        lock.lock();
        try {
            // check
            while (flag != 1) {
                condition1.await();
            }
            // business code
            System.out.println(Thread.currentThread().getName()+ ":: print 05 times, Round: " + loop);
            flag = 2;
            // notify
            condition2.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void print10(int loop) throws InterruptedException {
        lock.lock();
        try {
            // check
            while (flag != 2) {
                condition2.await();
            }
            // business code
            System.out.println(Thread.currentThread().getName()+ ":: print 10 times, Round: " + loop);
            flag = 3;
            // notify
            condition3.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void print20(int loop) throws InterruptedException {
        lock.lock();
        try {
            // check
            while (flag != 3) {
                condition2.await();
            }
            // business code
            System.out.println(Thread.currentThread().getName()+ ":: print 20 times, Round: " + loop);
            flag = 1;
            // notify
            condition1.signalAll();
        } finally {
            lock.unlock();
        }
    }

}
public class ThreadScheduleWithCondition {
    public static void main(String[] args) {
        ShareResrouce shareResrouce = new ShareResrouce();

        new Thread(() -> {
            for (int i = 1; i< 10; i ++){
                try{
                    shareResrouce.print5(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 1; i< 10; i ++){
                try{
                    shareResrouce.print10(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 1; i< 10; i ++){
                try{
                    shareResrouce.print20(i);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "C").start();
    }
}
