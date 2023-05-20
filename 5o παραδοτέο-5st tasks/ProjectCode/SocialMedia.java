import java.util.InputMismatchException;
import java.util.Scanner;

public class SocialMedia {
    private Scanner scanner;

    public SocialMedia() {
        this.scanner = new Scanner(System.in);
    }

    public String selectShareOption() {
        System.out.println("Select a sharing option:");
        System.out.println("1. Email");
        System.out.println("2. Facebook");
        System.out.println("3. Twitter");
        System.out.println("4. WhatsApp");
        System.out.print("Enter your choice: ");

        int choice = readChoice();

        switch (choice) {
            case 1:
                return "Email";
            case 2:
                return "Facebook";
            case 3:
                return "Twitter";
            case 4:
                return "WhatsApp";
            default:
                System.out.println("Invalid choice. Please select a valid option.");
                return selectShareOption();
        }
    }

    private int readChoice() {
        int choice = 0;
        boolean isValidInput = false;
        do {
            try {
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
}
