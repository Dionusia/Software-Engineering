public class FoodItem extends FoodDiary {
    private String name;
    private NutritionInfo nutritionInfo;
    private String notes;
    private String tag;
    
    public FoodItem(String name, NutritionInfo nutritionInfo, String notes, String tag) {
    this.name = name;
    this.nutritionInfo = nutritionInfo;
    this.notes = notes;
    this.tag = tag;
    }
    
    public String getName() {
    return name;
    }
    
    public void setName(String name) {
    this.name = name;
    }
    
    public NutritionInfo getNutritionInfo() {
    return nutritionInfo;
    }
    
    public void setNutritionInfo(NutritionInfo nutritionInfo) {
    this.nutritionInfo = nutritionInfo;
    }
    
    public String getNotes() {
    return notes;
    }
    
    public void setNotes(String notes) {
    this.notes = notes;
    }
    
    public String getTag() {
    return tag;
    }
    
    public void setTag(String tag) {
    this.tag = tag;
    }
}
 
 