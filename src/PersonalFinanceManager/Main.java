package PersonalFinanceManager;

import PersonalFinanceManager.UI.WelcomeScreen;
import PersonalFinanceManager.Utils.FileManager;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {
        // dark mode
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Initialize file system
        FileManager.initializeStorage();

        // more modern look
        UIManager.put("Button.arc", 15);
        UIManager.put("Component.arc", 15);
        UIManager.put("ProgressBar.arc", 15);
        UIManager.put("TextComponent.arc", 15);

        // Launch app with splash effect (writing effect) idk
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Personal Finance Manager");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(1000, 700));
            frame.setLocationRelativeTo(null);

            // Set app icon
            ImageIcon icon = new ImageIcon(Main.class.getResource("/PersonalFinanceManager/Resources/icons/app_icon.png"));
            frame.setIconImage(icon.getImage());

            // Load welcome screen
            frame.add(new WelcomeScreen(frame));
            frame.setVisible(true);
        });
    }
}
