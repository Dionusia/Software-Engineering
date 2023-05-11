public class Client extends User {
    private int age;
    private String gender;
    private int height;
    private double weight;
    private String activityLevel;
    private String dietaryPreferences;
    private String goals;
    private String role;
    
    public Client(String username, String password, int age, String gender, int height, double weight,
            String activityLevel, String dietaryPreferences, String goals, String role) {
        super(username, password,role);
        this.age = age;
        this.gender = gender;
        this.height = height;
        this.weight = weight;
        this.activityLevel = activityLevel;
        this.dietaryPreferences = dietaryPreferences;
        this.goals = goals;
        this.role = role;
    }
    
    // getters and setters for age, gender, height, weight, activityLevel, dietaryPreferences, goals, and role
    public int getAge() {
        return age;
    }
    
    public void setAge(int age) {
        this.age = age;
    }
    
    public String getGender() {
        return gender;
    }
    
    public void setGender(String gender) {
        this.gender = gender;
    }
    
    public int getHeight() {
        return height;
    }
    
    public void setHeight(int height) {
        this.height = height;
    }
    
    public double getWeight() {
        return weight;
    }
    
    public void setWeight(double weight) {
        this.weight = weight;
    }
    
    public String getActivityLevel() {
        return activityLevel;
    }
    
    public void setActivityLevel(String activityLevel) {
        this.activityLevel = activityLevel;
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

    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}
