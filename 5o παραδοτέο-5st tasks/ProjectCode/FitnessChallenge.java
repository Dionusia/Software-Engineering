import java.util.List;

public class FitnessChallenge extends Challenges {
    public FitnessChallenge(String name, String description, String rules, int duration, String requirements) {
        setName(name);
        setDescription(description);
        setRules(rules);
        setDuration(duration);
        setRequirements(requirements);
    }

    public void addFitnessChallenge(List<Challenges> challengeList, FitnessChallenge challenge) {
        challengeList.add(challenge);
        System.out.println("Fitness challenge added successfully.");
    }

    @Override
    public String getChallengeInfo() {
        return super.getChallengeInfo();
    }
}