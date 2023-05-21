import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class LoginPage {
    private static HashMap<String, String> users = new HashMap<>();
    private static ArrayList<Client> clients = new ArrayList<>();
    private static ArrayList<Instructor> instructors = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    
    public static void main(String[] args) {
        // create some clients and instructors
        Client client1 = new Client("stelios", "1234", 42, "male", 180, 75.0, ActivityLevel.MODERATELY_ACTIVE, "vegetarian", HealthGoal.LOSE_WEIGHT, "client");
        Client client2 = new Client("dionusia", "12314", 21, "male", 180, 75.0, ActivityLevel.MODERATELY_ACTIVE, "vegetarian", HealthGoal.LOSE_WEIGHT, "client");
        Instructor instructor1 = new Instructor("aggelos", "1234", "instructor");
        Instructor instructor2 = new Instructor("nadia", "1234", "instructor");
        clients.add(client1);
        clients.add(client2);
        instructors.add(instructor1);
        instructors.add(instructor2);
        ReviewInstructor.setRegisteredInstructors(List.of(instructor1, instructor2));
        // manually add users
        users.put("dionusia", "1234");
        users.put("stelios", "1234");
        users.put("aggelos", "1234");
        
        while (true) {
            System.out.println("Welcome to FIND YOUR BALANCE! Please choose an option:");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    login();
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Exiting program...");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    
    private static void login() {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
        System.out.println("Enter your password:");
        String password = scanner.nextLine();

        if (users.containsKey(username) && users.get(username).equals(password)) {
            System.out.println("Login successful!");

            // display different menu if user is client or instructor
            for (Client client : clients) {
                if (client.getUsername().equals(username)) {
                    Menu menu = new Menu();
                    menu.run();
                    return;
                }
            }
            for (Instructor instructor : instructors) {
                if (instructor.getUsername().equals(username)) {
                    MenuInstructor menu = new MenuInstructor();
                    menu.run();
                    return;
                }
            }
        } else {
            System.out.println("Incorrect username or password. Please try again.");
        }
    }


    private static void register() {
        System.out.println("Enter your username:");
        String username = scanner.nextLine();
    
        if (users.containsKey(username)) {
            System.out.println("Username already exists. Please try again with a different username.");
            return;
        }
    
        System.out.println("Enter your password:");
        String password = scanner.nextLine();
    
        System.out.println("Choose your role:");
        System.out.println("1. Client");
        System.out.println("2. Instructor");
        int roleChoice = scanner.nextInt();
        scanner.nextLine(); // consume the newline character
    
        switch (roleChoice) {
            case 1:
                System.out.println("Enter your age:");
                int age = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
    
                System.out.println("Enter your gender:");
                String gender = scanner.nextLine();
    
                System.out.println("Enter your height (in cm):");
                int height = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
    
                System.out.println("Enter your weight (in kg):");
                double weight = scanner.nextDouble();
                scanner.nextLine(); // consume the newline character
    
                System.out.println("Enter your activity level:");
                System.out.println("1. Slightly Active");
                System.out.println("2. Moderately Active");
                System.out.println("3. Very Active");
                System.out.println("4. Extremely Active");
                int activityChoice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                ActivityLevel activityLevel;
                switch (activityChoice) {
                    case 1:
                        activityLevel = ActivityLevel.SLIGHTLY_ACTIVE;
                        break;
                    case 2:
                        activityLevel = ActivityLevel.MODERATELY_ACTIVE;
                        break;
                    case 3:
                        activityLevel = ActivityLevel.VERY_ACTIVE;
                        break;
                    case 4:
                        activityLevel = ActivityLevel.EXTREMELY_ACTIVE;
                        break;
                    default:
                        System.out.println("Invalid choice. Setting activity level to Moderately Active.");
                        activityLevel = ActivityLevel.MODERATELY_ACTIVE;
                }
    
                System.out.println("Enter your dietary preferences:");
                String dietaryPreferences = scanner.nextLine();
    
                System.out.println("Choose your health goal:");
                List<HealthGoal> healthGoals = new ArrayList<>();
                healthGoals.add(HealthGoal.LOSE_WEIGHT);
                healthGoals.add(HealthGoal.BUILD_MUSCLE);
    
                for (int i = 0; i < healthGoals.size(); i++) {
                    HealthGoal goal = healthGoals.get(i);
                    System.out.println((i + 1) + ". " + goal.getName());
                }
    
                int goalChoice = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
    
                if (goalChoice >= 1 && goalChoice <= healthGoals.size()) {
                    HealthGoal selectedGoal = healthGoals.get(goalChoice - 1);
                    Client client = new Client(username, password, age, gender, height, weight, activityLevel,
                            dietaryPreferences, selectedGoal, "client");
                    clients.add(client);
                    users.put(username, password);
                    System.out.println("Registration successful!");
                } else {
                    System.out.println("Invalid choice. Registration canceled.");
                }
    
                break;
            case 2:
                Instructor instructor = new Instructor(username, password, "instructor");
                instructors.add(instructor);
                users.put(username, password);
                System.out.println("Registration successful!");
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
                return;
        }
    }   

}
    /*private static void displayUsers() {
        System.out.println("Registered Users:");
        for (String username : users.keySet()) {
            System.out.println("Username: " + username + ", Password: " + users.get(username));
            for (Client client : clients) {
                if (client.getUsername().equals(username)) {
                    System.out.println("Client Info:");
                    System.out.println("Age: " + client.getAge());
                    System.out.println("Gender: " + client.getGender());
                    System.out.println("Height: " + client.getHeight() + " cm");
                    System.out.println("Weight: " + client.getWeight() + " kg");
                    System.out.println("Activity Level: " + client.getActivityLevel());
                    System.out.println("Dietary Preferences: " + client.getDietaryPreferences());
                    System.out.println("Goals: " + client.getGoals());
                    System.out.println("role: " + client.getRole());
                }
            }
        }
    }*/