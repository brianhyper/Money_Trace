package PersonalFinanceManager.UI;

import PersonalFinanceManager.AppTheme;
import PersonalFinanceManager.Components.GlassBox;
import PersonalFinanceManager.Components.GraphComponent;
import PersonalFinanceManager.Components.NavigationBar;
import PersonalFinanceManager.Models.Transaction;
import PersonalFinanceManager.Utils.DateUtils;

import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.net.URL;

public class DashboardPanel extends JPanel {
    private final JFrame parentFrame;
    private final NavigationBar navigationBar;
    private JLabel dateLabel;
    private final GraphComponent graphComponent;
    private ArrayList<Transaction> upcomingTransactions;

    // Sample data (to be replaced with actual data loading)
    private double totalIncome = 9467.20;
    private double totalExpenses = 8645.15;
    private double netIncome = 822.05;

    public DashboardPanel(JFrame parentFrame) {
        this.parentFrame = parentFrame;
        setLayout(new BorderLayout());
        setBackground(AppTheme.getBackgroundColor());

        // Initialize navigation bar (visible only on this panel)
        navigationBar = new NavigationBar(parentFrame);
        add(navigationBar, BorderLayout.WEST);

        // Initialize data (mock data for demo)
        initializeData();

        // Main content panel
        JPanel contentPanel = new JPanel(new BorderLayout());
        contentPanel.setOpaque(false);

        // Add top bar (header)
        JPanel topBar = createTopBar();
        contentPanel.add(topBar, BorderLayout.NORTH);

        // Main dashboard content
        JPanel dashboardContent = new JPanel(new GridBagLayout());
        dashboardContent.setOpaque(false);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.BOTH;

        // First row - Upcoming payments
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.weightx = 1.0;
        gbc.weighty = 0.2;
        JPanel upcomingPanel = createUpcomingPanel();
        dashboardContent.add(upcomingPanel, gbc);

        // Second row - Financial summaries
        // Income panel
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.weightx = 1.0;
        gbc.weighty = 0.3;
        JPanel incomePanel = createSummaryPanel("Total Income", totalIncome, AppTheme.INCOME_GREEN);
        dashboardContent.add(incomePanel, gbc);

        // Expenses panel
        gbc.gridx = 1;
        gbc.gridy = 1;
        JPanel expensesPanel = createSummaryPanel("Total Expenses", totalExpenses, AppTheme.EXPENSE_RED);
        dashboardContent.add(expensesPanel, gbc);

        // Net Income panel
        gbc.gridx = 2;
        gbc.gridy = 1;
        JPanel netIncomePanel = createSummaryPanel("Net Income", netIncome,
                netIncome >= 0 ? AppTheme.INCOME_GREEN : AppTheme.EXPENSE_RED);
        dashboardContent.add(netIncomePanel, gbc);

        // Graph panel
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 3;
        gbc.weighty = 0.5;
        graphComponent = new GraphComponent();
        dashboardContent.add(graphComponent, gbc);

        // Create scroll pane for content
        JScrollPane scrollPane = new JScrollPane(dashboardContent);
        scrollPane.setBorder(null);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        contentPanel.add(scrollPane, BorderLayout.CENTER);

        add(contentPanel, BorderLayout.CENTER);
    }

    private void initializeData() {
        // Mock upcoming transactions
        upcomingTransactions = new ArrayList<>();
        upcomingTransactions.add(new Transaction("Landscaping", "Other", 70.00, DateUtils.currentDatePlusHours(16)));
        upcomingTransactions.add(new Transaction("Capital One", "CreditCard", 100.00, DateUtils.currentDatePlusDays(1)));
        upcomingTransactions.add(new Transaction("Chase", "Kids", 82.00, DateUtils.currentDatePlusDays(1)));
    }

    private JPanel createTopBar() {
        JPanel topBar = new JPanel(new BorderLayout());
        topBar.setOpaque(false);
        topBar.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));

        // Dashboard title
        JLabel titleLabel = new JLabel("Dashboard");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(AppTheme.getTextColor());
        topBar.add(titleLabel, BorderLayout.WEST);

        // Time frame selector
        JPanel timeFrame = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        timeFrame.setOpaque(false);

        JButton thisMonthBtn = new JButton("This Month");
        thisMonthBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        thisMonthBtn.setForeground(Color.WHITE);
        thisMonthBtn.setBackground(AppTheme.ACCENT_BLUE);
        thisMonthBtn.setBorderPainted(false);
        thisMonthBtn.setFocusPainted(false);
        thisMonthBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        // Null-safe icon loading
        URL calendarIconUrl = getClass().getResource("src/PersonalFinanceManager/Resources/icons/calendar.png");
        if (calendarIconUrl != null) {
            thisMonthBtn.setIcon(new ImageIcon(calendarIconUrl));
        } else {
            System.err.println("Missing calendar.png na ni ya dashboard");
        }

        // Add date and time refresh
        dateLabel = new JLabel(DateUtils.getCurrentFormattedDate());
        dateLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        dateLabel.setForeground(AppTheme.getSecondaryTextColor());

        timeFrame.add(thisMonthBtn);

        topBar.add(timeFrame, BorderLayout.EAST);

        return topBar;
    }

    private JPanel createUpcomingPanel() {
        GlassBox panel = new GlassBox(15);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JLabel titleLabel = new JLabel("Upcoming");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        titleLabel.setForeground(AppTheme.getTextColor());
        panel.add(titleLabel, BorderLayout.NORTH);

        // Create panel for upcoming transactions
        JPanel transactionsPanel = new JPanel(new GridLayout(1, 0, 10, 0));
        transactionsPanel.setOpaque(false);

        // Add transaction cards
        for (Transaction transaction : upcomingTransactions) {
            transactionsPanel.add(createTransactionCard(transaction));
        }

        panel.add(transactionsPanel, BorderLayout.CENTER);

        return panel;
    }

    private JPanel createTransactionCard(Transaction transaction) {
        GlassBox card = new GlassBox(12, new Color(30, 35, 40, 220), 0.8f);
        card.setLayout(new BorderLayout(10, 10));
        card.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        // Category and name
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setOpaque(false);

        // Add icon based on category
        JLabel iconLabel = new JLabel();
        ImageIcon icon = getIconForCategory(transaction.getCategory());
        if (icon != null) {
            iconLabel.setIcon(icon);
        } else {
            iconLabel.setText("No icon");
        }
        topPanel.add(iconLabel, BorderLayout.WEST);

        JPanel categoryPanel = new JPanel(new BorderLayout());
        categoryPanel.setOpaque(false);

        JLabel categoryLabel = new JLabel(transaction.getCategory());
        categoryLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        categoryLabel.setForeground(AppTheme.getSecondaryTextColor());
        categoryPanel.add(categoryLabel, BorderLayout.NORTH);

        JLabel nameLabel = new JLabel(transaction.getDescription());
        nameLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        nameLabel.setForeground(AppTheme.getTextColor());
        categoryPanel.add(nameLabel, BorderLayout.CENTER);

        topPanel.add(categoryPanel, BorderLayout.CENTER);

        card.add(topPanel, BorderLayout.NORTH);

        // Amount
        JLabel amountLabel = new JLabel("Amount");
        amountLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        amountLabel.setForeground(AppTheme.getSecondaryTextColor());

        JLabel amountValueLabel = new JLabel(formatCurrency(transaction.getAmount()));
        amountValueLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        amountValueLabel.setForeground(AppTheme.getTextColor());

        JPanel amountPanel = new JPanel(new BorderLayout());
        amountPanel.setOpaque(false);
        amountPanel.add(amountLabel, BorderLayout.NORTH);
        amountPanel.add(amountValueLabel, BorderLayout.CENTER);

        card.add(amountPanel, BorderLayout.CENTER);

        // Due date
        JLabel dueLabel = new JLabel(formatDueTime(transaction.getDate()));
        dueLabel.setFont(new Font("Segoe UI", Font.ITALIC, 12));
        dueLabel.setForeground(AppTheme.getSecondaryTextColor());

        card.add(dueLabel, BorderLayout.SOUTH);

        return card;
    }

    private JPanel createSummaryPanel(String title, double amount, Color accentColor) {
        GlassBox panel = new GlassBox(15);
        panel.setLayout(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Icon
        JPanel iconPanel = new JPanel(new BorderLayout());
        iconPanel.setOpaque(false);

        JLabel iconLabel = new JLabel();
        iconLabel.setOpaque(true);
        iconLabel.setBackground(new Color(accentColor.getRed(), accentColor.getGreen(), accentColor.getBlue(), 60));
        iconLabel.setPreferredSize(new Dimension(50, 50));
        iconLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Set icon based on panel type
        ImageIcon icon = null;
        String iconPath = null;
        if (title.contains("Income")) {
            iconPath = "/PersonalFinanceManager/Resources/icons/income_icon.png";
        } else if (title.contains("Expenses")) {
            iconPath = "/PersonalFinanceManager/Resources/icons/expense_icon.png";
        } else {
            iconPath = "/PersonalFinanceManager/Resources/icons/wallet_icon.png";
        }
        URL iconUrl = getClass().getResource(iconPath);
        if (iconUrl != null) {
            icon = new ImageIcon(iconUrl);
            iconLabel.setIcon(icon);
        } else {
            iconLabel.setText("No icon");
            System.err.println("Missing resource: " + iconPath);
        }

        // Round the corners of the icon background
        iconLabel.setBorder(BorderFactory.createEmptyBorder());
        iconPanel.add(iconLabel, BorderLayout.WEST);

        panel.add(iconPanel, BorderLayout.NORTH);

        // Title
        JLabel titleLabel = new JLabel(title);
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        titleLabel.setForeground(AppTheme.getTextColor());
        panel.add(titleLabel, BorderLayout.CENTER);

        // Amount
        JLabel amountLabel = new JLabel(formatCurrency(amount));
        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 26));
        amountLabel.setForeground(accentColor);
        panel.add(amountLabel, BorderLayout.SOUTH);

        return panel;
    }

    private String formatCurrency(double amount) {
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(Locale.US);
        return currencyFormatter.format(amount);
    }

    private String formatDueTime(long timestamp) {
        long currentTime = System.currentTimeMillis();
        long diff = timestamp - currentTime;

        // If due in hours
        if (diff < 24 * 60 * 60 * 1000) {
            long hours = diff / (60 * 60 * 1000);
            return "in about " + hours + " hours";
        } else {
            // If due in days
            long days = diff / (24 * 60 * 60 * 1000);
            return "in " + days + " day" + (days > 1 ? "s" : "");
        }
    }

    private ImageIcon getIconForCategory(String category) {
        String iconPath;
        switch (category.toLowerCase()) {
            case "other":
                iconPath = "/PersonalFinanceManager/Resources/icons/other_icon.png";
                break;
            case "creditcard":
                iconPath = "/PersonalFinanceManager/Resources/icons/credit_card_icon.png";
                break;
            case "kids":
                iconPath = "/PersonalFinanceManager/Resources/icons/kids_icon.png";
                break;
            default:
                iconPath = "/PersonalFinanceManager/Resources/icons/default_icon.png";
        }

        URL iconUrl = getClass().getResource(iconPath);
        if (iconUrl != null) {
            return new ImageIcon(iconUrl);
        } else {
            System.err.println("Missing resource: " + iconPath);
            return null;
        }
    }
}