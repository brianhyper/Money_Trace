package PersonalFinanceManager;

import javax.swing.*;
import java.awt.*;

public class AppTheme {
    // 😂😂😂😂Dark theme color palette
    public static final Color BACKGROUND_DARK = new Color(13, 17, 23);
    public static final Color CARD_DARK = new Color(22, 27, 34, 220);
    public static final Color TEXT_PRIMARY = new Color(255, 255, 255);
    public static final Color TEXT_SECONDARY = new Color(180, 180, 185);
    public static final Color ACCENT_BLUE = new Color(0, 174, 239);
    public static final Color INCOME_GREEN = new Color(46, 204, 113);
    public static final Color EXPENSE_RED = new Color(231, 76, 60);
    public static final Color CARD_BORDER = new Color(60, 65, 75);

    // Alternative light theme
    public static final Color BACKGROUND_LIGHT = new Color(240, 240, 245);
    public static final Color CARD_LIGHT = new Color(255, 255, 255, 230);
    public static final Color TEXT_DARK = new Color(30, 30, 30);

    private static boolean isDarkMode = true;

    public static boolean isDarkMode() {
        return isDarkMode;
    }

    public static void setDarkMode(boolean dark) {
        isDarkMode = dark;
    }

    public static Color getBackgroundColor() {
        return isDarkMode ? BACKGROUND_DARK : BACKGROUND_LIGHT;
    }

    public static Color getCardColor() {
        return isDarkMode ? CARD_DARK : CARD_LIGHT;
    }

    public static Color getTextColor() {
        return isDarkMode ? TEXT_PRIMARY : TEXT_DARK;
    }

    public static Color getSecondaryTextColor() {
        return isDarkMode ? TEXT_SECONDARY : new Color(100, 100, 100);
    }

    // Apply theme to a component
    public static void applyTheme(JComponent component) {
        component.setBackground(getBackgroundColor());
        component.setForeground(getTextColor());
        component.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
}
