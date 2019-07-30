package safeCollection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class ConcurrentSafeCollectionTest {
    static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();
    public static void main(String[] args){
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list1 = new CopyOnWriteArrayList<>();
                list1.add("device1");
                list1.add("device2");
                System.out.println("thread1 start---" + Thread.currentThread() + ":" + map);
                map.put("topic1", list1);
                System.out.println(Thread.currentThread() + ":" + map);
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list1 = new CopyOnWriteArrayList<>();
                list1.add("device11");
                list1.add("device22");
                System.out.println("thread2 start---" + Thread.currentThread() + ":" + map);
                try {
                    System.out.println("thread2 is  in sleep");
                    Thread.sleep(1000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                map.put("topic1", list1);
                System.out.println(Thread.currentThread() + ":" + map);
            }
        });

        Thread thread3 = new Thread(new Runnable() {
            @Override
            public void run() {
                List<String> list1 = new CopyOnWriteArrayList<>();
                list1.add("device111");
                list1.add("device222");
                System.out.println("thread3 start---" + Thread.currentThread() + ":" + map);
                map.put("topic2", list1);
                System.out.println(Thread.currentThread() + ":" + map);
            }
        });

        //start thread
        thread1.start();


        thread2.start();
        thread3.start();

    }
}
