package PersonalFinanceManager.Components;

import PersonalFinanceManager.AppTheme;
import PersonalFinanceManager.Models.Budget;
import PersonalFinanceManager.Models.Debt;
import PersonalFinanceManager.UI.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class NavigationBar extends JPanel {
    private final JFrame parentFrame;
    private final List<NavItem> navItems;
    private int selectedIndex = 0;

    public NavigationBar(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        this.navItems = new ArrayList<>();

        setPreferredSize(new Dimension(80, 0));
        setBackground(new Color(15, 20, 25));
        setLayout(new BorderLayout());

        // App logo at top
        JLabel logoLabel = new JLabel(new ImageIcon(getClass().getResource("/Resources/icons/app_logo_small.png")));
        logoLabel.setHorizontalAlignment(JLabel.CENTER);
        logoLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 30, 0));
        add(logoLabel, BorderLayout.NORTH);

        // Navigation items in center
        JPanel navPanel = new JPanel(new GridLayout(6, 1, 0, 15));
        navPanel.setOpaque(false);
        navPanel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        // Add nav items
        addNavItem(navPanel, "Dashboard", "/Resources/icons/dashboard_icon.png", DashboardPanel.class);
        addNavItem(navPanel, "Calendar", "/Resources/icons/calendar_icon.png", CalendarPanel.class);
        addNavItem(navPanel, "Accounts", "/Resources/icons/accounts_icon.png", AccountsPanel.class);
        addNavItem(navPanel, "Budget", "/Resources/icons/budget_icon.png", BudgetPanel.class);
        addNavItem(navPanel, "Debt", "/Resources/icons/debt_icon.png", DebtPanel.class);
        addNavItem(navPanel, "Settings", "/Resources/icons/settings_icon.png", SettingsPanel.class);

        JPanel navCenterWrapper = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        navCenterWrapper.setOpaque(false);
        navCenterWrapper.add(navPanel);

        add(navCenterWrapper, BorderLayout.CENTER);

        // Select first item by default
        setSelectedIndex(0);
    }

    private void addNavItem(JPanel container, String name, String iconPath, Class<? extends JPanel> panelClass) {
        NavItem item = new NavItem(name, iconPath, panelClass);
        navItems.add(item);
        container.add(item);

        // Add click handler
        final int index = navItems.size() - 1;
        item.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (selectedIndex != index) {
                    setSelectedIndex(index);
                    navigateTo(panelClass);
                }
            }
        });
    }

    public void setSelectedIndex(int index) {
        if (index >= 0 && index < navItems.size()) {
            // Deselect old item
            if (selectedIndex >= 0 && selectedIndex < navItems.size()) {
                navItems.get(selectedIndex).setSelected(false);
            }

            // Select new item
            selectedIndex = index;
            navItems.get(selectedIndex).setSelected(true);
        }
    }

    private void navigateTo(Class<? extends JPanel> panelClass) {
        try {
            // Create instance of the panel
            JPanel targetPanel = panelClass.getDeclaredConstructor(JFrame.class).newInstance(parentFrame);

            // Replace current content
            Container contentPane = parentFrame.getContentPane();
            contentPane.removeAll();
            contentPane.add(targetPanel);

            // Refresh UI
            parentFrame.revalidate();
            parentFrame.repaint();
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(parentFrame,
                    "Error navigating to page: " + e.getMessage(),
                    "Navigation Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Inner class for navigation items
    private class NavItem extends JPanel {
        private final String name;
        private final JLabel iconLabel;
        private final JPanel selectionIndicator;
        private boolean isSelected = false;

        public NavItem(String name, String iconPath, Class<? extends JPanel> panelClass) {
            this.name = name;
            setLayout(new BorderLayout());
            setOpaque(false);
            setCursor(new Cursor(Cursor.HAND_CURSOR));

            // Selection indicator
            selectionIndicator = new JPanel();
            selectionIndicator.setPreferredSize(new Dimension(4, 0));
            selectionIndicator.setBackground(AppTheme.ACCENT_BLUE);
            selectionIndicator.setVisible(false);
            add(selectionIndicator, BorderLayout.WEST);

            // Icon
            iconLabel = new JLabel(new ImageIcon(getClass().getResource(iconPath)));
            iconLabel.setHorizontalAlignment(JLabel.CENTER);
            add(iconLabel, BorderLayout.CENTER);

            // Item label
            JLabel nameLabel = new JLabel(name);
            nameLabel.setFont(new Font("Segoe UI", Font.PLAIN, 10));
            nameLabel.setForeground(AppTheme.TEXT_SECONDARY);
            nameLabel.setHorizontalAlignment(JLabel.CENTER);
            add(nameLabel, BorderLayout.SOUTH);

            // Add hover effects
            addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (!isSelected) {
                        selectionIndicator.setBackground(new Color(AppTheme.ACCENT_BLUE.getRed(),
                                AppTheme.ACCENT_BLUE.getGreen(),
                                AppTheme.ACCENT_BLUE.getBlue(), 100));
                        selectionIndicator.setVisible(true);
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (!isSelected) {
                        selectionIndicator.setVisible(false);
                    }
                }
            });
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
            selectionIndicator.setVisible(selected);
            selectionIndicator.setBackground(selected ? AppTheme.ACCENT_BLUE :
                    new Color(AppTheme.ACCENT_BLUE.getRed(), AppTheme.ACCENT_BLUE.getGreen(), AppTheme.ACCENT_BLUE.getBlue(), 100));

            if (selected) {
                setBorder(BorderFactory.createEmptyBorder(0, 0, 5, 0));
            } else {
                setBorder(BorderFactory.createEmptyBorder());
            }
        }
    }
}
