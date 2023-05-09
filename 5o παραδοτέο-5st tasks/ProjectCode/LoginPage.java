
import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class LoginPage {

    private Map<String, Entry> users = new HashMap<>();

    public LoginPage() {
        users.put("Stelios", new Entry("1234", 42, "Male", "High"));
        users.put("Dionusia", new Entry("1234", 21, "Female", "High"));
        users.put("Aggelos", new Entry("1234", 23, "Male", "High"));
    }

    public void register(Scanner scanner) {
        Boolean falseCrend;
        try {
            String username;
            String password;

            do {
                falseCrend = true;
                System.out.println("Enter your name:");
                username = scanner.nextLine();

                if (username.isEmpty()) {
                    System.out.println("Your username is not valid. Please try again.");
                    falseCrend = false;
                }
            } while (!falseCrend);

            do {
                falseCrend = true;
                System.out.println("Enter your password:");
                password = scanner.nextLine();

                if (password.isEmpty()) {
                    System.out.println("Your password is not valid. Please try again.");
                    falseCrend = false;
                }
            } while (!falseCrend);

            System.out.println("Enter your gender:");
            String gender = scanner.nextLine();
            System.out.println("Enter your activity level:");
            String level = scanner.nextLine();
            System.out.println("Enter your age:");
            int age = scanner.nextInt();

            if (falseCrend) {
                users.put(username, new Entry(password, age, gender, level));
                System.out.println("Registration successful!");
            }

        } finally {
            scanner.nextLine();
        }
    }

    public void login(){
        Scanner scanner = new Scanner(System.in);
        try{
            System.out.println("Enter your name:");
            String username = scanner.nextLine();
            System.out.println("Enter your password:");
            String password = scanner.nextLine();
            
            if (!users.containsKey(username)) {
                System.out.println("Invalid username or password.");
            } else {
                Entry user = users.get(username);
                if (user.getPassword().equals(password)) {
                    System.out.println("Login successful!");
                    Menu menu = new Menu();
                    menu.run();
                } else {
                    System.out.println("Invalid username or password.");
                }
            }
        } finally {
            scanner.close();
        }
    }

    public static void main(String[] args) {
        LoginPage loginPage = new LoginPage();
        Scanner scanner = new Scanner(System.in);
        try {
            String choiceStr;
            int choice = 1;
            boolean isLogin = false;
            boolean reg = false;

            while (!isLogin) {
                System.out.println("##############  FIND YOUR BALANCE  ##############");
                if (!reg) {
                    System.out.println("Enter 1 to register.");
                }
                System.out.println("Enter 2 to log in.");
                System.out.println("Enter 3 to exit.");

                choiceStr = scanner.nextLine();
                try {
                    choice = Integer.parseInt(choiceStr);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid choice.");
                    continue;
                }

                if (choice == 1) {
                    loginPage.register(scanner);
                    reg = true;
                } else if (choice == 2) {
                    loginPage.login();
                    isLogin = true;
                } else if (choice == 3) {
                    System.out.println("Exiting program...");
                    break;
                } else {
                    System.out.println("Invalid choice.");
                }
            }
        } finally {
            scanner.close();
        }
    }
    // Προσαρμοσμένη κλάση για την αποθήκευση των πεδίων
    public  class Entry {
        private String password;
        private int age;
        private String gender;
        private String level;

        public Entry(){}
        public Entry(String password, int age,String  gender ,String level) {
            this.password = password;
            this.age = age;
            this.gender = gender;
            this.level=level;
        }

        public String getPassword() {
            return password;
        }

        public int getAge() {
            return age;
        }

        public String getGender() {
            return gender;
        }
        public String getLevel(){
            return level;
        }
        public void setPassword(String password){
            this.password=password;
        }
        public void setAge(int age){
            this.age=age;
        }
    }
}