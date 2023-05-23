public class WaterImport extends WaterTracker {
    private double weight;
    private ActivityLevel activityLevel;
    
    public WaterImport(int targetIntake, double weight, ActivityLevel activityLevel, Client client) {
        super(targetIntake, client);
        this.weight = weight;
        this.activityLevel = activityLevel;
    }

    public void importWaterIntake(int amount) {
        addWaterIntake(amount);
        System.out.println("Water intake of " + amount + "ml imported successfully.");
    
        if (weight == 0 || activityLevel == null) {
            System.out.println("Please update your weight and activity level.");
        } else {
            calculateWaterIntake();
        }
    }

    private void calculateWaterIntake() {
        double multiplier = getActivityLevelMultiplier(activityLevel);
        int targetIntake = (int) (weight * multiplier * 0.033); //0.033-> μετατρέπεται το βάρος σε κιλά στην αντίστοιχη πρόσληψη σε χιλιοστόλιτρα
        System.out.println("Total water intake for the day: " + getCurrentIntake() + "ml");
    
        if (getCurrentIntake() >= targetIntake) {
            System.out.println("Congratulations! You have reached your water intake goal for the day.");
        } else {
            int remainingIntake = targetIntake - getCurrentIntake();
            System.out.println("You need to consume " + remainingIntake + "ml more to reach your target.");
        }
    }

    private double getActivityLevelMultiplier(ActivityLevel activityLevel) {
        switch (activityLevel) {
            case SLIGHTLY_ACTIVE:
                return 1.2;
            case MODERATELY_ACTIVE:
                return 1.5;
            case VERY_ACTIVE:
                return 1.8;
            case EXTREMELY_ACTIVE:
                return 2.0;
            default:
                return 1.0;
        }
    }

    //setters
    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setActivityLevel(ActivityLevel activityLevel) {
        this.activityLevel = activityLevel;
    }
}