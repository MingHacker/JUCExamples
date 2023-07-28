package readwrite;


import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

class MyCache {
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private volatile Map<String, Object> map = new HashMap<>();

    public void put(String key, Object value) throws InterruptedException {
        lock.writeLock().lock();
        try{
            System.out.println(Thread.currentThread().getName() + " writing " + key);
            TimeUnit.MICROSECONDS.sleep(100);
            map.put(key, value);
            System.out.println(Thread.currentThread().getName() + " finished writing  " + key);
        }finally {
            lock.writeLock().unlock();
        }
    }

    public Object get(String key) {
        lock.readLock().lock();
        Object o;
        try {
            System.out.println(Thread.currentThread().getName() + " reading " + key);
            o = map.get(key);
            System.out.println(Thread.currentThread().getName() + " finsihed reading " + key);
        } finally {
            lock.readLock().unlock();
        }
        return o;
    }
}

public class ReadWriteLockDemo {

    public static void main(String[] args) {
        MyCache myCache = new MyCache();
        for (int i = 0; i < 5; i++) {
            int finalI = i;
            String finalI1 = UUID.randomUUID().toString();
            new Thread(() -> {
                try {
                    myCache.put(String.valueOf(finalI), finalI1);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }, String.valueOf(i)).start();
        }


        for (int i = 0; i < 5; i++) {
            int finalI = i;
            new Thread(() -> {
                System.out.println(myCache.get(String.valueOf(finalI)));
            }, String.valueOf(i)).start();
        }
    }
}
