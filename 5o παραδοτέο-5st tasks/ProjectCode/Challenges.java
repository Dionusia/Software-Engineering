import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Challenges {
    private String name;
    private String description;
    private String rules;
    private int duration;
    private String requirements;
    private Scanner scanner;

    public Challenges() {
        this.scanner = new Scanner(System.in);
    }

    public void challengesMenu(List<Challenges> challengeList) {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("--------------- CHALLENGES ---------------");
            System.out.println("1. Display challenges");
            System.out.println("2. Search challenge");
            System.out.println("3. Back to main menu");
            System.out.println("-------------------------------------------");
            int choice = readChoice();
            switch (choice) {
                case 1:
                    displayChallenges(challengeList);
                    break;
                case 2:
                    //
                    break;
                case 3:
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
        scanner.nextLine();
        return choice;
    }

    private void displayChallenges(List<Challenges> challengeList) {
        System.out.println("--------------- CHALLENGES ---------------");
        for (int i = 0; i < challengeList.size(); i++) {
            Challenges challenge = challengeList.get(i);
            System.out.println((i + 1) + ". " + challenge.getChallengeInfo());
        }
        System.out.println("-------------------------------------------");
    
        int selectedChallengeIndex = readSelectedChallengeIndex(challengeList);
        if (selectedChallengeIndex == -1) {
            System.out.println("Challenge selection canceled. Returning to main menu.");
            return;
        }
    
        Challenges selectedChallenge = challengeList.get(selectedChallengeIndex);
        System.out.println(selectedChallenge.getChallengeInfo());
    
        boolean acceptedTerms = acceptChallengeTerms();
        if (acceptedTerms) {
            System.out.println("You have accepted the terms and conditions of the challenge.");
            System.out.println("Starting the challenge...");
            // Add code to start the challenge here
        } else {
            System.out.println("You have declined the terms and conditions of the challenge.");
            System.out.println("Returning to main menu.");
        }
    }
    
    private int readSelectedChallengeIndex(List<Challenges> challengeList) {
        int selectedChallengeIndex = -1;
        boolean isValidInput = false;
        do {
            try {
                System.out.print("Select a challenge (enter the corresponding number): ");
                selectedChallengeIndex = scanner.nextInt();
                if (selectedChallengeIndex >= 1 && selectedChallengeIndex <= challengeList.size()) {
                    isValidInput = true;
                } else {
                    System.out.println("Invalid input. Please enter a valid challenge number.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        } while (!isValidInput);
        scanner.nextLine();
        return selectedChallengeIndex - 1;
    }
    
    private boolean acceptChallengeTerms() {
        String choice;
        do {
            System.out.print("Do you accept the terms and conditions of the challenge? (Y/N): ");
            choice = scanner.nextLine().trim().toUpperCase();
        } while (!choice.equals("Y") && !choice.equals("N"));
        return choice.equals("Y");
    }

    //setters-getters
    public String getChallengeInfo() {
        return "Challenge: " + name + "\nDescription: " + description + "\nRules: " + rules + "\nDuration: " + duration + " days\nRequirements: " + requirements;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRequirements(String requirements) {
        this.requirements = requirements;
    }

    // Add data manually
    public void addSampleData(List<Challenges> challengeList) {
        NutritionChallenge nutritionChallenge = new NutritionChallenge(
                "Healthy Eating Challenge",
                "Improve your eating habits and make healthier food choices.",
                "Follow a balanced diet, avoid processed foods, and consume plenty of fruits and vegetables.",
                30,
                "Keep a food journal and track your daily meals."
        );
    
        FitnessChallenge fitnessChallenge = new FitnessChallenge(
                "30-Day Fitness Challenge",
                "Get fit and improve your overall physical fitness in just 30 days.",
                "Perform a combination of cardio exercises, strength training, and flexibility exercises.",
                30,
                "Create a workout plan and track your progress."
        );
    
        challengeList.add(nutritionChallenge);
        challengeList.add(fitnessChallenge);
    }
}