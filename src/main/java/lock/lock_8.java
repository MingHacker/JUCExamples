package lock;

class Phone{
    public synchronized void sendSMS() throws Exception {
        //stop 4s

        System.out.println("------sendSMS");
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println("------sendEmail");
    }

    public void getHello() throws Exception {
        System.out.println("------getHello");
    }
}

public class lock_8 {

}
