package lock;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.Vector;

public class ThreadSafeVector {
    public static void main(String[] args) {
        List<String> list = new Vector<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // add content to collection
                list.add(UUID.randomUUID().toString().substring(0,4));
                // read content from collection
                System.out.println(list);
            }, String.valueOf(i)).start();
        }
    }
}
