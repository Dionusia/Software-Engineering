import java.util.Scanner;

public class Menu {

    private boolean isRunning;
    private Scanner scanner;

    public Menu() {
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
        System.out.println("--------------- MENU ---------------");
        System.out.println("1. Food Diary");
        System.out.println("2. Recommended Programs");
        System.out.println("3. Recipes");
        System.out.println("4. Chat Room");
        System.out.println("5. Challenges");
        System.out.println("6. Reviews");
        System.out.println("7. Water Tracker");
        System.out.println("8. Set your goals");
        System.out.println("9. Rewards/Points");
        System.out.println("10. Change Info");
        System.out.println("11. Log Out");
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
            case 1:
                foodDiary();
                break;
            case 2:
                recommendedPrograms();
                break;
            case 3:
                recipes();
                break;
            case 6:
                reviews();
                break;
            case 11:
                logOut();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void foodDiary() {
        FoodDiary foodDiary = new FoodDiary();
        foodDiary.foodDiaryMenu();
    }

    private void recommendedPrograms(){
        RecommendedPrograms programs = new RecommendedPrograms();
        programs.RecommendedProgramsMenu();
    }

    private void recipes(){
        Recipes recipes = new Recipes();
        recipes.addSampleData();
        recipes.recipesMenu();
    }


    private void reviews() {
        Review review = new Review();
        review.reviews();
    }
    
    private void logOut() {
        System.out.println("Logging out...");
        LoginPage.main(null);
    }
}