package jucAid;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierDemo {
    private final static ExecutorService executer = Executors.newFixedThreadPool(10);
    private final static CyclicBarrier barrier = new CyclicBarrier(10);

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            final String name = "玩家" + i;
            executer.execute(() -> {
                try {
                    Thread.sleep(2000);
                    System.out.println(name + "已准备,等待其他玩家准备...");
                    barrier.await();
                    Thread.sleep(1000);
                    System.out.println(name + "已加入游戏");
                } catch (InterruptedException e) {
                    System.out.println(name + "离开游戏");
                } catch (BrokenBarrierException e) {
                    System.out.println(name + "离开游戏");
                }
            });
        }
        executer.shutdown();
    }
}
