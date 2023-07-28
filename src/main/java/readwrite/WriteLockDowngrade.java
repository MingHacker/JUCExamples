package readwrite;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class WriteLockDowngrade
{
    public static void main(String[] args) {
        ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock =    rwLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = rwLock.writeLock();


        // Get the write lock
        writeLock.lock();
        System.out.println("writing sth");
        // Get the read lock
        readLock.lock();
        System.out.println("reading sth");
        // Release the write lock
        writeLock.unlock();
        // Release the read lock
        readLock.unlock();
    }
}
