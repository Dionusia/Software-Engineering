import java.util.List;
import java.util.Scanner;

public class ReviewInstructor extends Review {
    private Instructor instructor;
    private static List<Instructor> registeredInstructors;
    private Scanner scanner;

    public ReviewInstructor(Instructor instructor, int rating, String reviewText, User user) {
        super(rating, reviewText, user);
        this.instructor = instructor;
        this.scanner = new Scanner(System.in);
    }

    // Getters and setters for the instructor field
    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public static void setRegisteredInstructors(List<Instructor> instructors) {
        registeredInstructors = instructors;
    }

    public void displayRegisteredInstructors() {
        System.out.println("Registered Instructors:");
        for (int i = 0; i < registeredInstructors.size(); i++) {
            Instructor instructor = registeredInstructors.get(i);
            System.out.println((i + 1) + ". " + instructor.getUsername());
        }
    }

    public void reviewInstructor() {

        displayRegisteredInstructors();
        
        Feedback feedback = new Feedback();

        while (true) {
            // Prompt the user to select an instructor
            System.out.println("Enter the number corresponding to the instructor you want to rate:");
            int instructorChoice = scanner.nextInt();
            scanner.nextLine(); // Consume the newline character
            
            // Check if the selected choice is valid
            if (instructorChoice < 1 || instructorChoice > registeredInstructors.size()) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }
            
            int rating;
            while (true) {
                // Prompt the user to rate the instructor
                System.out.println("Rate the instructor on a scale of 1-5 stars:");
                rating = scanner.nextInt();
                scanner.nextLine(); // Consume the newline character
                
                // Check if the rating is within the valid range
                if (rating < 1 || rating > 5) {
                    System.out.println("Invalid rating. Please enter a number between 1 and 5.");
                } else {
                    break;
                }
            }
            
            String reviewText;
            while (true) {
                // Prompt the user to write a review
                System.out.println("Write a review for the instructor:");
                reviewText = scanner.nextLine();
                
                // Check if the review text is empty
                if (reviewText.trim().isEmpty()) {
                    System.out.println("Review text cannot be empty. Please write a review.");
                } else {
                    break;
                }
            }
            
            Instructor selectedInstructor = registeredInstructors.get(instructorChoice - 1);
            
            ReviewInstructor review = new ReviewInstructor(selectedInstructor, rating, reviewText, user);
        
            // Add the review to the Feedback collection
            feedback.addReview(review);
    
            System.out.println("Review and rating submitted successfully!");
            break;
        }
    }

    public void editReview() {
        //implementation of editing review
    }
}