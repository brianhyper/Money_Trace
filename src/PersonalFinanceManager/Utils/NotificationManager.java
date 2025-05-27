package PersonalFinanceManager.Utils;

import javax.swing.*;

public class NotificationManager {
    public static void notifyInfo(String message) {
        JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void notifyWarning(String message) {
        JOptionPane.showMessageDialog(null, message, "Warning", JOptionPane.WARNING_MESSAGE);
    }

    public static void notifyError(String message) {
        JOptionPane.showMessageDialog(null, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
