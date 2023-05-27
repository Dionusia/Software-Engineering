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