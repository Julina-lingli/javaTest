package blockingQueue;

public class CommonParaments {
    public static final int NACCOUNTS = 10;
    public static final int QUEUE_SIZE = 10;
    public static final int INITIAL_BALANCE = 1000;
    public static final int MAX_AMOUNT = 1500;

    public static final int PRODUCER_THREAD;
    public static final int CONSUMER_THREAD;
    public static final int PRODUCER_DELAY = 10;
    public static final int CONSUMER_DELAY = 1;

    static {
        PRODUCER_THREAD = 3;
        CONSUMER_THREAD = 10;
    }


}
