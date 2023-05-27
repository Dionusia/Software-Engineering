import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Review {
    private int rating;
    private String reviewText;
    protected User user;
    private Scanner scanner;
    List<Recipes> recipes = new ArrayList<>();

    public Review() {
        this.scanner = new Scanner(System.in);
    }

    public Review(int rating, String reviewText, User user) {
        this.rating = rating;
        this.reviewText = reviewText;
        this.user = user;
    }

    public void reviews() {
        System.out.println("1. Review Recipe");
        System.out.println("2. Review Instructor");
        System.out.print("Enter your choice: ");
        int reviewChoice = Integer.parseInt(scanner.nextLine());

        switch (reviewChoice) {
            case 1:
                reviewRecipe();
                break;
            case 2:
                reviewInstructor();
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    private void reviewInstructor() {
        ReviewInstructor displayInstructors = new ReviewInstructor(null, 0, "", null);
        displayInstructors.reviewInstructor();
    }

    private void reviewRecipe() {
        ReviewRecipe reviewRecipe = new ReviewRecipe();
        reviewRecipe.reviewRecipe();
    }  
    
    //getters-setters
    public int getRating() {
        return rating;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public User getUser() {
        return user;
    }
}