package PersonalFinanceManager.Models;

import java.io.Serializable;
import java.security.MessageDigest;
import java.util.Base64;

public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private String username;
    private String passwordHash;
    private String email;
    private boolean darkMode;

    public User(String username, String password, String email) {
        this.username = username;
        this.passwordHash = hashPassword(password);
        this.email = email;
        this.darkMode = true;
    }

    public String getUsername() { return username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public boolean isDarkMode() { return darkMode; }
    public void setDarkMode(boolean darkMode) { this.darkMode = darkMode; }

    public boolean checkPassword(String password) {
        return hashPassword(password).equals(passwordHash);
    }

    public void changePassword(String oldPassword, String newPassword) throws Exception {
        if (!checkPassword(oldPassword)) {
            throw new Exception("Old password is incorrect");
        }
        this.passwordHash = hashPassword(newPassword);
    }

    private String hashPassword(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hash = md.digest(password.getBytes("UTF-8"));
            return Base64.getEncoder().encodeToString(hash);
        } catch (Exception e) {
            e.printStackTrace();
            // Fallback to plain password if hashing fails
            return password;
        }
    }
}

