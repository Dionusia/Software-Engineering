public class PointsBalance {
    private int totalPoints;

    public PointsBalance() {
        totalPoints = 0;
    }

    public void addPoints(int points) {
        totalPoints += points;
    }

    public void subtractPoints(int points) {
        totalPoints -= points;
    }

    //setters-getters
    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }
}