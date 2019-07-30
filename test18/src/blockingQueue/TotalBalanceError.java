package blockingQueue;

public class TotalBalanceError extends RuntimeException{
    public TotalBalanceError(){
        super();
    }

    public TotalBalanceError(String message){
        super(message);
    }

}
