package threadTest;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Bank {
    private  final  int[] accounts;
    private ReentrantLock bankLock;
    private Condition sufficientFunds;

    /**
     *  construct
     * @param n
     * @param initialBalance
     */
    public Bank(int n, int initialBalance){
        accounts = new int[n];
        Arrays.fill(accounts, initialBalance);
        bankLock = new ReentrantLock();
        sufficientFunds = bankLock.newCondition();
    }

    /**
     * transfer money from one account to another
     * @param from
     * @param to
     * @param amount
     */
    public void transfer(int from, int to, int amount) throws InterruptedException{
        bankLock.lock();
        try {
            System.out.println(Thread.currentThread() + "--transfer lock--" + bankLock.getHoldCount());
            // System.out.println(Thread.currentThread() + "--transfer lock wait--" + bankLock.getQueueLength());
            /* 对获得锁但没有做有用工作的线程进行管理
            if (accounts[from] < amount) {
                System.out.println("--transfer return--" + Thread.currentThread().getName());
                return;
                // throw new MyException(Thread.currentThread() + ":amount is not enough!");
            }
             */
            while (accounts[from] < amount){
                sufficientFunds.await();
                System.out.println(Thread.currentThread() + "--state:" + Thread.currentThread().getState());
            }

            accounts[from] -= amount;
            accounts[to] += amount;
            sufficientFunds.signalAll();
            System.out.printf("%s--amount:%d from %d to %d\n", Thread.currentThread().getName(), amount, from, to);
            int totalBalance = getTotalBalance();
            System.out.println(Thread.currentThread() + "--after lock--" + bankLock.getHoldCount());
            System.out.printf(Thread.currentThread()+"Total balance: %d\n", totalBalance);
            if (totalBalance != 1000)
                throw new MyException(Thread.currentThread()+ ":TotalBalance is " + totalBalance);


        }
        finally {
            bankLock.unlock();
            System.out.println(Thread.currentThread() + "--lock--" + bankLock.getHoldCount());
        }

    }

    /**
     *
     * @return
     */
    public int getTotalBalance(){
        bankLock.lock();
        try{
            // System.out.println(Thread.currentThread() + "--getTotalBalance--state:" + Thread.currentThread().getState());
            System.out.println(Thread.currentThread() + "--getTotalBalance lock--" + bankLock.getHoldCount());
            int sum = 0;
            for (int a : accounts){
                sum += a;
            }
            return sum;
        }
        finally {
            bankLock.unlock();
        }

    }

    public int getSize(){
        return accounts.length;
    }





}
