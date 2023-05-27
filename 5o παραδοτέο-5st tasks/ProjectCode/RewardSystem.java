import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RewardSystem {
    private List<PointsHistory> pointsHistory;
    private List<String> availableRewards;
    private PointsBalance pointsBalance;
    private Scanner scanner;

    public RewardSystem() {
        pointsHistory = new ArrayList<>();
        availableRewards = new ArrayList<>();
        pointsBalance = new PointsBalance();
        this.scanner = new Scanner(System.in);
    }

    public void rewardsMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("--------------- REWARD SYSTEM ---------------");
            System.out.println("1. Display points");
            System.out.println("2. Available Rewards");
            System.out.println("3. View History");
            System.out.println("4. Back to main menu");
            System.out.println("-------------------------------------------");
            int choice = readChoice();
            switch (choice) {
                case 1:
                    displayPoints();
                    break;
                case 2:
                    displayAvailableRewards();
                    break;
                case 3:
                    viewPointsHistory();
                    break;
                case 4:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void displayPoints() {
        int totalPoints = pointsBalance.getTotalPoints();
        System.out.println("Total Points: " + totalPoints);
        System.out.println();
    }

    private void displayAvailableRewards() {
        System.out.println("Available Rewards:");
        for (int i = 0; i < availableRewards.size(); i++) {
            String reward = availableRewards.get(i);
            System.out.println((i + 1) + ". " + reward);
        }
        System.out.println("0. Back to reward menu");
        System.out.println();
    
        int choice = readChoice();
        if (choice == 0) {
            return;
        }
    
        int rewardIndex = choice - 1;
        if (rewardIndex < 0 || rewardIndex >= availableRewards.size()) {
            System.out.println("Invalid choice.");
            return;
        }
    
        String selectedReward = availableRewards.get(rewardIndex);
        int rewardPoints = getRewardPoints(selectedReward);
    
        int totalPoints = pointsBalance.getTotalPoints();
        if (totalPoints < rewardPoints) {
            System.out.println("You don't have enough points to redeem this reward.");
        } else {
            System.out.println("Congratulations! You have redeemed the reward: " + selectedReward);
            pointsBalance.subtractPoints(rewardPoints);
            System.out.println("Points deducted: " + rewardPoints);
            System.out.println("Remaining points: " + pointsBalance.getTotalPoints());
            System.out.println("Instructions on how to claim the reward will be provided.");
        }
        System.out.println();
    }

    private void viewPointsHistory() {
        System.out.println("Points History:");
        for (PointsHistory history : pointsHistory) {
            System.out.println("Date: " + history.getTransactionDate());
            System.out.println("Type: " + history.getTransactionType());
            System.out.println("Points: " + history.getPoints());
            System.out.println("Description: " + history.getDescription());
            System.out.println();
        }
    }

    private int readChoice() {
        int choice = 0;
        boolean isValidInput = false;
        do {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        } while (!isValidInput);
        scanner.nextLine();
        return choice;
    }

    public void addPointsHistory(PointsHistory history) {
        pointsHistory.add(history);
    }

    public void addReward(String reward, int pointsRequired) {
        availableRewards.add(reward + " (" + pointsRequired + " points)");
    }

    private int getRewardPoints(String reward) {
        // Extract the points required from the reward string to calculate for the redeem of reward
        int startIndex = reward.lastIndexOf("(") + 1;
        int endIndex = reward.lastIndexOf(" points)");
        String pointsStr = reward.substring(startIndex, endIndex);
    
        // Parse the points string to an integer
        int rewardPoints = Integer.parseInt(pointsStr);
    
        return rewardPoints;
    }

    public List<PointsHistory> getPointsHistory() {
        return pointsHistory;
    }

    public List<String> getAvailableRewards() {
        return availableRewards;
    }

    public PointsBalance getPointsBalance() {
        return pointsBalance;
    }

    public void addSampleData(RewardSystem rewardSystem) {
        //sample points history
        PointsHistory history1 = new PointsHistory(new Date(), PointsHistory.TransactionType.EARNING, 50, "Completed challenge");
        PointsHistory history2 = new PointsHistory(new Date(), PointsHistory.TransactionType.EARNING, 20, "Referral bonus");
        PointsHistory history3 = new PointsHistory(new Date(), PointsHistory.TransactionType.SPENDING, -30, "Redeemed reward");
        rewardSystem.addPointsHistory(history1);
        rewardSystem.addPointsHistory(history2);
        rewardSystem.addPointsHistory(history3);

        //available rewards
        rewardSystem.addReward("Discount Coupon in Fitness Equipment", 50);
        rewardSystem.addReward("Gift Card", 100);
        rewardSystem.addReward("Free Diet Program", 75);

        //sample points balance
        PointsBalance pointsBalance = rewardSystem.getPointsBalance();
        pointsBalance.addPoints(100);
    }
}
