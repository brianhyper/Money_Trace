package PersonalFinanceManager.UI;

import PersonalFinanceManager.AppTheme;
import PersonalFinanceManager.Components.GlassBox;
import PersonalFinanceManager.Models.Transaction;
import PersonalFinanceManager.Utils.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class CalendarPanel extends JPanel {
    public CalendarPanel(JFrame parentFrame) {
        setLayout(new BorderLayout());
        setBackground(AppTheme.getBackgroundColor());

        JLabel title = new JLabel("Calendar");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(AppTheme.getTextColor());
        add(title, BorderLayout.NORTH);

        // Placeholder: Use a calendar component or custom calendar
        JPanel calendarBox = new GlassBox(20);
        calendarBox.setPreferredSize(new Dimension(600, 400));
        calendarBox.setLayout(new BorderLayout());
        JLabel calendarLabel = new JLabel("Calendar Widget Here", SwingConstants.CENTER);
        calendarLabel.setForeground(AppTheme.getSecondaryTextColor());
        calendarBox.add(calendarLabel, BorderLayout.CENTER);

        add(calendarBox, BorderLayout.CENTER);

        // Below calendar: summary and upcoming
        JPanel bottomPanel = new JPanel(new GridLayout(1, 2, 20, 0));
        bottomPanel.setOpaque(false);

        GlassBox summaryBox = new GlassBox(15);
        summaryBox.setLayout(new BoxLayout(summaryBox, BoxLayout.Y_AXIS));
        JLabel summaryLabel = new JLabel("Monthly Summary");
        summaryLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        summaryLabel.setForeground(AppTheme.getTextColor());
        summaryBox.add(summaryLabel);
        summaryBox.add(new JLabel("Income: $0.00"));
        summaryBox.add(new JLabel("Spending: $0.00"));

        GlassBox upcomingBox = new GlassBox(15);
        upcomingBox.setLayout(new BoxLayout(upcomingBox, BoxLayout.Y_AXIS));
        JLabel upcomingLabel = new JLabel("Upcoming Expenses");
        upcomingLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        upcomingLabel.setForeground(AppTheme.getTextColor());
        upcomingBox.add(upcomingLabel);
        upcomingBox.add(new JLabel("No upcoming expenses."));

        bottomPanel.add(summaryBox);
        bottomPanel.add(upcomingBox);

        add(bottomPanel, BorderLayout.SOUTH);
    }
}
