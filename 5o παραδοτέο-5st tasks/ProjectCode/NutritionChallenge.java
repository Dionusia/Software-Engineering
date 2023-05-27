import java.util.List;

public class NutritionChallenge extends Challenges {
    public NutritionChallenge(String name, String description, String rules, int duration, String requirements) {
        setName(name);
        setDescription(description);
        setRules(rules);
        setDuration(duration);
        setRequirements(requirements);
    }

    public void addNutritionChallenge(List<Challenges> challengeList, NutritionChallenge challenge) {
        challengeList.add(challenge);
        System.out.println("Nutrition challenge added successfully.");
    }

    @Override
    public String getChallengeInfo() {
        return super.getChallengeInfo();
    }
}