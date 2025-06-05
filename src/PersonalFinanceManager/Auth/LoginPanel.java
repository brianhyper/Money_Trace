package PersonalFinanceManager.Auth;

import PersonalFinanceManager.AppTheme;
import PersonalFinanceManager.Components.GlassBox;
import PersonalFinanceManager.Models.User;
import PersonalFinanceManager.UI.DashboardPanel;
import PersonalFinanceManager.UI.WelcomeScreen;
import PersonalFinanceManager.Utils.FileManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.RoundRectangle2D;

public class LoginPanel extends JPanel {
    private final JFrame parentFrame;
    private final JTextField usernameField;
    private final JPasswordField passwordField;
    private final JButton loginButton;
    private final JButton backButton;
    private JLabel errorLabel;

    public LoginPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBackground(AppTheme.getBackgroundColor());

        // centered content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);

        // main container
        GlassBox mainContainer = new GlassBox(20);
        mainContainer.setPreferredSize(new Dimension(500, 400));
        mainContainer.setLayout(new GridBagLayout());

        // Configure GridBagCo.
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Header with icon
        ImageIcon lockIcon = new ImageIcon(getClass().getResource("/PersonalFinanceManager/Resources/icons/lock_icon.png"));
        JLabel iconLabel = new JLabel(lockIcon);
        iconLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(30, 25, 10, 25);
        mainContainer.add(iconLabel, gbc);

        // Title
        JLabel titleLabel = new JLabel("Login to your account");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 24));
        titleLabel.setForeground(AppTheme.getTextColor());
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(10, 25, 30, 25);
        mainContainer.add(titleLabel, gbc);

        // Error message
        errorLabel = new JLabel("");
        errorLabel.setFont(new Font("Segoe UI", Font.BOLD, 14));
        errorLabel.setForeground(AppTheme.EXPENSE_RED);
        errorLabel.setHorizontalAlignment(JLabel.CENTER);
        errorLabel.setVisible(false);
        gbc.insets = new Insets(0, 25, 15, 25);
        mainContainer.add(errorLabel, gbc);

        // Username field box
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameLabel.setForeground(AppTheme.getTextColor());
        gbc.insets = new Insets(0, 50, 5, 50);
        mainContainer.add(usernameLabel, gbc);

        usernameField = new JTextField();
        usernameField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        usernameField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        customizeTextField(usernameField);
        gbc.insets = new Insets(0, 50, 20, 50);
        mainContainer.add(usernameField, gbc);

        // Password field box
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordLabel.setForeground(AppTheme.getTextColor());
        gbc.insets = new Insets(0, 50, 5, 50);
        mainContainer.add(passwordLabel, gbc);

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createEmptyBorder(10, 15, 10, 15));
        customizeTextField(passwordField);
        gbc.insets = new Insets(0, 50, 30, 50);
        mainContainer.add(passwordField, gbc);

        // Login button
        loginButton = new JButton("Login");
        loginButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(AppTheme.ACCENT_BLUE);
        loginButton.setBorderPainted(false);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        loginButton.setPreferredSize(new Dimension(0, 50));

        // hover effect
        loginButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(AppTheme.ACCENT_BLUE.brighter());
            }

            public void mouseExited(java.awt.event.MouseEvent evt) {
                loginButton.setBackground(AppTheme.ACCENT_BLUE);
            }
        });

        // action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });

        gbc.insets = new Insets(0, 50, 15, 50);
        mainContainer.add(loginButton, gbc);

        // Back button
        backButton = new JButton("Back to Welcome Screen");
        backButton.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        backButton.setForeground(AppTheme.getSecondaryTextColor());
        backButton.setBackground(null);
        backButton.setBorderPainted(false);
        backButton.setFocusPainted(false);
        backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        backButton.setContentAreaFilled(false);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                backToWelcome();
            }
        });

        gbc.insets = new Insets(0, 50, 30, 50);
        mainContainer.add(backButton, gbc);

        // main container to content panel
        contentPanel.add(mainContainer);
        add(contentPanel, BorderLayout.CENTER);

        // 😬🤬Do NOT call getRootPane() here!

        // The default button will be set in addNotify(), after the panel is attached to a root pane.
    }

    @Override
    public void addNotify() {
        super.addNotify();
        // Now the panel is attached to a hierarchy, so getRootPane() is safe
        JRootPane rootPane = getRootPane();
        if (rootPane != null) {
            rootPane.setDefaultButton(loginButton);
        }
    }

    private void customizeTextField(JTextField textField) {
        textField.setBackground(new Color(30, 35, 40));
        textField.setForeground(AppTheme.getTextColor());
        textField.setCaretColor(AppTheme.getTextColor());

        textField.setUI(new javax.swing.plaf.basic.BasicTextFieldUI() {
            @Override
            protected void paintBackground(Graphics g) {
                Graphics2D g2d = (Graphics2D) g;
                g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                g2d.setColor(new Color(30, 35, 40));
                g2d.fill(new RoundRectangle2D.Double(0, 0, textField.getWidth(), textField.getHeight(), 10, 10));

                // Add border
                g2d.setColor(new Color(60, 65, 70));
                g2d.setStroke(new BasicStroke(1.0f));
                g2d.draw(new RoundRectangle2D.Double(0, 0, textField.getWidth() - 1, textField.getHeight() - 1, 10, 10));
            }
        });
    }

    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());

        if (username.isEmpty() || password.isEmpty()) {
            showError("Please enter both username and password");
            return;
        }

        // Authenticate user
        User user = FileManager.authenticateUser(username, password);

        if (user != null) {
            // Login successful
            showDashboard();
        } else {
            // Login failed
            showError("Invalid username or password");
        }
    }

    private void showError(String message) {
        errorLabel.setText(message);
        errorLabel.setVisible(true);

        // "shake" animation for error
        final int originalX = getX();
        final Timer timer = new Timer(30, null);
        final int[] direction = {1};
        final int[] count = {0};

        timer.addActionListener(e -> {
            if (count[0]++ > 10) {
                timer.stop();
                setLocation(originalX, getY());
            } else {
                setLocation(originalX + direction[0] * 5, getY());
                direction[0] *= -1;
            }
        });

        timer.start();
    }

    private void showDashboard() {
        // Navigate to dashboard
        parentFrame.getContentPane().removeAll();
        parentFrame.getContentPane().add(new DashboardPanel(parentFrame));
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    private void backToWelcome() {
        // Navigate back to welcome screen
        parentFrame.getContentPane().removeAll();
        parentFrame.getContentPane().add(new WelcomeScreen(parentFrame));
        parentFrame.revalidate();
        parentFrame.repaint();
    }
}