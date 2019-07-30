package synchronizedTest;

import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class SynchronizedMethod {

    public static void producerOpt(ArrayDeque<Integer> queue, Integer ele) throws Exception{
        synchronized (queue){
            while (queue.size() == CommonPara.MAX_SIZE){
                queue.wait();
                /**
                try {
                    queue.wait();
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
                 */
            }
            System.out.println("producer thread:" + Thread.currentThread() + " ele:" + ele.toString());
            queue.add(ele);
            queue.notifyAll();
        }
    }

}
