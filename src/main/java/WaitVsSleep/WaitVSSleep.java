package WaitVsSleep;

import org.jetbrains.annotations.NotNull;

public class WaitVSSleep {
    public static void main(String[] args) {
        Printer p = new Printer();
        TestThread t1 = new TestThread(p, "Geek");
        TestThread t2 = new TestThread(p, "For You");
        t1.start();
        t2.start();
    }
}

class Printer {

    Printer() {

    }

    synchronized public void display(@NotNull String s) {
        for (int i = 0; i < s.length(); i++) {

            System.out.print(s.charAt(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
        }

    }
}

class TestThread extends Thread {

    Printer printer;
    public String s;

    public TestThread(Printer printer, String s){
        this.printer = printer;
        this.s = s;
    }

    public void run(){
        printer.display(s);
    }

}


