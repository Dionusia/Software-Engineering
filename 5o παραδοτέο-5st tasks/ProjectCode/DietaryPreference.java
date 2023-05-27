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

    //for display the name
    @Override
    public String toString() {
        return name;
    }
}