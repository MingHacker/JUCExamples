package lock8;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

class Phone {

    public static synchronized void sendEmail() {
        try {
            TimeUnit.MILLISECONDS.sleep(4000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("--> send email");
    }

    public  synchronized void sendSMS() {
        System.out.println("--> send message");
    }

    public void hello(){
        System.out.println("hello");
    }

}


public class Lock8Demo {
    public static void main(String[] args) {
        Phone phone = new Phone();
        Phone phone2 = new Phone();

        new Thread(() -> {
            phone.sendEmail();
        }, "a").start();

        // ensure a thread start first
        try {
            TimeUnit.MILLISECONDS.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        new Thread(() -> {
            //phone.sendSMS();
            //phone.hello();
            phone2.sendSMS();
        }, "b").start();

    }
}
