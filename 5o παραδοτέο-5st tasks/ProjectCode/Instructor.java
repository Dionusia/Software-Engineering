public class Instructor extends User {
    public Instructor(String username, String password) {
        super(username, password);
    }
    
    public void createMealPlan(Client client) {
        // logic for creating a personalized meal plan for the client
    }
    
    public void createFitnessPlan(Client client) {
        // logic for creating a personalized fitness plan for the client
    }
    
    public void manageClientProfile(Client client) {
        // logic for managing the client's profile
    }
}