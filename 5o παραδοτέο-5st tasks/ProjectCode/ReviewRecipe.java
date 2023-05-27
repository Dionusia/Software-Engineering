import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ReviewRecipe extends Review {
    private List<Recipes> recipes;
    private Scanner scanner;

    public ReviewRecipe() {
        recipes = new ArrayList<>();

        // Sample data to run the code
        Recipes recipe1 = new Recipes();
        recipe1.setName("Pasta Carbonara");
        recipes.add(recipe1);

        Recipes recipe2 = new Recipes();
        recipe2.setName("Chicken Stir Fry");
        recipes.add(recipe2);
        this.scanner = new Scanner(System.in);
    }

    public void displayRecipeNames() {
        if (recipes.isEmpty()) {
            System.out.println("No recipes available!");
            return;
        }
    
        System.out.println("Recipe Names:");
        for (int i = 0; i < recipes.size(); i++) {
            Recipes recipe = recipes.get(i);
            System.out.println((i + 1) + ". " + recipe.getName());
        }
    }
    public void reviewRecipe() {
        if (recipes.isEmpty()) {
            System.out.println("No recipes available for review!");
            return;
        }
    
        int recipeCount = recipes.size();
    
        System.out.println("Select a recipe to review:");
    
        displayRecipeNames();
    
        System.out.println("Enter the number of the recipe you want to review:");
        int recipeIndex;
        try {
            recipeIndex = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid recipe selection!");
            return;
        }
    
        if (recipeIndex < 1 || recipeIndex > recipeCount) {
            System.out.println("Invalid recipe selection!");
            return;
        }
    
        Recipes selectedRecipe = recipes.get(recipeIndex - 1);
    
        int rating;
            while (true) {
                // Prompt the user to rate the instructor
                System.out.println("Rate the recipe on a scale of 1-5 stars:");
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
                System.out.println("Write a review for the recipe:");
                reviewText = scanner.nextLine();
                
                // Check if the review text is empty
                if (reviewText.trim().isEmpty()) {
                    System.out.println("Review text cannot be empty. Please write a review.");
                } else {
                    break;
                }
            }
    
        Review review = new Review();
        review.setReviewText(reviewText);
        review.setRating(rating);
        selectedRecipe.addReview(review);
    
        System.out.println("Review added successfully!");
    }

    public void addReview(Recipes recipe, int rating, String reviewText, User user) {
        Review review = new Review(rating, reviewText, user);
        recipe.addReview(review);
    }
}






