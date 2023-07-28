package sync;


// Create a resource class
class Share {
    //init
    private int number = 0;

    // + 1
    public synchronized void increase() throws InterruptedException {
        while (number != 0) {
            this.wait();
        }

        number++;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        this.notifyAll();
        return;

    }

    // -1
    public synchronized void decrease() throws InterruptedException {
        while (number != 1) {
            this.wait();
        }
        number--;
        System.out.println(Thread.currentThread().getName() + "::" + number);
        this.notifyAll();
        return;

    }
}

public class ThreadDemo1 {
    public static void main(String[] args) {
        Share share = new Share();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    share.increase();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "A").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    share.decrease();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "B").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    share.decrease();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "C").start();

        new Thread(() -> {
            for (int i = 0; i < 40; i++) {
                try {
                    share.increase();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }, "D").start();
    }
}
