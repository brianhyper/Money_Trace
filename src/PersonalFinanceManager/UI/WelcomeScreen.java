package PersonalFinanceManager.UI;

import PersonalFinanceManager.AppTheme;
import PersonalFinanceManager.Auth.LoginPanel;
import PersonalFinanceManager.Auth.SignupPanel;
import PersonalFinanceManager.Components.GlassBox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WelcomeScreen extends JPanel {
    private final JFrame parentFrame;
    private final Timer typingTimer;
    private final String welcomeText = "Welcome to Personal Finance Manager";
    private final String descriptionText = "Track, manage, and visualize your finances with ease";
    private int charIndex = 0;
    private final JLabel titleLabel;
    private final JLabel descriptionLabel;
    private final FadePanel buttonPanel;

    public WelcomeScreen(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBackground(AppTheme.getBackgroundColor());

        // Centered content panel
        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setOpaque(false);

        // Main glass container
        GlassBox mainContainer = new GlassBox(20);
        mainContainer.setPreferredSize(new Dimension(600, 400));
        mainContainer.setLayout(new GridBagLayout());

        // GridBagConstraints setup
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(15, 25, 15, 25);

        // App logo/icon
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/PersonalFinanceManager/Resources/icons/app_logo.png"));
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(30, 25, 20, 25);
        mainContainer.add(logoLabel, gbc);

        // Title with typing effect
        titleLabel = new JLabel("");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(AppTheme.getTextColor());
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(10, 25, 5, 25);
        mainContainer.add(titleLabel, gbc);

        // Description
        descriptionLabel = new JLabel("");
        descriptionLabel.setFont(new Font("Segoe UI", Font.PLAIN, 16));
        descriptionLabel.setForeground(AppTheme.getSecondaryTextColor());
        descriptionLabel.setHorizontalAlignment(JLabel.CENTER);
        gbc.insets = new Insets(5, 25, 30, 25);
        mainContainer.add(descriptionLabel, gbc);

        // Button panel with fade-in support
        buttonPanel = new FadePanel(new GridLayout(1, 2, 20, 0));
        buttonPanel.setOpaque(false);

        JButton loginButton = createStyledButton("Login", AppTheme.ACCENT_BLUE);
        JButton signupButton = createStyledButton("Sign Up", new Color(100, 100, 100));

        loginButton.addActionListener(e -> showLoginPanel());
        signupButton.addActionListener(e -> showSignupPanel());

        buttonPanel.add(loginButton);
        buttonPanel.add(signupButton);

        // Initially hide buttons until animation completes
        buttonPanel.setVisible(false);

        gbc.insets = new Insets(20, 40, 40, 40);
        mainContainer.add(buttonPanel, gbc);

        contentPanel.add(mainContainer);
        add(contentPanel, BorderLayout.CENTER);

        // Typing animation
        typingTimer = new Timer(50, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (charIndex <= welcomeText.length()) {
                    titleLabel.setText(welcomeText.substring(0, charIndex));
                    charIndex++;
                } else if (charIndex <= welcomeText.length() + descriptionText.length()) {
                    int descIndex = charIndex - welcomeText.length();
                    descriptionLabel.setText(descriptionText.substring(0, descIndex));
                    charIndex++;
                } else {
                    ((Timer) e.getSource()).stop();
                    fadeInButtons();
                }
            }
        });
        typingTimer.start();
    }

    private void fadeInButtons() {
        buttonPanel.setVisible(true);
        buttonPanel.setAlpha(0.0f); // Start with 0 opacity

        Timer fadeTimer = new Timer(30, new ActionListener() {
            float alpha = 0.0f;
            @Override
            public void actionPerformed(ActionEvent e) {
                alpha += 0.05f;
                if (alpha >= 1.0f) {
                    alpha = 1.0f;
                    ((Timer) e.getSource()).stop();
                }
                buttonPanel.setAlpha(alpha);
                buttonPanel.repaint();
            }
        });
        fadeTimer.start();
    }

    private JButton createStyledButton(String text, Color color) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 16));
        button.setForeground(Color.WHITE);
        button.setBackground(color);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        button.setPreferredSize(new Dimension(120, 50));
        button.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                button.setBackground(color.brighter());
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                button.setBackground(color);
            }
        });
        return button;
    }

    private void showLoginPanel() {
        slideTransition(new LoginPanel(parentFrame));
    }

    private void showSignupPanel() {
        slideTransition(new SignupPanel(parentFrame));
    }

    private void slideTransition(JPanel newPanel) {
        Container contentPane = parentFrame.getContentPane();
        contentPane.removeAll();
        contentPane.add(newPanel);
        parentFrame.revalidate();
        parentFrame.repaint();
    }

    // Custom fade-in panel
    static class FadePanel extends JPanel {
        private float alpha = 1.0f;
        public FadePanel(LayoutManager layout) {
            super(layout);
            setOpaque(false);
        }
        public void setAlpha(float value) { this.alpha = value; }
        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alpha));
            super.paintComponent(g2d);
            g2d.dispose();
        }
    }
}
