package PersonalFinanceManager.Components;

import PersonalFinanceManager.AppTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class GlassBox extends JPanel {
    private final int cornerRadius;
    private final Color customColor;
    private final float opacity;
    private String title;
    private boolean showBorder;

    public GlassBox(int cornerRadius) {
        this(cornerRadius, null, 0.9f);
    }

    public GlassBox(int cornerRadius, Color customColor, float opacity) {
        this.cornerRadius = cornerRadius;
        this.customColor = customColor;
        this.opacity = opacity;
        this.showBorder = true;

        setOpaque(false);
        setLayout(new BorderLayout());
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setShowBorder(boolean show) {
        this.showBorder = show;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Use custom color or default from theme
        Color background = customColor != null ? customColor : AppTheme.getCardColor();

        // Create semi-transparent rounded rectangle background
        g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, opacity));
        g2d.setColor(background);
        g2d.fill(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), cornerRadius, cornerRadius));

        // Add subtle border
        if (showBorder) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f));
            g2d.setColor(AppTheme.CARD_BORDER);
            g2d.setStroke(new BasicStroke(1.5f));
            g2d.draw(new RoundRectangle2D.Double(1, 1, getWidth()-2, getHeight()-2, cornerRadius, cornerRadius));
        }

        // Draw title if present
        if (title != null && !title.isEmpty()) {
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
            g2d.setColor(AppTheme.getTextColor());
            g2d.setFont(new Font("Segoe UI", Font.BOLD, 16));
            g2d.drawString(title, 15, 25);

            // Draw separator line
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            g2d.setStroke(new BasicStroke(1.0f));
            g2d.drawLine(15, 35, getWidth() - 15, 35);
        }

        g2d.dispose();
    }
}
