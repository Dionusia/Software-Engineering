public enum HealthGoal {
    LOSE_WEIGHT("Lose Weight"),
    BUILD_MUSCLE("Build Muscle");

    private String name;

    HealthGoal(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static HealthGoal fromString(String value) {
        for (HealthGoal goal : HealthGoal.values()) {
            if (goal.name.equalsIgnoreCase(value)) {
                return goal;
            }
        }
        return null;
    }
}