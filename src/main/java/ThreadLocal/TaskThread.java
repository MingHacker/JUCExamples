package ThreadLocal;

import java.lang.reflect.Method;

public class TaskThread<T> extends Thread {

    private T t;

    public TaskThread(String threadName, T t) {
        this.setName(threadName);
        this.t = t;
    }

    @Override
    public void run(){
        for (int i = 0; i < 2; i++) {

            try {
                Class[] argsClass = new Class[0];
                Method method = t.getClass().getMethod("getUniqueId", argsClass);
                int value = (int) method.invoke(t);
                System.out.println("thread[" + Thread.currentThread().getName() + "] --> uniqueId[" + value + "]");
            } catch (Exception e) {
                continue;
            }
        }
    }

}
