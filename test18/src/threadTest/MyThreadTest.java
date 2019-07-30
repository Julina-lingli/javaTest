package threadTest;

import java.io.InterruptedIOException;

public class MyThreadTest {
    public static final int NACCOUNTS = 10;
    public static final int INITIAL_BALANCE = 1000;
    public static final int MAX_AMOUNT = 1000;
    public static final int DELAY = 10;

    public static void main(String[] args){
        Bank bank = new Bank(NACCOUNTS, INITIAL_BALANCE);
        for (int i = 0; i < NACCOUNTS; i++){
            int fromAccount = i;
            System.out.println("" + i + "---Thread count:" + Thread.currentThread().getThreadGroup().activeCount());
            //new Thread()创建一个Thread类的对象，new Runnable(){}创建一个实现Runnable接口的匿名内部类的对象，
            // 并在{}中实现接口中的方法
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try{

                        while (true){
                            int toAccount = (int) (bank.getSize() * Math.random());
                            int amount = (int)(MAX_AMOUNT * Math.random());
                            // System.out.println(Thread.currentThread() + "--state:" + Thread.currentThread().getState());
                            bank.transfer(fromAccount, toAccount, amount);
                            // System.out.println("---run return--" + Thread.currentThread().getName());
                            Thread.sleep((int) (DELAY * Math.random()));
                        }

                    }catch (InterruptedException e){
                        e.printStackTrace();

                    }
                    /**
                    catch (MyException e){
                        System.out.println("------------throw---" + Thread.currentThread().getName());
                        e.printStackTrace();
                    }
                     */



                }
            }).start();

            // System.out.println("-----------" + i + "---Thread count:" + Thread.currentThread().getThreadGroup().activeCount());
        }



    }
}
