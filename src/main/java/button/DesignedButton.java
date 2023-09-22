package button;

import util.Constants;

import javax.swing.JButton;
import java.awt.*;

public class DesignedButton extends JButton {
    private static final int ARC_WIDTH = 10;
    private static final int ARC_HEIGHT = 10;
    private static final int BORDER_THICKNESS = 2;
    private static final Color BORDER_COLOR = Color.BLACK;

    public DesignedButton(String text) {
        super(text);

        setBackground(new Color(255, 255, 255));
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        if (getModel().isArmed()) {
            g2.setColor(Color.DARK_GRAY);
        } else if (getModel().isRollover()) {
            g2.setColor(Color.LIGHT_GRAY);
        } else {
            g2.setColor(getBackground());
        }

        int borderThickness = BORDER_THICKNESS;
        int halfBorderThickness = borderThickness / 2;
        int adjustedWidth = getWidth() - borderThickness;
        int adjustedHeight = getHeight() - borderThickness;

        g2.fillRoundRect(halfBorderThickness, halfBorderThickness, adjustedWidth, adjustedHeight, ARC_WIDTH, ARC_HEIGHT);

        g2.setColor(BORDER_COLOR);
        g2.setStroke(new BasicStroke(borderThickness));
        g2.drawRoundRect(halfBorderThickness, halfBorderThickness, adjustedWidth, adjustedHeight, ARC_WIDTH, ARC_HEIGHT);

        super.paintComponent(g2);
        g2.dispose();
    }
}



