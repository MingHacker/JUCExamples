package lock;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {

    public static void main(String[] args) {

        ReentrantLock lock1 = new ReentrantLock();
        ReentrantLock lock2 = new ReentrantLock();

        Object a = new Object();
        Object b = new Object();

        new Thread(() -> {
            synchronized (a){
                System.out.println("get lock a, trying to get lock b");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (b) {
                    System.out.println("get lock b!!");
                }
            }
        }).start();

        new Thread(() -> {
            synchronized (b){
                System.out.println("get lock b, trying to get lock a");
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                synchronized (a) {
                    System.out.println("get lock a!!");
                }
            }
        }).start();

        new Thread(() -> {
            lock1.lock();
            try {
                System.out.println("get lock1 trying to get lock2");
                TimeUnit.SECONDS.sleep(1);
                lock2.lock();
                try{
                    System.out.println("get lock2!!");
                }finally {
                    lock2.unlock();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock1.unlock();
            }
        }).start();

        new Thread(() -> {
            lock2.lock();
            try {
                System.out.println("get lock2 trying to get lock1");
                TimeUnit.SECONDS.sleep(1);
                lock1.lock();
                try{
                    System.out.println("get lock1!!");
                }finally {
                    lock1.unlock();
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                lock2.unlock();
            }
        }).start();
    }

}
