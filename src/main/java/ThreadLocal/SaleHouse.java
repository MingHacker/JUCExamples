package ThreadLocal;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

class Sales {

    AtomicInteger totalSale;

    ThreadLocal<Integer> saleCount = ThreadLocal.withInitial(() -> 0);

    Sales() {
        totalSale = new AtomicInteger();
    }

    void saleOneHouse() {
        saleCount.set(saleCount.get() + 1);
        totalSale.getAndIncrement();
    }
}


public class SaleHouse {

    public static void main(String[] args) {

        Sales sales = new Sales();

        for (int i = 0; i < 5; i++) {
            new Thread(() -> {
                try {
                    int saleNumber = new Random().nextInt(5) + 1;
                    for (int j = 0; j < saleNumber; j++) {
                        sales.saleOneHouse();
                    }
                    System.out.println(sales.saleCount.get());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    sales.saleCount.remove();
                }
                System.out.println("value after remove: " + sales.saleCount.get());

            }, String.valueOf(i)).start();
        }

        try {
            TimeUnit.MILLISECONDS.sleep(300);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println(Thread.currentThread().getName() + " sale houses:" + sales.totalSale.get());

    }

}
