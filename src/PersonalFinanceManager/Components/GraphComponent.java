package PersonalFinanceManager.Components;

import PersonalFinanceManager.AppTheme;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;
import java.util.List;

public class GraphComponent extends GlassBox {
    private List<Double> incomeData;
    private List<Double> expenseData;
    private List<Double> balanceData;
    private List<String> labels;
    private double maxValue;

    public GraphComponent() {
        super(15);
        setTitle("Financial Trends");
        setPreferredSize(new Dimension(0, 300));

        // Sample data for demonstration
        initializeSampleData();
    }

    private void initializeSampleData() {
        // Create some sample data points
        incomeData = new ArrayList<>();
        expenseData = new ArrayList<>();
        balanceData = new ArrayList<>();
        labels = new ArrayList<>();

        // Add sample values (these would normally be loaded from data)
        String[] months = {"Jan", "Feb", "Mar", "Apr", "May", "Jun"};
        double[] income = {7500, 7800, 8200, 8600, 9000, 9467};
        double[] expenses = {6800, 7200, 7600, 8100, 8300, 8645};

        for (int i = 0; i < months.length; i++) {
            labels.add(months[i]);
            incomeData.add(income[i]);
            expenseData.add(expenses[i]);
            balanceData.add(income[i] - expenses[i]);
        }

        // Calculate max value for scaling
        maxValue = 0;
        for (Double value : incomeData) {
            maxValue = Math.max(maxValue, value);
        }
        for (Double value : expenseData) {
            maxValue = Math.max(maxValue, value);
        }

        // Add some margin
        maxValue *= 1.1;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Graph dimensions
        int padding = 50;
        int labelPadding = 30;
        int rightPadding = 40;
        int pointWidth = 10;

        int width = getWidth() - padding - rightPadding;
        int height = getHeight() - padding - labelPadding;

        // Draw graph area background (optional)
        g2d.setColor(new Color(30, 35, 42, 120));
        g2d.fillRoundRect(padding, padding, width, height, 10, 10);

        // Draw legend
        drawLegend(g2d, padding, 30);

        // Draw axis
        g2d.setColor(AppTheme.getSecondaryTextColor());
        g2d.setStroke(new BasicStroke(1.0f));

        // Draw horizontal lines
        for (int i = 0; i <= 5; i++) {
            int y = height + padding - (i * height / 5);

            // Draw dotted lines
            g2d.setStroke(new BasicStroke(1.0f, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{2}, 0));
            g2d.drawLine(padding, y, padding + width, y);

            // Draw labels
            g2d.setColor(AppTheme.getSecondaryTextColor());
            String yLabel = String.format("$%.0f", i * maxValue / 5);
            FontMetrics metrics = g2d.getFontMetrics();
            int labelWidth = metrics.stringWidth(yLabel);
            g2d.drawString(yLabel, padding - labelWidth - 5, y + (metrics.getHeight() / 2) - 3);
        }

        // Draw data points and lines
        if (incomeData.size() > 0) {
            drawDataLine(g2d, incomeData, AppTheme.INCOME_GREEN, padding, labelPadding, width, height, pointWidth);
            drawDataLine(g2d, expenseData, AppTheme.EXPENSE_RED, padding, labelPadding, width, height, pointWidth);
            drawDataLine(g2d, balanceData, AppTheme.ACCENT_BLUE, padding, labelPadding, width, height, pointWidth);
        }

        // Draw x-axis labels
        int xPadding = width / (labels.size() - 1);
        g2d.setColor(AppTheme.getSecondaryTextColor());
        g2d.setFont(new Font("Segoe UI", Font.PLAIN, 12));

        for (int i = 0; i < labels.size(); i++) {
            int x = padding + (i * xPadding);
            int y = height + padding + 20;
            g2d.drawString(labels.get(i), x - 10, y);
        }

        g2d.dispose();
    }

    private void drawDataLine(Graphics2D g2d, List<Double> dataPoints, Color color,
                              int padding, int labelPadding, int width, int height, int pointWidth) {

        // Points
        int xPadding = width / (dataPoints.size() - 1);
        List<Point> graphPoints = new ArrayList<>();

        for (int i = 0; i < dataPoints.size(); i++) {
            int x = padding + (i * xPadding);
            int y = (int)(height + padding - ((dataPoints.get(i) / maxValue) * height));
            graphPoints.add(new Point(x, y));
        }

        // Draw lines with gradient
        for (int i = 0; i < graphPoints.size() - 1; i++) {
            Point p1 = graphPoints.get(i);
            Point p2 = graphPoints.get(i + 1);

            // Create gradient line
            GradientPaint gradientLine = new GradientPaint(
                    p1.x, p1.y, new Color(color.getRed(), color.getGreen(), color.getBlue(), 150),
                    p2.x, p2.y, new Color(color.getRed(), color.getGreen(), color.getBlue(), 200)
            );
            g2d.setPaint(gradientLine);
            g2d.setStroke(new BasicStroke(3.0f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            g2d.draw(new Line2D.Double(p1.x, p1.y, p2.x, p2.y));
        }

        // Draw points
        for (Point p : graphPoints) {
            // Gradient circle for points
            RadialGradientPaint pointGradient = new RadialGradientPaint(
                    new Point2D.Double(p.x, p.y), pointWidth / 2.0f,
                    new float[] {0.0f, 1.0f},
                    new Color[] {color, new Color(color.getRed(), color.getGreen(), color.getBlue(), 100)}
            );

            g2d.setPaint(pointGradient);
            g2d.fill(new Ellipse2D.Double(p.x - pointWidth/2.0, p.y - pointWidth/2.0, pointWidth, pointWidth));

            // Add white border
            g2d.setColor(new Color(255, 255, 255, 180));
            g2d.setStroke(new BasicStroke(1.0f));
            g2d.draw(new Ellipse2D.Double(p.x - pointWidth/2.0, p.y - pointWidth/2.0, pointWidth, pointWidth));
        }
    }

    private void drawLegend(Graphics2D g2d, int x, int y) {
        // Set up legend items
        String[] items = {"Income", "Expenses", "Balance"};
        Color[] colors = {AppTheme.INCOME_GREEN, AppTheme.EXPENSE_RED, AppTheme.ACCENT_BLUE};

        g2d.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        FontMetrics metrics = g2d.getFontMetrics();

        int itemX = x;

        for (int i = 0; i < items.length; i++) {
            // Draw colored rectangle
            g2d.setColor(colors[i]);
            g2d.fillRect(itemX, y, 10, 10);

            // Draw item name
            g2d.setColor(AppTheme.getTextColor());
            g2d.drawString(items[i], itemX + 15, y + 9);

            // Calculate next position
            itemX += metrics.stringWidth(items[i]) + 35;
        }
    }
}
