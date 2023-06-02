public class RecommendedRecipes extends Recipes {
    private String dietaryPreferences;
    private String goals;

    public RecommendedRecipes() {
        super();
    }

    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public void setDietaryPreferences(String dietaryPreferences) {
        this.dietaryPreferences = dietaryPreferences;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }
}
