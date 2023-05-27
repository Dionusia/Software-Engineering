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
    private List<Review> reviews;
    private DietaryPreference dietaryPreference;

    public Recipes() {
        this.recommendedRecipes = new ArrayList<>();
        this.personalRecipeCollection = new PersonalRecipeCollection();
        this.dietaryPreference = null;
        this.reviews = new ArrayList<>();
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
            System.out.println("6. Search recipes by ingredient");
            System.out.println("7. Back to main menu");
            System.out.println("---------------------------------------");
            int choice = readChoice();
            switch (choice) {
                case 1:
                    displayRecommendedRecipes(recommendedRecipes);
                    break;
                case 2:
                    personalRecipeCollection.displaySavedRecipes();
                    break;
                case 3:
                    modifyRecipe(personalRecipeCollection);
                    break;
                case 4:
                    shareRecipe(personalRecipeCollection);
                    break;
                case 5:
                    createNewRecipe();
                    break;
                case 6:
                    searchRecipesByIngredient();
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

    public void displayRecommendedRecipes(List<Recipes> recipes) {
        System.out.println("Recommended Recipes:");
        for (int i = 0; i < recipes.size(); i++) {
            Recipes recipe = recipes.get(i);
            System.out.println((i + 1) + ". " + recipe.getName());
            System.out.println("Cooking Time: " + recipe.getCookingTime() + " minutes");
            System.out.println("Serving Size: " + recipe.getServingSize());
            System.out.println("Dietary Preferences: " + recipe.getDietaryPreference());
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

    public void shareRecipe(PersonalRecipeCollection personalRecipeCollection) {
        List<Recipes> savedRecipes = personalRecipeCollection.getRecipes();
    
        if (savedRecipes.isEmpty()) {
            System.out.println("No saved recipes found.");
            return;
        }
    
        System.out.println("Select a recipe to share:");
        for (int i = 0; i < savedRecipes.size(); i++) {
            System.out.println((i + 1) + ". " + savedRecipes.get(i).getName());
        }
    
        int recipeChoice = readChoice();
    
        Recipes selectedRecipe = savedRecipes.get(recipeChoice - 1);
    
        SocialMedia socialMedia = new SocialMedia();
        String selectedOption = socialMedia.selectShareOption();
    
        System.out.println("Successfully shared recipe '" + selectedRecipe.getName() + "' on " + selectedOption);
    }
    
    private void searchRecipesByIngredient() {
        System.out.print("Enter the name of the ingredient to search for: ");
        String ingredientName = scanner.nextLine();
        
        List<Recipes> matchingRecipes = new ArrayList<>();

        for (Recipes recipe : recommendedRecipes) {
            for (Ingredient ingredient : recipe.getIngredients()) {
                if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
                    matchingRecipes.add(recipe);
                    break;
                }
            }
        }

        if (matchingRecipes.isEmpty()) {
            System.out.println("No recipes found that include the ingredient: " + ingredientName);
        } else {
            System.out.println("Recipes including the ingredient: " + ingredientName);
            for (int i = 0; i < matchingRecipes.size(); i++) {
                Recipes recipe = matchingRecipes.get(i);
                System.out.println((i + 1) + ". " + recipe.getName());
                System.out.println("Cooking Time: " + recipe.getCookingTime() + " minutes");
                System.out.println("Serving Size: " + recipe.getServingSize());
                System.out.println("Dietary Preferences: " + recipe.getDietaryPreference());
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
        }
    }

    public void addReview(Review review) {
        reviews.add(review);
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

    public DietaryPreference getDietaryPreference() {
        return dietaryPreference;
    }

    public void setDietaryPreference(DietaryPreference dietaryPreference) {
        this.dietaryPreference = dietaryPreference;
    }

    //add data manually
    public void addSampleData() {
        DietaryPreference preference1 = new DietaryPreference("Vegetarian");
        DietaryPreference preference2 = new DietaryPreference("Vegan");
        DietaryPreference preference3 = new DietaryPreference("Οmnivorous");
    
        Recipes recipe1 = new Recipes();
        recipe1.setName("Spaghetti Bolognese");
        recipe1.setCookingTime(30);
        recipe1.setServingSize(4);
        recipe1.setIngredients(new ArrayList<>());
        recipe1.getIngredients().add(new Ingredient("Spaghetti", 200, "grams"));
        recipe1.getIngredients().add(new Ingredient("Ground Beef", 500, "grams"));
        recipe1.getIngredients().add(new Ingredient("Tomato Sauce", 400, "grams"));
        recipe1.getIngredients().add(new Ingredient("Onion", 1, "medium"));
        recipe1.setInstructions(new ArrayList<>());
        recipe1.getInstructions().add("Cook spaghetti according to package instructions.");
        recipe1.getInstructions().add("In a large pan, brown the ground beef and onion.");
        recipe1.getInstructions().add("Add tomato sauce and simmer for 10 minutes.");
        recipe1.getInstructions().add("Serve the sauce over cooked spaghetti.");
        recipe1.setDietaryPreference(preference1);
    
        Recipes recipe2 = new Recipes();
        recipe2.setName("Chicken Stir-Fry");
        recipe2.setCookingTime(25);
        recipe2.setServingSize(3);
        recipe2.setIngredients(new ArrayList<>());
        recipe2.getIngredients().add(new Ingredient("Chicken Breast", 400, "grams"));
        recipe2.getIngredients().add(new Ingredient("Bell Pepper", 2, "pieces"));
        recipe2.getIngredients().add(new Ingredient("Broccoli", 1, "head"));
        recipe2.getIngredients().add(new Ingredient("Soy Sauce", 2, "tablespoons"));
        recipe2.setInstructions(new ArrayList<>());
        recipe2.getInstructions().add("Cut the chicken, bell pepper, and broccoli into bite-sized pieces.");
        recipe2.getInstructions().add("Stir-fry the chicken in a pan until cooked.");
        recipe2.getInstructions().add("Add the bell pepper and broccoli and cook for 3-4 minutes.");
        recipe2.getInstructions().add("Stir in the soy sauce and cook for another 2 minutes.");
        recipe2.setDietaryPreference(preference2);

        Recipes recipe3 = new Recipes();
        recipe3.setName("Roasted Vegetable Quinoa Salad");
        recipe3.setCookingTime(40);
        recipe3.setServingSize(6);
        recipe3.setIngredients(new ArrayList<>());
        recipe3.getIngredients().add(new Ingredient("Quinoa", 1, "cup"));
        recipe3.getIngredients().add(new Ingredient("Bell Pepper", 2, "pieces"));
        recipe3.getIngredients().add(new Ingredient("Zucchini", 1, "medium"));
        recipe3.getIngredients().add(new Ingredient("Cherry Tomatoes", 1, "cup"));
        recipe3.getIngredients().add(new Ingredient("Red Onion", 1, "small"));
        recipe3.getIngredients().add(new Ingredient("Olive Oil", 2, "tablespoons"));
        recipe3.getIngredients().add(new Ingredient("Lemon Juice", 2, "tablespoons"));
        recipe3.getIngredients().add(new Ingredient("Onion", 2, "medium"));
        recipe3.setInstructions(new ArrayList<>());
        recipe3.getInstructions().add("Cook quinoa according to package instructions.");
        recipe3.getInstructions().add("Preheat the oven to 400°F (200°C).");
        recipe3.getInstructions().add("Cut the bell pepper, zucchini, and red onion into bite-sized pieces.");
        recipe3.getInstructions().add("Toss the vegetables with olive oil, salt, and pepper.");
        recipe3.getInstructions().add("Spread the vegetables on a baking sheet and roast for 20-25 minutes.");
        recipe3.getInstructions().add("In a large bowl, combine cooked quinoa, roasted vegetables, cherry tomatoes, and lemon juice.");
        recipe3.getInstructions().add("Season with salt and pepper to taste.");
        recipe3.setDietaryPreference(preference3);
    
        recommendedRecipes.add(recipe1);
        recommendedRecipes.add(recipe2);
        recommendedRecipes.add(recipe3);
    } 
}
