import java.util.ArrayList;
import java.util.List;

public class ProgressTracker {
    private List<Challenges> challenges;
    private List<String> completedChallenges;

    public ProgressTracker() {
        new ArrayList<>();
        challenges = new ArrayList<>();
        completedChallenges = new ArrayList<>();
    }

    public void addChallenge(Challenges challenge) {
        challenges.add(challenge);
    }

    public void completeChallenge(Client client, Challenges challenge) {
        //tracking progress and completed challenges
        client.incrementProgress(challenge);
        completedChallenges.add(challenge.getChallengeInfo());

        // Provide feedback and encouragement
        System.out.println("Congratulations! You have successfully completed the challenge.");

        // Reward client with points or rewards if he complete the challenge
        System.out.println("You have earned 20 points, for completing the challenges.");
    }

    public void quitChallenge(Client client, Challenges challenge) {
        // Prompt client for confirmation
        System.out.println("Are you sure you want to quit the challenge?");

        // If client confirms, end the challenge, remove challenge progress and mark it as incomplete
        client.decrementProgress(challenge);
        completedChallenges.remove(challenge.getChallengeInfo());

        // Provide a short survey to understand the reason for quitting
        System.out.println("Please help us understanding, to help us improve!");
    }

    public void requestHelp(Client client, String message) {
        //go to use case chat message
        System.out.println("Help request sent. Please wait for assistance.");
    }

    public void provideGuidance(Client client, String guidance) {
        System.out.println("Here's some guidance to overcome the obstacle: " + guidance);
    }

    public void modifyWorkout(Client client) {
        // Detect the problem or discomfort during the workout, display message asking if the client wants to modify or stop training
        System.out.println("It seems you are experiencing some discomfort. Do you want to modify the workout?");

        // If the client chooses to modify, suggest modifications based on fitness level and goals
        System.out.println("Here are some suggested modifications: ...");
    }
}






