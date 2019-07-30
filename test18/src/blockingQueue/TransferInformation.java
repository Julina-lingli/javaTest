package blockingQueue;

public class TransferInformation {
    private int from;
    private int to;
    private int amount;
    private int itemID;

    public TransferInformation(){

    }
    public TransferInformation(int from, int to, int amount, int itemID){
        this.from = from;
        this.to = to;
        this.amount = amount;
        this.itemID = itemID;

    }
    public int getAmount() {
        return amount;
    }

    public int getTo() {
        return to;
    }

    public int getFrom() {
        return from;
    }

    public int getItemID() {
        return itemID;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTo(int to) {
        this.to = to;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return getClass().getName() + "id:" + itemID + "--amount: " + amount + " from:" + from
                + " to:" + to;
    }
}
