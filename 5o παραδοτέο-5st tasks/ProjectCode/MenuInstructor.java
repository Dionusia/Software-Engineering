import java.util.ArrayList;
import java.util.List;
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

    //we haven't implement menu instructor
    private void displayMenu() {
        System.out.println("--------------- MENU INSTRUCTOR ---------------");
        System.out.println("1. Create Meal Plan");
        System.out.println("2. Create Fitness Plan");
        System.out.println("3. Manage Client Profile");
        System.out.println("4. See your feedback");
        System.out.println("5. Log Out");
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
                feedback();
                break;
            case 5:
                logOut();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void feedback() {
        Feedback feedback = new Feedback(); 
        List<Instructor> registeredInstructors = new ArrayList<>(); 
    
        Instructor loggedInInstructor = getCurrentLoggedInInstructor();
    
        if (loggedInInstructor != null && registeredInstructors.contains(loggedInInstructor)) {
            // Get the reviews for the current instructor
            List<ReviewInstructor> instructorReviews = feedback.getReviewsByInstructor(loggedInInstructor);
            
            System.out.println("Instructor: " + loggedInInstructor.getUsername());
            
            // Display the reviews and ratings
            for (ReviewInstructor review : instructorReviews) {
                System.out.println("Rating: " + review.getRating());
                System.out.println("Review: " + review.getReviewText());
                System.out.println("User: " + review.getUser().getUsername());
                System.out.println("---------------------");
            }
            
            System.out.println();
        } else {
            System.out.println("No reviews found for the logged-in instructor.");
        }
    }

    private Instructor getCurrentLoggedInInstructor() {
        Instructor loggedInInstructor = null;
    
        return loggedInInstructor;
    }

    private void logOut() {
        System.out.println("Logging out...");
        LoginPage.main(null);
    }
}
