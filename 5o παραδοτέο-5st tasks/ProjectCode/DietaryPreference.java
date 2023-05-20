import java.util.ArrayList;
import java.util.List;

public class DietaryPreference {
    private String name;
    private List<Recipes> suggestedRecipes;

    public DietaryPreference(String name) {
        this.name = name;
        this.suggestedRecipes = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Recipes> getSuggestedRecipes() {
        return suggestedRecipes;
    }

    public void addSuggestedRecipe(Recipes recipe) {
        suggestedRecipes.add(recipe);
    }

    //for display the name
    @Override
    public String toString() {
        return name;
    }
}