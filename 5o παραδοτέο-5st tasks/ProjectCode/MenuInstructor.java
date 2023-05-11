import java.util.Scanner;

public class MenuInstructor {

    private boolean isRunning;
    private Scanner scanner;

    public MenuInstructor() {
        this.isRunning = true;
        this.scanner = new Scanner(System.in);
    }

    public void run() {
        while (isRunning) {
            displayMenu();
            int choice = readChoice();
            executeChoice(choice);
        }
        scanner.close();
    }

    private void displayMenu() {
        System.out.println("--------------- MENU INSTRUCTOR ---------------");
        System.out.println("1. Create Meal Plan");
        System.out.println("2. Create Fitness Plan");
        System.out.println("3. Manage Client Profile");
        System.out.println("4. Log Out");
        System.out.println("-------------------------------------");
    }

    private int readChoice() {
        int choice = 0;
        boolean isValidInput = false;
        do {
            try {
                System.out.print("Enter your choice: ");
                choice = Integer.parseInt(scanner.nextLine());
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!isValidInput);
        return choice;
    }

    private void executeChoice(int choice) {
        switch (choice) {
            case 4:
                logOut();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void logOut() {
        System.out.println("Logging out...");
        LoginPage.main(null);
    }
}
