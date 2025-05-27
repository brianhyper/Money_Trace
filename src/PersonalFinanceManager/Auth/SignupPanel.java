package PersonalFinanceManager.Auth;

import PersonalFinanceManager.AppTheme;
import PersonalFinanceManager.Components.GlassBox;
import PersonalFinanceManager.Models.User;
import PersonalFinanceManager.Utils.FileManager;
import PersonalFinanceManager.Utils.NotificationManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SignupPanel extends JPanel {
    private JFrame parentFrame;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    public SignupPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new GridBagLayout());
        setBackground(AppTheme.getBackgroundColor());

        GlassBox box = new GlassBox(20);
        box.setPreferredSize(new Dimension(400, 350));
        box.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 30, 10, 30);

        JLabel title = new JLabel("Sign Up");
        title.setFont(new Font("Segoe UI", Font.BOLD, 24));
        title.setForeground(AppTheme.getTextColor());
        gbc.gridy = 0;
        box.add(title, gbc);

        usernameField = new JTextField();
        usernameField.setBorder(BorderFactory.createTitledBorder("Username"));
        gbc.gridy++;
        box.add(usernameField, gbc);

        passwordField = new JPasswordField();
        passwordField.setBorder(BorderFactory.createTitledBorder("Password"));
        gbc.gridy++;
        box.add(passwordField, gbc);

        confirmPasswordField = new JPasswordField();
        confirmPasswordField.setBorder(BorderFactory.createTitledBorder("Confirm Password"));
        gbc.gridy++;
        box.add(confirmPasswordField, gbc);

        JButton signupBtn = new JButton("Sign Up");
        signupBtn.setBackground(AppTheme.ACCENT_BLUE);
        signupBtn.setForeground(Color.WHITE);
        signupBtn.setFont(new Font("Segoe UI", Font.BOLD, 16));
        signupBtn.addActionListener(this::handleSignup);
        gbc.gridy++;
        box.add(signupBtn, gbc);

        JButton loginBtn = new JButton("Already have an account? Login");
        loginBtn.setBorderPainted(false);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setForeground(AppTheme.ACCENT_BLUE);
        loginBtn.addActionListener(e -> goToLogin());
        gbc.gridy++;
        box.add(loginBtn, gbc);

        add(box);
    }

    private void handleSignup(ActionEvent e) {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirm = new String(confirmPasswordField.getPassword());

        if (username.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            NotificationManager.notifyError("Please fill all fields.");
            return;
        }
        if (!password.equals(confirm)) {
            NotificationManager.notifyError("Passwords do not match.");
            return;
        }
        // FIX: Pass the raw password, not password.hashCode()
        User user = new User(username, password, "The email will come later");
        boolean success = FileManager.saveUser(user);
        if (success) {
            NotificationManager.notifyInfo("Registration successful! Please log in.");
            goToLogin();
        } else {
            NotificationManager.notifyError("Username already exists.");
        }
    }

    private void goToLogin() {
        parentFrame.getContentPane().removeAll();
        parentFrame.getContentPane().add(new LoginPanel(parentFrame));
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}