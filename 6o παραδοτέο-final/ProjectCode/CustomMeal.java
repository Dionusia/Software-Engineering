import java.util.List;

public class CustomMeal extends FoodItem {
    private List<String> dietaryRestrictions;
    private List<String> ingredients;
 
    public CustomMeal(String name, NutritionInfo nutritionInfo, String notes, String tag, List<String> dietaryRestrictions, List<String> ingredients) {
       super(name, nutritionInfo, notes, tag);
       this.dietaryRestrictions = dietaryRestrictions;
       this.ingredients = ingredients;
    }
 
    public List<String> getDietaryRestrictions() {
       return dietaryRestrictions;
    }
 
    public void setDietaryRestrictions(List<String> dietaryRestrictions) {
       this.dietaryRestrictions = dietaryRestrictions;
    }
 
    public List<String> getIngredients() {
       return ingredients;
    }
 
    public void setIngredients(List<String> ingredients) {
       this.ingredients = ingredients;
    }
}