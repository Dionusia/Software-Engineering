import java.util.ArrayList;
import java.util.List;

public class FitnessProgram extends RecommendedPrograms {
    private String name;
    private int duration;
    private List<String> exercises;

    public FitnessProgram(String name, int duration) {
        this.name = name;
        this.duration = duration;
        this.exercises = new ArrayList<>();
    }

    public void addExercise(String exercise) {
        exercises.add(exercise);
    }

    public void removeExercise(String exercise) {
        exercises.remove(exercise);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public List<String> getExercises() {
        return exercises;
    }

    @Override
    public String toString() {
        return getName();
    }
}