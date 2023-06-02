import java.util.Date;

class PointsHistory extends RewardSystem {
    private Date transactionDate;
    private TransactionType transactionType;
    private int points;
    private String description;

    public PointsHistory(Date transactionDate, TransactionType transactionType, int points, String description) {
        this.transactionDate = transactionDate;
        this.transactionType = transactionType;
        this.points = points;
        this.description = description;
    }

    //getters
    public Date getTransactionDate() {
        return transactionDate;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public int getPoints() {
        return points;
    }

    public String getDescription() {
        return description;
    }

    enum TransactionType {
        EARNING, SPENDING
    }
}