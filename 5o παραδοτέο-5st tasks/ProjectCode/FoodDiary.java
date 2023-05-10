import java.util.Scanner;

public class FoodDiary {
    
    private Scanner scanner;

    public FoodDiary() {
        this.scanner = new Scanner(System.in);
    }

    public void foodDiaryMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("--------------- FOOD DIARY ---------------");
            System.out.println("1. Add food item");
            System.out.println("2. Remove food item");
            System.out.println("3. Display meals");
            System.out.println("4. Add custom meal");
            System.out.println("5. Back to main menu");
            System.out.println("-------------------------------------------");
            int choice = readChoice();
            switch (choice) {
                case 1:
                    System.out.println("Display...");
                    //addFoodItem();
                    break;
                case 2:
                    System.out.println("Display...");
                    //removeFoodItem();
                    break;
                case 3:
                    System.out.println("Display...");
                    //displayMeals();
                    break;
                case 4:
                    System.out.println("Display...");
                    //addCustomMeal();
                    break;
                case 5:
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
                choice = Integer.parseInt(scanner.nextLine());
                isValidInput = true;
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        } while (!isValidInput);
        return choice;
    }
}