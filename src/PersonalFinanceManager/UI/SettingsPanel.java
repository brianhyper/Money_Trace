package PersonalFinanceManager.UI;

import PersonalFinanceManager.AppTheme;
import PersonalFinanceManager.Components.GlassBox;
import PersonalFinanceManager.Utils.NotificationManager;

import javax.swing.*;
import java.awt.*;
 //not implemented yet😥
public class SettingsPanel extends JPanel {
    public SettingsPanel(JFrame parentFrame) {
        setLayout(new BorderLayout());
        setBackground(AppTheme.getBackgroundColor());

        JLabel title = new JLabel("Settings");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(AppTheme.getTextColor());
        add(title, BorderLayout.NORTH);

        GlassBox box = new GlassBox(20);
        box.setLayout(new GridLayout(5, 1, 0, 12));
        box.setPreferredSize(new Dimension(400, 250));

        JButton themeBtn = new JButton("Toggle Light/Dark Theme");
        themeBtn.addActionListener(e -> {
            AppTheme.setDarkMode(!AppTheme.isDarkMode());
            NotificationManager.notifyInfo("Theme changed. Restart app to apply.");
        });

        JButton changeLoginBtn = new JButton("Change Login Credentials");
        JButton refreshBtn = new JButton("Refresh App State");
        JButton exitBtn = new JButton("Exit");
        exitBtn.addActionListener(e -> System.exit(0));

        box.add(themeBtn);
        box.add(changeLoginBtn);
        box.add(refreshBtn);
        box.add(exitBtn);

        add(box, BorderLayout.CENTER);
    }
}
