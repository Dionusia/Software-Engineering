import java.util.ArrayList;
import java.util.List;

public class DietProgram {
    private String name;
    private int duration;
    private List<String> meals;
    private String preparationInfo;
    private String ingredientInfo;

    public DietProgram(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.meals = new ArrayList<>();
    }

    public void addMeal(String meal) {
        meals.add(meal);
    }

    public void removeMeal(String meal) {
        meals.remove(meal);
    }

    public List<String> getMeals() {
        return meals;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getPreparationInfo() {
        return preparationInfo;
    }

    public void setPreparationInfo(String preparationInfo) {
        this.preparationInfo = preparationInfo;
    }

    public String getIngredientInfo() {
        return ingredientInfo;
    }

    public void setIngredientInfo(String ingredientInfo) {
        this.ingredientInfo = ingredientInfo;
    }
}