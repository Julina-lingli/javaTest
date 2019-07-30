package synchronizedTest;

import java.util.ArrayDeque;

public class SynchronizedTest {
    private static int ELEMENT_INITAL = 100;
    private static ArrayDeque<Integer> queue = new ArrayDeque<>(CommonPara.MAX_SIZE);
    public static class RunnableC implements Runnable{

        @Override
        public void run() {
            synchronized (queue) {
                while(true) {
                    while (queue.size() == 0) {
                        try {
                            queue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                    Integer ele = queue.poll();
                    System.out.println("consumer thread:" + Thread.currentThread() + " ele:" + ele.toString());
                    queue.notifyAll();
                }
            }



        }
    }

    public static void main(String[] args){

        //producer
        Thread threadP = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    int ele = (int) (ELEMENT_INITAL * Math.random());
                    try {
                        SynchronizedMethod.producerOpt(queue, new Integer(ele));
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }
        });
        threadP.start();

        //consumer
        Thread threadC = new Thread(new RunnableC());
        threadC.start();


    }
}
