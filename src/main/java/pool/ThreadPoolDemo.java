package pool;

import java.util.Collection;
import java.util.List;
import java.util.concurrent.*;

public class ThreadPoolDemo {

    public static void main(String[] args) {

        ExecutorService threadPool1 = Executors.newCachedThreadPool();

        try {
            for (int i = 0; i < 10; i++) {
                threadPool1.execute(() -> {
                    System.out.println(Thread.currentThread().getName() + " doing sth");
                });
            }
        } finally {
            threadPool1.shutdown();
        }

    }

}
