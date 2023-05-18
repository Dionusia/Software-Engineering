import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class RecommendedPrograms {
    private List<FitnessProgram> fitnessPrograms;
    private List<DietProgram> dietPrograms;
    private List<Object> savedPrograms;
    private Scanner scanner;

    public RecommendedPrograms() {
        this.fitnessPrograms = new ArrayList<>();
        this.dietPrograms = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }


    public void RecommendedProgramsMenu() {
        addSampleData(); //to add the data
        System.out.println("Please fill out the Fitness and Dietary Preferences Questionnaire");
        // prompt for fill out questionnaire
        Questionnaire questionnaire = new Questionnaire();
        questionnaire.fillOut();
        
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("--------------- RECOMMENDED PROGRAMS ---------------");
            System.out.println("1. View recommended programs");
            System.out.println("2. View my programs");
            System.out.println("3. Modify saved program");
            System.out.println("4. Update questionnaire");
            System.out.println("5. Back to main menu");
            System.out.println("-------------------------------------------");
            int choice = readChoice();
            switch (choice) {
                case 1:
                    displayPrograms();
                    break;
                case 2:
                    displaySavedPrograms();
                    break;
                case 3:
                    modifySavedPrograms();
                    break;
                case 4:
                    questionnaire.fillOut();
                    break;
                case 5:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private int readChoice() {
        int choice = 0;
        boolean isValidInput = false;
        do {
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                isValidInput = true;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine(); 
            }
        } while (!isValidInput);
        
        scanner.nextLine(); // Consume the newline character
        
        return choice;
    }


    public void addFitnessProgram(FitnessProgram program) {
        fitnessPrograms.add(program);
    }

    public void addDietProgram(DietProgram program) {
        dietPrograms.add(program);
    }

    public List<FitnessProgram> getFitnessPrograms() {
        return fitnessPrograms;
    }

    public List<DietProgram> getDietPrograms() {
        return dietPrograms;
    }
    
    private List<String> getAllPrograms() {
        List<String> allPrograms = new ArrayList<>();
        for (FitnessProgram fitnessProgram : fitnessPrograms) {
            String programDetails = "Fitness Program: " + fitnessProgram.getName()
                    + "\nDuration: " + fitnessProgram.getDuration() + " minutes"
                    + "\nExercises: " + fitnessProgram.getExercises();
            allPrograms.add(programDetails);
        }
        for (DietProgram dietProgram : dietPrograms) {
            String programDetails = "Diet Program: " + dietProgram.getName()
                    + "\nDuration: " + dietProgram.getDuration() + " days"
                    + "\nMeals: " + dietProgram.getMeals();
            allPrograms.add(programDetails);
        }
        return allPrograms;
    }

    public void displayPrograms() {
        List<String> allPrograms = getAllPrograms();
        if (allPrograms.isEmpty()) {
            System.out.println("No programs available.");
        } else {
            System.out.println("All Programs:");
            for (int i = 0; i < allPrograms.size(); i++) {
                String program = allPrograms.get(i);
                System.out.println((i + 1) + ". " + program);
            }
            
            System.out.print("Enter the program numbers you want to save (separated by commas): ");
            String input = scanner.nextLine().trim();
            
            String[] programNumbers = input.split(",");
            List<String> selectedPrograms = new ArrayList<>();
            for (String number : programNumbers) {
                int index = Integer.parseInt(number.trim()) - 1;
                if (index >= 0 && index < allPrograms.size()) {
                    selectedPrograms.add(allPrograms.get(index));
                }
            }
            
            if (!selectedPrograms.isEmpty()) {
                savePrograms(selectedPrograms);
                System.out.println("Programs saved successfully!");
            }
        }
    }

    public void savePrograms(List<String> programs) {
        savedPrograms = new ArrayList<>(programs);
    }

    public void displaySavedPrograms() {
        if (savedPrograms == null || savedPrograms.isEmpty()) {
            System.out.println("No saved programs.");
        } else {
            System.out.println("Saved Programs:");
            for (Object program : savedPrograms) {
                System.out.println(program);
            }
        }
    }

    public void modifySavedPrograms() {
        if (savedPrograms == null || savedPrograms.isEmpty()) {
            System.out.println("No saved programs.");
            return;
        }
    
        System.out.println("Saved Programs:");
        for (int i = 0; i < savedPrograms.size(); i++) {
            System.out.println((i + 1) + ". " + savedPrograms.get(i));
        }
    
        System.out.print("Enter the program number you want to modify: ");
        int programNumber = readChoice();
    
        if (programNumber < 1 || programNumber > savedPrograms.size()) {
            System.out.println("Invalid program number.");
            return;
        }
    
        Object selectedProgram = savedPrograms.get(programNumber - 1);
        System.out.println("Selected Program:");
        System.out.println(selectedProgram);
    
        if (selectedProgram instanceof FitnessProgram) {
            FitnessProgram fitnessProgram = (FitnessProgram) selectedProgram;
            System.out.print("Enter the new fitness program name (or leave blank to keep the same): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                fitnessProgram.setName(newName);
            }
    
            System.out.print("Enter the new fitness program duration in minutes (or 0 to keep the same): ");
            int newDuration = readChoice();
            if (newDuration > 0) {
                fitnessProgram.setDuration(newDuration);
            }
    
            System.out.print("Enter the new exercises (separated by commas) (or leave blank to keep the same): ");
            String newExercises = scanner.nextLine().trim();
            if (!newExercises.isEmpty()) {
                fitnessProgram.getExercises().clear(); // Remove existing exercises
                String[] exerciseArray = newExercises.split(",");
                for (String exercise : exerciseArray) {
                    fitnessProgram.addExercise(exercise.trim());
                }
            }
        } else if (selectedProgram instanceof DietProgram) {
            DietProgram dietProgram = (DietProgram) selectedProgram;
            System.out.print("Enter the new diet program name (or leave blank to keep the same): ");
            String newName = scanner.nextLine().trim();
            if (!newName.isEmpty()) {
                dietProgram.setName(newName);
            }
    
            System.out.print("Enter the new diet program duration in days (or 0 to keep the same): ");
            int newDuration = readChoice();
            if (newDuration > 0) {
                dietProgram.setDuration(newDuration);
            }
    
            System.out.print("Enter the new meals (separated by commas) (or leave blank to keep the same): ");
            String newMeals = scanner.nextLine().trim();
            if (!newMeals.isEmpty()) {
                dietProgram.getMeals().clear(); // Remove existing meals
                String[] mealArray = newMeals.split(",");
                for (String meal : mealArray) {
                    dietProgram.addMeal(meal.trim());
                }
            }
        }
    
        System.out.println("Program modified successfully!");
    }
    
   
    //data inserted manually
    public void addSampleData() {
        // Create fitness programs
        FitnessProgram fitnessProgram1 = new FitnessProgram("Fitness Program 1", 30);
        fitnessProgram1.addExercise("Push-ups");
        fitnessProgram1.addExercise("Squats");
        fitnessProgram1.addExercise("Plank");
    
        FitnessProgram fitnessProgram2 = new FitnessProgram("Fitness Program 2", 45);
        fitnessProgram2.addExercise("Running");
        fitnessProgram2.addExercise("Jumping Jacks");
        fitnessProgram2.addExercise("Burpees");
    
        // Create diet programs
        DietProgram dietProgram1 = new DietProgram("Diet Program 1", 30);
        dietProgram1.addMeal("Breakfast: Oatmeal with fruits");
        dietProgram1.addMeal("Lunch: Grilled chicken with vegetables");
        dietProgram1.addMeal("Dinner: Salmon with quinoa");
    
        DietProgram dietProgram2 = new DietProgram("Diet Program 2", 45);
        dietProgram2.addMeal("Breakfast: Avocado toast");
        dietProgram2.addMeal("Lunch: Quinoa salad");
        dietProgram2.addMeal("Dinner: Vegetable stir-fry");
    
        // Add programs to RecommendedPrograms
        addFitnessProgram(fitnessProgram1);
        addFitnessProgram(fitnessProgram2);
        addDietProgram(dietProgram1);
        addDietProgram(dietProgram2);
    }
}