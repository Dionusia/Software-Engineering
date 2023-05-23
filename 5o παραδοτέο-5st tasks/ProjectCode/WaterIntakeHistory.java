import java.util.ArrayList;
import java.util.List;

public class WaterIntakeHistory {
    private List<Integer> intakeHistory;

    public WaterIntakeHistory() {
        intakeHistory = new ArrayList<>();
    }

    public void addIntake(int amount) {
        intakeHistory.add(amount);
    }

    public List<Integer> getIntakeHistory() {
        return intakeHistory;
    }

    public void displayIntakeHistory() {
        if (intakeHistory.isEmpty()) {
            System.out.println("No intake history available.");
        } else {
            System.out.println("Water Intake History:");
            for (int i = 0; i < intakeHistory.size(); i++) {
                int intake = intakeHistory.get(i);
                System.out.println((i + 1) + ". " + intake + "ml");
            }
        }
    }

    public void deleteIntakeFromHistory(int index) {
        if (index >= 1 && index <= intakeHistory.size()) {
            int deletedIntake = intakeHistory.remove(index - 1);
            System.out.println("Intake of " + deletedIntake + "ml has been deleted from the history.");
        } else {
            System.out.println("Invalid index.");
        }
    }

    public void modifyIntakeInHistory(int index, int newAmount) {
        if (index >= 1 && index < intakeHistory.size()) {
            int oldAmount = intakeHistory.set(index, newAmount);
            System.out.println("Intake of " + oldAmount + "ml has been modified to " + newAmount + "ml in the history.");
        } else {
            System.out.println("Invalid index.");
        }
    }
}