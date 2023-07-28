package lock;

import java.util.*;

public class ThreadUnsafe {
    public static void main(String[] args) {

        /**
         * List
         */
        List<String> list = new ArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // add content to collection
                list.add(UUID.randomUUID().toString().substring(0,4));
                // read content from collection
                System.out.println(list);
            }, String.valueOf(i)).start();
        }

        /**
         * hashset
         */
        Set set = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // add content to collection
                set.add(UUID.randomUUID().toString().substring(0,4));
                // read content from collection
                System.out.println(set);
            }, String.valueOf(i)).start();
        }
    }
}
