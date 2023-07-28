package lock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;


public class ThreadSafeCollection {
    public static void main(String[] args) {
        List<String> list = Collections.synchronizedList(new ArrayList<>());

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
