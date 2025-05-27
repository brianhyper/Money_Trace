package PersonalFinanceManager.Auth;

import PersonalFinanceManager.Models.User;
import PersonalFinanceManager.Utils.FileManager;

public class AuthManager {
    public static boolean register(String username, String password) {
        if (username.isEmpty() || password.isEmpty()) return false;
        // FIX: Pass the raw password, not password.hashCode()
        User user = new User(username, password, "The email will come later");
        return FileManager.saveUser(user);
    }

    public static User login(String username, String password) {
        return FileManager.authenticateUser(username, password);
    }
}