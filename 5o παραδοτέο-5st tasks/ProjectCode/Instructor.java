public class Instructor extends User {

    private String role;
    
    public Instructor(String username, String password, String role) {
        super(username, password,role);
        this.role = role;
    }
    
    public void createMealPlan(Client client) {
        // creating a personalized meal plan for the client
    }
    
    public void createFitnessPlan(Client client) {
        // creating a personalized fitness plan for the client
    }
    
    public void manageClientProfile(Client client) {
        // managing the client's profile
    }
    
    public String getRole() {
        return role;
    }
    
    public void setRole(String role) {
        this.role = role;
    }
}