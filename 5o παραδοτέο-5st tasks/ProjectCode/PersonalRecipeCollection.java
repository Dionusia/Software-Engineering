import java.util.ArrayList;
import java.util.List;

public class PersonalRecipeCollection {
    private List<Recipes> savedRecipes;

    public PersonalRecipeCollection() {
        this.savedRecipes = new ArrayList<>();
    }

    public void addRecipe(Recipes recipe) {
        savedRecipes.add(recipe);
    }

    public void displaySavedRecipes() {
        System.out.println("Saved Recipes:");
        for (int i = 0; i < savedRecipes.size(); i++) {
            Recipes recipe = savedRecipes.get(i);
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
    }
    public List<Recipes> getRecipes() {
        return savedRecipes;
    }
}
