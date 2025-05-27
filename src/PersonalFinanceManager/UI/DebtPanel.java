package PersonalFinanceManager.UI;

import PersonalFinanceManager.AppTheme;
import PersonalFinanceManager.Components.GlassBox;
import PersonalFinanceManager.Models.Debt;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class DebtPanel extends JPanel {

    public DebtPanel(JFrame parentFrame) {
        setLayout(new BorderLayout());
        setBackground(AppTheme.getBackgroundColor());

        // Top bar
        JPanel topBar = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topBar.setOpaque(false);
        topBar.setBorder(new EmptyBorder(20, 20, 0, 20));

        JLabel titleLabel = new JLabel("Debt");
        titleLabel.setFont(new Font("Segoe UI", Font.BOLD, 28));
        titleLabel.setForeground(AppTheme.getTextColor());
        topBar.add(titleLabel);

        add(topBar, BorderLayout.NORTH);

        // Main Content
        JPanel mainContentPanel = new JPanel();
        mainContentPanel.setOpaque(false);
        mainContentPanel.setLayout(new BoxLayout(mainContentPanel, BoxLayout.Y_AXIS));
        mainContentPanel.setBorder(new EmptyBorder(10, 20, 20, 20));

        // Example Debt (replace with actual data)
        Debt sampleDebt = new Debt("Car Loan", 15000, false, "Bank of America");
        mainContentPanel.add(createDebtCard(sampleDebt));

        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);

        // Footer: Add Debt Button
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setOpaque(false);
        footerPanel.setBorder(new EmptyBorder(0, 20, 20, 20));

        JButton addDebtButton = new JButton("Add Debt");
        addDebtButton.setBackground(AppTheme.ACCENT_BLUE);
        addDebtButton.setForeground(Color.WHITE);
        addDebtButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        addDebtButton.setFocusPainted(false);
        addDebtButton.setBorder(new EmptyBorder(8, 24, 8, 24));

        footerPanel.add(addDebtButton);
        add(footerPanel, BorderLayout.SOUTH);
    }

    private JPanel createDebtCard(Debt debt) {
        GlassBox card = new GlassBox(15, AppTheme.CARD_DARK, 0.9f);
        card.setLayout(new BorderLayout());
        card.setBorder(new EmptyBorder(15, 15, 15, 15));

        // Description
        JLabel descriptionLabel = new JLabel(debt.getDescription());
        descriptionLabel.setFont(new Font("Segoe UI", Font.BOLD, 16));
        descriptionLabel.setForeground(AppTheme.getTextColor());
        card.add(descriptionLabel, BorderLayout.NORTH);

        // Amount Panel
        JPanel amountPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        amountPanel.setOpaque(false);

        String sign = debt.isLoaned() ? "+" : "-";
        Color amountColor = debt.isLoaned() ? AppTheme.INCOME_GREEN : AppTheme.EXPENSE_RED;
        JLabel amountLabel = new JLabel(sign + "$" + String.format("%.2f", debt.getAmount()));

        amountLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        amountLabel.setForeground(amountColor);
        amountPanel.add(amountLabel);

        card.add(amountPanel, BorderLayout.CENTER);

        // Person details
        JLabel personLabel = new JLabel("To/From: " + debt.getPerson());
        personLabel.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        personLabel.setForeground(AppTheme.TEXT_SECONDARY);

        card.add(personLabel, BorderLayout.SOUTH);

        return card;
    }
}
