package PersonalFinanceManager.UI;

import PersonalFinanceManager.AppTheme;
import PersonalFinanceManager.Components.GlassBox;
import PersonalFinanceManager.Models.Budget;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

//not implemented yet😥

public class BudgetPanel extends JPanel {
    private List<Budget> budgets;

    public BudgetPanel() {
        setLayout(new BorderLayout());
        setBackground(AppTheme.getBackgroundColor());

        JLabel title = new JLabel("Budgets");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(AppTheme.getTextColor());
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setOpaque(false);
        topBar.add(title);
        add(topBar, BorderLayout.NORTH);

        // some data
        budgets = new ArrayList<>();
        long now = System.currentTimeMillis();
        long month = 2592000000L; // 30 days in ms
        budgets.add(new Budget("Food", 500, now, now + month, ""));
        budgets.get(0).setSpent(550); // ikiexceeded
        budgets.add(new Budget("Transport", 200, now, now + month, ""));
        budgets.get(1).setSpent(120);
        budgets.add(new Budget("Entertainment", 150, now, now + month, ""));
        budgets.get(2).setSpent(80);

        JPanel listPanel = new JPanel();
        listPanel.setOpaque(false);
        listPanel.setLayout(new BoxLayout(listPanel, BoxLayout.Y_AXIS));
        listPanel.setBorder(BorderFactory.createEmptyBorder(16, 32, 16, 32));

        for (Budget b : budgets) {
            listPanel.add(createBudgetCard(b));
            listPanel.add(Box.createVerticalStrut(16));
        }

        JScrollPane scrollPane = new JScrollPane(listPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(null);
        add(scrollPane, BorderLayout.CENTER);

        JButton addBudgetBtn = new JButton("Add Budget");
        addBudgetBtn.setBackground(AppTheme.ACCENT_BLUE);
        addBudgetBtn.setForeground(Color.WHITE);
        addBudgetBtn.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addBudgetBtn.setFocusPainted(false);
        addBudgetBtn.setBorder(BorderFactory.createEmptyBorder(8, 24, 8, 24));
        JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnPanel.setOpaque(false);
        btnPanel.add(addBudgetBtn);
        add(btnPanel, BorderLayout.SOUTH);
    }

    private JPanel createBudgetCard(Budget budget) {
        Color cardColor = AppTheme.CARD_DARK;
        GlassBox card = new GlassBox(18, cardColor, 0.92f);
        card.setLayout(new BorderLayout());
        card.setBorder(BorderFactory.createEmptyBorder(18, 24, 18, 24));

        JLabel catLabel = new JLabel(budget.getCategory());
        catLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        catLabel.setForeground(AppTheme.getTextColor());

        JLabel limitLabel = new JLabel("Limit: $" + String.format("%.2f", budget.getLimit()));
        limitLabel.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        limitLabel.setForeground(AppTheme.getSecondaryTextColor());

        JLabel spentLabel = new JLabel("Spent: $" + String.format("%.2f", budget.getSpent()));
        spentLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        spentLabel.setForeground(budget.isExceeded() ? AppTheme.EXPENSE_RED : AppTheme.INCOME_GREEN);

        JPanel infoPanel = new JPanel(new GridLayout(2, 1));
        infoPanel.setOpaque(false);
        infoPanel.add(limitLabel);
        infoPanel.add(spentLabel);

        card.add(catLabel, BorderLayout.NORTH);
        card.add(infoPanel, BorderLayout.CENTER);

        if (budget.isExceeded()) {
            JLabel alert = new JLabel("Limit Exceeded!");
            alert.setForeground(AppTheme.EXPENSE_RED);
            alert.setFont(new Font("Segoe UI", Font.BOLD, 14));
            card.add(alert, BorderLayout.SOUTH);
        }

        return card;
    }
}
