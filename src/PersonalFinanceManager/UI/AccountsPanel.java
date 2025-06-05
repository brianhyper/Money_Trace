package PersonalFinanceManager.UI;

import PersonalFinanceManager.AppTheme;
import PersonalFinanceManager.Components.GlassBox;
import PersonalFinanceManager.Models.Transaction;

import javax.swing.*;
import java.awt.*;
import java.util.List;

//not implemented yet😥

public class AccountsPanel extends JPanel {
    public AccountsPanel(JFrame parentFrame) {
        setLayout(new BorderLayout());
        setBackground(AppTheme.getBackgroundColor());

        JLabel title = new JLabel("Accounts");
        title.setFont(new Font("Segoe UI", Font.BOLD, 28));
        title.setForeground(AppTheme.getTextColor());
        add(title, BorderLayout.NORTH);

        // Transactions Table
        String[] columns = {"Date", "Description", "Amount", "Type"};
        Object[][] data = {}; // Fill with transaction data

        JTable table = new JTable(data, columns);
        table.setBackground(AppTheme.getCardColor());
        table.setForeground(AppTheme.getTextColor());
        table.setRowHeight(28);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setOpaque(false);
        add(scrollPane, BorderLayout.CENTER);

        JButton exportBtn = new JButton("Export Records");
        exportBtn.setBackground(AppTheme.ACCENT_BLUE);
        exportBtn.setForeground(Color.WHITE);
        add(exportBtn, BorderLayout.SOUTH);
    }
}
