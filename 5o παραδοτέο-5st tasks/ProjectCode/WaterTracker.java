import java.util.InputMismatchException;
import java.util.Scanner;

public class WaterTracker {
    private int targetIntake;
    private int currentIntake;
    private Scanner scanner;
    private Client client;
    private WaterIntakeHistory intakeHistory;

    public WaterTracker(int targetIntake, Client client) {
        this.targetIntake = targetIntake;
        this.currentIntake = 0;
        this.scanner = new Scanner(System.in);
        this.client = client;
        this.intakeHistory = new WaterIntakeHistory();
    }

    public void WaterMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("--------------- WATER TRACKER ---------------");
            System.out.println("1. Display intake of the day");
            System.out.println("2. Import water");
            System.out.println("3. View History");
            System.out.println("4. Back to main menu");
            System.out.println("---------------------------------------------");
            int choice = readChoice();
            switch (choice) {
                case 1:
                    displayIntake();
                    break;
                case 2:
                    importWater();
                    break;
                case 3:
                    viewHistory();
                    break;
                case 4:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
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

    public void addWaterIntake(int amount) {
        currentIntake += amount;
        intakeHistory.addIntake(amount);
    }

    private void displayIntake() {
        System.out.println("Current water intake: " + getCurrentIntake() + "ml");
    }

    private void importWater() {
        System.out.print("Enter the amount of water consumed (in ml): ");
        int amount = readIntInput();
        addWaterIntake(amount);

        WaterImport waterImport = new WaterImport(getTargetIntake(), client.getWeight(), client.getActivityLevel(), client);
        waterImport.importWaterIntake(amount);
    }

    private void viewHistory() {
        intakeHistory.displayIntakeHistory();

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n----------- HISTORY MENU -----------");
            System.out.println("1. Modify Intake");
            System.out.println("2. Delete Intake");
            System.out.println("3. Exit");
            System.out.println("------------------------------------");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    modifyIntake();
                    break;
                case 2:
                    deleteIntake();
                    break;
                case 3:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void modifyIntake() {
        System.out.print("Enter the index of the intake to modify: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        if (index >= 1 && index <= intakeHistory.getIntakeHistory().size()) {
            System.out.print("Enter the new intake amount: ");
            int newAmount = scanner.nextInt();
            scanner.nextLine();

            intakeHistory.modifyIntakeInHistory(index, newAmount);
        } else {
            System.out.println("Invalid index.");
        }
    }

    private void deleteIntake() {
        System.out.print("Enter the index of the intake to delete: ");
        int index = scanner.nextInt();
        scanner.nextLine();

        intakeHistory.deleteIntakeFromHistory(index);
    }

    private int readIntInput() {
        int value = 0;
        boolean isValidInput = false;
        do {
            try {
                System.out.print("Enter an integer value: ");
                value = scanner.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer value.");
                scanner.nextLine();
            }
        } while (!isValidInput);
        scanner.nextLine();
        return value;
    }

    public void setLoggedInClient(Client client) {
        this.client = client;
    }

    public int getTargetIntake() {
        return targetIntake;
    }

    public int getCurrentIntake() {
        return currentIntake;
    }
}