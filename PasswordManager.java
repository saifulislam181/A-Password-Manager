import java.util.*;

public class PasswordManager {
    private Map<String, List<PasswordEntry>> passwords;
    private List<User> users;
    private FileHandler fileHandler;

    public PasswordManager() {
        passwords = new HashMap<>();
        passwords.put("Website", new ArrayList<>());
        passwords.put("DesktopApplication", new ArrayList<>());
        passwords.put("Game", new ArrayList<>());
        
        users = new ArrayList<>();
        fileHandler = new FileHandler();
    }

    public void start() {
        // Load users and passwords from file
        users = fileHandler.loadUserData();
        passwords = fileHandler.loadPasswords();

        // Console-based menu for user actions
    }

    public void registerUser(User user) {
        users.add(user);
        fileHandler.saveUserData(users);  // Save updated user list to file
    }

    public boolean login(String username, String password) {
        for (User user : users) {
            if (user.login(username, password)) {
                return true;
            }
        }
        return false;
    }

    public void addPassword(String category, PasswordEntry entry) {
        passwords.get(category).add(entry);
        fileHandler.savePasswords(passwords);
    }

    public void editPassword(String category, String title, String newPassword) {
        for (PasswordEntry entry : passwords.get(category)) {
            if (entry.getTitle().equals(title)) {
                entry.setPassword(newPassword);
                fileHandler.savePasswords(passwords);
                return;
            }
        }
    }

    public void deletePassword(String category, String title) {
        passwords.get(category).removeIf(entry -> entry.getTitle().equals(title));
        fileHandler.savePasswords(passwords);
    }

    public List<PasswordEntry> viewAllPasswords() {
        List<PasswordEntry> allPasswords = new ArrayList<>();
        for (List<PasswordEntry> list : passwords.values()) {
            allPasswords.addAll(list);
        }
        return allPasswords;
    }
}
