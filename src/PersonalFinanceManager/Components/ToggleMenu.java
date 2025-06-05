package PersonalFinanceManager.Components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

//not implemented yet😥( dk whats wrong)

public class ToggleMenu extends JPanel {
    private JButton toggleButton;
    private JPanel menuPanel;
    private boolean isOpen = false;

    public ToggleMenu(JPanel menuContent) {
        setLayout(new BorderLayout());
        toggleButton = new JButton("☰");
        toggleButton.setFont(new Font("Segoe UI", Font.BOLD, 24));
        toggleButton.setFocusPainted(false);
        toggleButton.setBorderPainted(false);
        toggleButton.setBackground(new Color(0,0,0,0));
        toggleButton.setForeground(Color.WHITE);

        menuPanel = menuContent;
        menuPanel.setVisible(isOpen);
        add(toggleButton, BorderLayout.NORTH);
        add(menuPanel, BorderLayout.CENTER);

        toggleButton.addActionListener(e -> toggleMenu());
    }

    public void toggleMenu() {
        isOpen = !isOpen;
        menuPanel.setVisible(isOpen);
        revalidate();
        repaint();
    }
}
