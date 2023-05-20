public class RecommendedRecipes extends Recipes {
    private String dietaryPreferences;
    private String goals;

    /*public RecommendedRecipes(String name, int cookingTime, int servingSize, List<Ingredient> ingredients, List<String> instructions, String dietaryPreferences, String goals) {
        super(name, cookingTime, servingSize, ingredients, instructions);
        this.dietaryPreferences = dietaryPreferences;
        this.goals = goals;
    }*/

    public String getDietaryPreferences() {
        return dietaryPreferences;
    }

    public String getGoals() {
        return goals;
    }
}
