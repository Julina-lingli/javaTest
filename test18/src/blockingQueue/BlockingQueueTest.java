package blockingQueue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
    private static BlockingQueue<TransferInformation> queue =
            new ArrayBlockingQueue<>(CommonParaments.QUEUE_SIZE);

    public static void main(String[] args){
        Bank bank = new Bank(CommonParaments.NACCOUNTS, CommonParaments.INITIAL_BALANCE);


        //开启多个线程往queue中写入转账信息命令
        for (int i = 1; i < CommonParaments.PRODUCER_THREAD; i++){
            // int fromAccount = i;
            Runnable producer = new Runnable() {
                @Override
                public void run() {
                    try{
                        int itemID = 0;
                        while(true){
                            int fromAccount = (int) (bank.getLength() * Math.random());
                            int toAccount = (int) (bank.getLength() * Math.random());

                            if(fromAccount == toAccount){
                                continue;
                            }
                            int amount = (int)(CommonParaments.MAX_AMOUNT * Math.random());
                            System.out.println(Thread.currentThread() + "put the queue element " +itemID + ":"+ fromAccount
                                    + "--" + toAccount + "--" + amount);
                            TransferInformation info = new TransferInformation(fromAccount, toAccount, amount, itemID);
                            queue.put(info);
                            Thread.sleep((int) (CommonParaments.PRODUCER_DELAY));
                            // TransferInformation firstElement = queue.peek();
                            // System.out.println("firstElement in the queue: " + firstElement);
                            itemID++;


                        }
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }

                }
            };
            new Thread(producer).start();

        }

        for(int j = 1; j < CommonParaments.CONSUMER_THREAD; j++){
            Runnable consumer = new Runnable() {
                @Override
                public void run() {
                    try {
                        while (true){
                            TransferInformation iterm = queue.take();
                            int fromAccount = iterm.getFrom();
                            int toAccount = iterm.getTo();
                            int amount = iterm.getAmount();
                            int id = iterm.getItemID();
                            bank.transfer(fromAccount, toAccount, amount, id);
                            Thread.sleep((int) (CommonParaments.CONSUMER_DELAY * Math.random()));

                        }


                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }


                }
            };
            new Thread(consumer).start();


        }









    }
}
