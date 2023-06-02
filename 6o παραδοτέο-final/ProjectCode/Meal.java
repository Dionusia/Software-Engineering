public class Meal extends FoodItem {
   public enum MealCategory {
       BREAKFAST,
       LUNCH,
       DINNER,
       SNACK
   }

   private MealCategory category;
   private FoodItem foodItem;

   public Meal(MealCategory category, FoodItem foodItem) {
      this.category = category;
      this.foodItem = foodItem;
  }

  //constructor for nutrition info
    public Meal(int calories, int fat, int cholesterol, int carbohydrates, int fiber, int protein, int vitamins) {
        NutritionInfo nutritionInfo = new NutritionInfo(calories, fat, cholesterol, carbohydrates, fiber, protein, vitamins);
        this.foodItem = new FoodItem(null, nutritionInfo, null, null);
    }

  public MealCategory getCategory() {
      return category;
  }

  public void setCategory(MealCategory category) {
      this.category = category;
  }

  public FoodItem getFoodItem() {
      return foodItem;
  }

  public void setFoodItem(FoodItem foodItem) {
      this.foodItem = foodItem;
  }

  public String getFoodName() {
   return foodItem.getName();
  }
}