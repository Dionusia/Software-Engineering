import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class Questionnaire {
    private List<Question> questions;
    private String title;
    private Scanner scanner;
    
    public Questionnaire() {
        this.title = "Fitness and Dietary Preferences Questionnaire";
        this.questions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        initializeQuestions();
    }

    private void initializeQuestions() {
        // Default questions for the questionnaire
        addQuestion(new Question("What is your current fitness level?", true));
        addQuestion(new Question("What is your primary fitness goal?", true));
        addQuestion(new Question("Do you have any dietary restrictions? If so, please specify.", false));
    }

    public void updateQuestionnaire() {
        System.out.println("Updating the Questionnaire");
        System.out.println("Current questions:");
        present();
        System.out.println("Enter the index of the question you want to update (1-" + questions.size() + "):");
        int index = readIndex();
        scanner.nextLine(); // Consume the newline character

        System.out.println("Enter the new question text:");
        String newQuestionText = scanner.nextLine();

        editQuestion(index - 1, newQuestionText);

        System.out.println("Question updated successfully!");
    }

    private int readIndex() {
        int index = 0;
        boolean isValidInput = false;
        do {
            try {
                System.out.print("Enter the index: ");
                index = scanner.nextInt();
                if (index >= 1 && index <= questions.size()) {
                    isValidInput = true;
                } else {
                    System.out.println("Invalid index. Please enter a valid index.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        } while (!isValidInput);
        return index;
    }
    
    public List<Question> getQuestions() {
        return questions;
    }
    
    public void setQuestions(List<Question> questions) {
        this.questions = questions;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public void addQuestion(Question question) {
        questions.add(question);
    }
    
    public void removeQuestion(int index) {
        questions.remove(index);
    }
    
    public void present() {
        System.out.println(title);
        for (int i = 0; i < questions.size(); i++) {
            System.out.println((i + 1) + ". " + questions.get(i).getQuestionText());
        }
    }
    
    public void editQuestion(int index, String newQuestionText) {
        questions.get(index).setQuestionText(newQuestionText);
    }
    
    public List<String> fillOut() {
        List<String> answers = new ArrayList<>();
        for (Question question : questions) {
            System.out.println(question.getQuestionText());
            String answer = scanner.nextLine();
            if (question.isRequired() && answer.trim().isEmpty()) {
                System.out.println("This question is required. Please enter an answer.");
                answer = scanner.nextLine();
            }
            answers.add(answer);
        }
        return answers;
    }
    
    public static class Question {
        private String questionText;
        private boolean required;
        
        public Question(String questionText, boolean required) {
            this.questionText = questionText;
            this.required = required;
        }
        
        public String getQuestionText() {
            return questionText;
        }
        
        public void setQuestionText(String questionText) {
            this.questionText = questionText;
        }
        
        public boolean isRequired() {
            return required;
        }
        
        public void setRequired(boolean required) {
            this.required = required;
        }
    }
}