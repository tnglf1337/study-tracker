package frame;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

public class RoundedBorder extends AbstractBorder {

    private int borderRadius;

    public RoundedBorder(int borderRadius) {
        this.borderRadius = borderRadius;
    }

    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Insets insets = getBorderInsets(c);
        int arcWidth = borderRadius * 2;
        int arcHeight = borderRadius * 2;
        int x1 = x + insets.left;
        int y1 = y + insets.top;
        int width1 = width - insets.right - insets.left - 1;
        int height1 = height - insets.top - insets.bottom - 1;
        Graphics clip = g.create();
        Shape shape = new RoundRectangle2D.Double(x1, y1, width1, height1, arcWidth, arcHeight);
        clip.setClip(shape);
        g.setColor(c.getBackground());
        g.fillRect(x, y, width, height);
        clip.dispose();
    }

    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(borderRadius, borderRadius, borderRadius, borderRadius);
    }

    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.top = insets.right = insets.bottom = borderRadius;
        return insets;
    }

}

