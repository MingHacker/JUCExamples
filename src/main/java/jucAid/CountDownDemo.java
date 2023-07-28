package jucAid;


import java.util.concurrent.CountDownLatch;

public class CountDownDemo {
    // 6 RPC requests, wait for and then summarize the results

    //create  CountDownlatch, set the initial value


    public static void main(String[] args) throws InterruptedException {

        CountDownLatch countDownLatch = new CountDownLatch(6);

        for (int i = 0; i < 6; i++) {
            new Thread(() -> {
                System.out.println(Thread.currentThread().getName() + " RPC response arrived");

                countDownLatch.countDown();

            }, String.valueOf(i)).start();
        }

        countDownLatch.await();

        System.out.println(Thread.currentThread().getName() + " get all the responses, sending out");
    }

}
