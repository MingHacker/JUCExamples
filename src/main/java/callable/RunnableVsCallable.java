package callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

class MyThread1 implements Runnable{

    @Override
    public void run() {
        System.out.println("hello world");
    }
}

class MyThread2 implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        System.out.println("Callable");
        return 200;
    }
}

public class RunnableVsCallable {

    public static void main(String[] args) throws ExecutionException, InterruptedException {

        FutureTask futureTask1 = new FutureTask(() -> {
            System.out.println(Thread.currentThread().getName() + " come in callable");
            return 1024;
        });
        new Thread(futureTask1, "AA").start();

        while(!futureTask1.isDone()){
            System.out.println("doing Task");
        }

        System.out.println(futureTask1.get());
        System.out.println(futureTask1.get());
        System.out.println(Thread.currentThread().getName());


    }

}
