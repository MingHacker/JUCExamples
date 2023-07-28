package lock;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;


public class ThreadSafeCopyOnWriteArrayList {
    public static void main(String[] args) {

        List<String> list = new CopyOnWriteArrayList<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // add content to collection
                list.add(UUID.randomUUID().toString().substring(0,4));
                // read content from collection
                System.out.println(list);
            }, String.valueOf(i)).start();
        }


        Set<String> set = new CopyOnWriteArraySet<>();

        for (int i = 0; i < 30; i++) {
            new Thread(() -> {
                // add content to collection
                set.add(UUID.randomUUID().toString().substring(0,4));
                // read content from collection
                System.out.println(set);
            }, String.valueOf(i)).start();
        }

        Map<String, String> mp = new ConcurrentHashMap();

        for (int i = 0; i < 30; i++) {
            int finalI = i;
            new Thread(() -> {
                // add content to collection
                mp.put(String.valueOf(finalI), UUID.randomUUID().toString().substring(0,4));
                // read content from collection
                System.out.println(mp);
            }, String.valueOf(i)).start();
        }
    }
}
