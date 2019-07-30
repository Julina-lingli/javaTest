package blockingQueue;

import javax.swing.*;
import java.util.Arrays;

public class Bank {
    private int[] accounts;
    private static final int TOTALBALANCE = CommonParaments.INITIAL_BALANCE * CommonParaments.NACCOUNTS;

    /**
     *
     */
    public Bank(){

    }

    /**
     *
     * @param n
     * @param initialBalance
     */
    public Bank(int n, int initialBalance){
        this.accounts = new int[n];
        Arrays.fill(this.accounts, initialBalance);
    }

    /**
     *
     * @param from
     * @param to
     * @param amount
     */
    public void transfer(int from, int to, int amount, int id){
        System.out.println(Thread.currentThread() + "id:" + id);
        if(accounts[from] < amount)
            return;
        accounts[from] -= amount;
        accounts[to] += amount;
        System.out.println(Thread.currentThread() +
                "--amount:" + amount + " from:"+ from + " to:" + to);
        int totalBalance = getTotal();
        if(totalBalance != TOTALBALANCE){
            throw new TotalBalanceError(Thread.currentThread()+ ":TotalBalance is " + totalBalance);
        }
        System.out.println(Thread.currentThread()+ ":TotalBalance is " + totalBalance);
    }

    /**
     *
     * @return
     */
    public int getTotal(){
        int sum = 0;
        for(int a : accounts){
            sum += a;
        }
        return sum;
    }

    public int getLength(){
        return accounts.length;
    }
}
