package lock;
import java.util.concurrent.locks.ReentrantLock;

class LTicket {
    private int number = 30;

    //reentrantlock
    private final ReentrantLock lock = new ReentrantLock(true);

    public void sale() {
        lock.lock();
        try {
            if (number > 0) {
                System.out.println(Thread.currentThread().getName() + ": Sale 1 ticket, left: " + --number);
            }
        } finally {
            lock.unlock();
        }
    }
}

public class LSaleTicket {

    public static void main(String[] args) {

        LTicket ticket = new LTicket();

        //create 3 threads:

        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "A");

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "B");

        Thread thread3 = new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                ticket.sale();
            }
        }, "C");

        thread1.start();
        thread2.start();
        thread3.start();
    }

}
