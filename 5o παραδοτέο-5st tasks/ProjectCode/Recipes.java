import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Recipes {
    private String name;
    private int cookingTime;
    private int servingSize;
    protected Scanner scanner;
    private List<Recipes> recommendedRecipes;
    private PersonalRecipeCollection personalRecipeCollection;
    private List<Ingredient> ingredients;
    private List<String> instructions;

    public Recipes() {
        this.recommendedRecipes = new ArrayList<>();
        this.personalRecipeCollection = new PersonalRecipeCollection();
        this.scanner = new Scanner(System.in);
    }

    public void recipesMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("--------------- RECIPES ---------------");
            System.out.println("1. Display recommended recipes");
            System.out.println("2. Display personal recipes");
            System.out.println("3. Modify a recipe");
            System.out.println("4. Share a recipe");
            System.out.println("5. Create a new recipe");
            System.out.println("6. Rate and review a recipe");
            System.out.println("7. Back to main menu");
            System.out.println("---------------------------------------");
            int choice = readChoice();
            switch (choice) {
                case 1:
                    displayRecommendedRecipes();
                    break;
                case 2:
                    personalRecipeCollection.displaySavedRecipes();
                    break;
                case 3:
                    modifyRecipe(personalRecipeCollection);
                    break;
                case 4:
                    // Implement share a recipe
                    break;
                case 5:
                    createNewRecipe();
                    break;
                case 6:
                    // Implement search recipe by ingredient
                    break;
                case 7:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    protected int readChoice() {
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

    private void displayRecommendedRecipes() {
        System.out.println("Recommended Recipes:");
        for (int i = 0; i < recommendedRecipes.size(); i++) {
            Recipes recipe = recommendedRecipes.get(i);
            System.out.println((i + 1) + ". " + recipe.getName());
            System.out.println("Cooking Time: " + recipe.getCookingTime() + " minutes");
            System.out.println("Serving Size: " + recipe.getServingSize());
            System.out.println("Ingredients:");
            for (Ingredient ingredient : recipe.getIngredients()) {
                System.out.println("- " + ingredient.getName() + ": " + ingredient.getQuantity() + " " + ingredient.getUnitOfMeasurement());
            }
            System.out.println("Instructions:");
            for (String instruction : recipe.getInstructions()) {
                System.out.println("- " + instruction);
            }
            System.out.println("---------------------------------------");
        }
    
        selectAndSaveRecipe();
    }
    
    private void selectAndSaveRecipe() {
        System.out.print("Enter the number of the recipe you want to add to your personal collection: ");
        try {
            int choice = scanner.nextInt();
            PersonalCollection(personalRecipeCollection, choice - 1);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a valid recipe number.");
            scanner.nextLine();
        }
    }
    
    public void PersonalCollection(PersonalRecipeCollection personalCollection, int recipeIndex) {
        if (recipeIndex >= 0 && recipeIndex < recommendedRecipes.size()) {
            Recipes selectedRecipe = recommendedRecipes.get(recipeIndex);
            personalCollection.addRecipe(selectedRecipe);
            System.out.println("Recipe added to personal collection successfully!");
        } else if (recipeIndex == recommendedRecipes.size() + 1) {
            personalCollection.displaySavedRecipes();
        } else {
            System.out.println("Invalid recipe index. Please select a valid recipe number.");
        }
    }

    
    public void modifyRecipe(PersonalRecipeCollection personalCollection) {
        // Display personal recipes
        personalCollection.displaySavedRecipes();
    
        // Ask the user to select a recipe to modify
        System.out.print("Enter the index of the recipe you want to modify: ");
        int recipeIndex = readChoice() - 1; // Subtract 1 to convert to zero-based index
    
        // Check if the recipe index is valid
        if (recipeIndex >= 0 && recipeIndex < personalCollection.getRecipes().size()) {
            Recipes selectedRecipe = personalCollection.getRecipes().get(recipeIndex);
    
            // Prompt the user to modify the recipe
            System.out.println("Modifying Recipe: " + selectedRecipe.getName());
            System.out.println("---------------------------------------");
    
            // Modify the recipe
            List<Ingredient> modifiedIngredients = new ArrayList<>();
    
            while (true) {
                System.out.println("Enter the modified ingredients (separated by commas):");
                System.out.println("Format: <Ingredient Name> <Quantity> <Unit of Measurement>");
                System.out.println("Example: Tomato Sauce 400 grams, Onion 1 medium");
                String ingredientInput = scanner.nextLine();
                modifiedIngredients = parseIngredients(ingredientInput);
    
                if (modifiedIngredients.isEmpty()) {
                    System.out.println("Invalid ingredient format. Please try again.");
                } else {
                    break;
                }
            }
    
            System.out.print("Enter the new serving size: ");
            int modifiedServingSize = readChoice();
    
            // Update the recipe with the modified ingredients and serving size
            selectedRecipe.setIngredients(modifiedIngredients);
            selectedRecipe.setServingSize(modifiedServingSize);
            System.out.println("Recipe modified successfully!");
        } else {
            System.out.println("Invalid recipe index. Please select a valid recipe number.");
        }
    }
    
    private List<Ingredient> parseIngredients(String ingredientInput) {
        List<Ingredient> ingredients = new ArrayList<>();
        String[] ingredientTokens = ingredientInput.split(",");
        for (String token : ingredientTokens) {
            String[] ingredientInfo = token.trim().split(" ");
            if (ingredientInfo.length >= 3) {
                String name = ingredientInfo[0];
                double quantity = Double.parseDouble(ingredientInfo[1]);
                String unitOfMeasurement = ingredientInfo[2];
                Ingredient ingredient = new Ingredient(name, quantity, unitOfMeasurement);
                ingredients.add(ingredient);
            }
        }
        return ingredients;
    }

    private void createNewRecipe() {
        NewRecipe newRecipe = new NewRecipe();
        newRecipe.collectRecipeInformation();
        recommendedRecipes.add(newRecipe);
        personalRecipeCollection.addRecipe(newRecipe);
        System.out.println("Recipe created successfully!");
    }

    // Getters-setters
    public String getName() {
        return name;
    }

    public int getCookingTime() {
        return cookingTime;
    }

    public int getServingSize() {
        return servingSize;
    }

    public List<Ingredient> getIngredients() {
        return ingredients;
    }

    public void setServingSize(int servingSize) {
        this.servingSize = servingSize;
    }
    
    public void setIngredients(List<Ingredient> ingredients) {
        this.ingredients = ingredients;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void setCookingTime(int cookingTime) {
        this.cookingTime = cookingTime;
    }

    public List<String> getInstructions() {
        return instructions;
    }

    public void setInstructions(List<String> instructions) {
        this.instructions = instructions;
    }

    public void shareRecipe(String medium) {
        // Code for sharing the recipe through the specified medium (e.g., social media or email)
    }

    //add data manually
    public void addSampleData() {
        Recipes recipe1 = new Recipes();
        recipe1.name = "Spaghetti Bolognese";
        recipe1.cookingTime = 30;
        recipe1.servingSize = 4;
        recipe1.ingredients = new ArrayList<>();
        recipe1.ingredients.add(new Ingredient("Spaghetti", 200, "grams"));
        recipe1.ingredients.add(new Ingredient("Ground Beef", 500, "grams"));
        recipe1.ingredients.add(new Ingredient("Tomato Sauce", 400, "grams"));
        recipe1.ingredients.add(new Ingredient("Onion", 1, "medium"));
        recipe1.instructions = new ArrayList<>();
        recipe1.instructions.add("Cook spaghetti according to package instructions.");
        recipe1.instructions.add("In a large pan, brown the ground beef and onion.");
        recipe1.instructions.add("Add tomato sauce and simmer for 10 minutes.");
        recipe1.instructions.add("Serve the sauce over cooked spaghetti.");

        Recipes recipe2 = new Recipes();
        recipe2.name = "Chicken Stir-Fry";
        recipe2.cookingTime = 25;
        recipe2.servingSize = 3;
        recipe2.ingredients = new ArrayList<>();
        recipe2.ingredients.add(new Ingredient("Chicken Breast", 400, "grams"));
        recipe2.ingredients.add(new Ingredient("Bell Pepper", 2, "pieces"));
        recipe2.ingredients.add(new Ingredient("Broccoli", 1, "head"));
        recipe2.ingredients.add(new Ingredient("Soy Sauce", 2, "tablespoons"));
        recipe2.instructions = new ArrayList<>();
        recipe2.instructions.add("Cut the chicken, bell pepper, and broccoli into bite-sized pieces.");
        recipe2.instructions.add("Stir-fry the chicken in a pan until cooked.");
        recipe2.instructions.add("Add the bell pepper and broccoli and cook for 3-4 minutes.");
        recipe2.instructions.add("Stir in the soy sauce and cook for another 2 minutes.");

        recommendedRecipes.add(recipe1);
        recommendedRecipes.add(recipe2);
    }
}
