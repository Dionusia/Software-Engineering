import java.util.List;
import java.util.Scanner;

public class ReviewRecipe extends Review {
    private List<Recipes> recipes;
    private Scanner scanner;

    public ReviewRecipe(List<Recipes> recipes) {
        this.recipes = recipes;
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
    
        System.out.println("Enter your recipe review:");
        String reviewText = scanner.nextLine();
        System.out.println("Enter your rating (1-5):");
        int rating;
        try {
            rating = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            System.out.println("Invalid rating format!");
            return;
        }
    
        if (rating < 1 || rating > 5) {
            System.out.println("Invalid rating! Rating should be between 1 and 5.");
            return;
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



