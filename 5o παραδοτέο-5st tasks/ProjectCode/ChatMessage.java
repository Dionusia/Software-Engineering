import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ChatMessage {
    private Map<String, List<String>> conversations;
    private Scanner scanner;

    public ChatMessage() {
        this.conversations = new HashMap<>();
        this.scanner = new Scanner(System.in);
    }

    public void MessageMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("--------------- FOOD DIARY ---------------");
            System.out.println("1. Chat with users");
            System.out.println("2. Search user");
            System.out.println("3. Create group chat");
            System.out.println("4. Back to main menu");
            System.out.println("-------------------------------------------");
            int choice = readChoice();
            switch (choice) {
                case 1:
                    communicateWithUser();
                    break;
                case 2:
                    search();
                    break;
                case 3:
                    createGroupChat();
                    break;
                case 4:
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

    public void displayRegisteredUsers(HashMap<String, String> registeredUsers) {
        System.out.println("Registered Users:");
        int index = 1;
        for (String username : registeredUsers.keySet()) {
            System.out.println(index + ". " + username);
            index++;
        }
    }

    public void search() {
        HashMap<String, String> registeredUsers = LoginPage.getRegisteredUsers();
    
        System.out.println("Enter the keyword to search for a user:");
        String keyword = scanner.nextLine();
    
        List<String> matchingUsers = searchUser(keyword, registeredUsers);
    
        if (matchingUsers.isEmpty()) {
            System.out.println("No matching users found.");
        } else {
            System.out.println("Matching Users:");
            displayUsersWithIndex(matchingUsers);
    
            System.out.println("Select a user to send a message:");
            int userChoice = readChoice();
    
            if (userChoice >= 1 && userChoice <= matchingUsers.size()) {
                String selectedUser = matchingUsers.get(userChoice - 1);
                System.out.println("You selected: " + selectedUser);
    
                // Perform message sending logic with the selected user
                System.out.println("Enter your message:");
                String message = scanner.nextLine();
    
                sendMessage("your_username", selectedUser, message); // Replace "your_username" with the actual username of the sender
    
                System.out.println("Message sent successfully!");
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    public List<String> searchUser(String keyword, HashMap<String, String> registeredUsers) {
        List<String> matchingUsers = new ArrayList<>();
    
        for (String username : registeredUsers.keySet()) {
            if (username.toLowerCase().contains(keyword.toLowerCase())) {
                matchingUsers.add(username);
            }
        }
    
        return matchingUsers;
    }
    
    private void displayUsersWithIndex(List<String> users) {
        for (int i = 0; i < users.size(); i++) {
            String name = users.get(i);
            System.out.println((i + 1) + ". " + name);
        }
    }

    public void communicateWithUser() {
        HashMap<String, String> registeredUsers = LoginPage.getRegisteredUsers();
        
        System.out.println("Select a user to communicate with:");
        displayRegisteredUsers(registeredUsers);
        
        int userChoice = readChoice();
        
        List<String> usernames = new ArrayList<>(registeredUsers.keySet());
        if (userChoice >= 1 && userChoice <= usernames.size()) {
            String selectedUser = usernames.get(userChoice - 1);
            System.out.println("You selected: " + selectedUser);
            
            System.out.println("Enter your message:");
            String message = scanner.nextLine();
            
            sendMessage("your_username", selectedUser, message); // Replace "your_username" with the actual username of the sender
            
            System.out.println("Message sent successfully!");
        } else {
            System.out.println("Invalid choice.");
        }
    }

    public void openChatWindow(String user, String recipient) {
        String chatId = getChatId(user, recipient);
        if (!conversations.containsKey(chatId)) {
            conversations.put(chatId, new ArrayList<>());
        }
    }

    public void sendMessage(String user, String recipient, String message) {
        String chatId = getChatId(user, recipient);
        List<String> conversation = conversations.get(chatId);
        if (conversation != null) {
            conversation.add(user + ": " + message);
        }
    }


    public void displayChatWindow(String user, String sender) {
        String chatId = getChatId(user, sender);
        // Display chat window for the sender
        List<String> conversation = conversations.get(chatId);
        if (conversation != null) {
            // Display conversation in the chat window
            for (String message : conversation) {
                System.out.println(message); // Display the message
            }
        }
    }

    public void notifyUserUnavailable(String user) {
        //implementation
    }

    public void createGroupChat() {
        HashMap<String, String> registeredUsers = LoginPage.getRegisteredUsers();
        
        System.out.println("Select users to create a group chat:");
        displayRegisteredUsers(registeredUsers);
        
        System.out.println("Enter the numbers of the users (comma-separated) to add to the group chat:");
        String userInput = scanner.nextLine();
        List<String> selectedUsers = new ArrayList<>();
        
        // Extract the selected users based on the user's input
        String[] userNumbers = userInput.split(",");
        for (String number : userNumbers) {
            int index = Integer.parseInt(number.trim()) - 1;
            if (index >= 0 && index < registeredUsers.size()) {
                String selectedUser = new ArrayList<>(registeredUsers.keySet()).get(index);
                selectedUsers.add(selectedUser);
            }
        }
        
        if (selectedUsers.isEmpty()) {
            System.out.println("No users selected for the group chat.");
        } else {
            System.out.println("Group chat created with the following users:");
            for (String user : selectedUsers) {
                System.out.println("- " + user);
            }
            
            System.out.println("Enter your message:");
            String message = scanner.nextLine();
            
            // Send the message to the group chat members
            for (String user : selectedUsers) {
                sendMessage("your_username", user, message); // Replace "your_username" with the actual username of the sender
            }
            
            System.out.println("Message sent successfully to the group chat members!");
        }
    }

    private String getChatId(String user, String recipient) {
        return user.compareTo(recipient) < 0 ? user + "-" + recipient : recipient + "-" + user;
    }
}