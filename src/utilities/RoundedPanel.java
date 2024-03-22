package utilities;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.RoundRectangle2D;

import javax.swing.JPanel;

/**
 * A custom JPanel subclass that features rounded corners. This panel supports varying radii for the corners, allowing for a high degree of customization.
 * @author Leuti Michele
 */
public class RoundedPanel extends JPanel {
	private static final long serialVersionUID = -6722871431241901969L;
	private int radius;
    private int radius2;
    
    /**
     * Constructs a RoundedPanel with specified radii for the corners. Allows for elliptical corners if two different radii are specified.
     *
     * @param radius1 The radius for the horizontal curvature of the corners.
     * @param radius2 The radius for the vertical curvature of the corners, allowing for elliptical shapes.
     */
    public RoundedPanel(int radius1, int radius2) {
        this.radius = radius1;
        this.radius2 = radius2;
        setOpaque(false); // Make the panel transparent
    }
    
    /**
     * Constructs a RoundedPanel with a uniform radius for all corners, creating a circular curvature at each corner.
     *
     * @param radius The radius to be used for both the horizontal and vertical curvature of the corners.
     */
    public RoundedPanel(int radius) {
        this.radius = radius;
        this.radius2 = radius;
        setOpaque(false); // Make the panel transparent
    }
    
    /**
     * Custom paint component method that renders the panel with rounded corners.
     *
     * @param g The Graphics object used for drawing the panel.
     */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        
        // Paints the rounded opaque panel with borders
        g2d.setColor(getBackground());
        g2d.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        //g2d.setColor(getForeground());
        //g2d.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius2, radius);
        g2d.dispose();
    }
    
    /**
     * Paints the panel's children, applying a clipping region that matches the panel's rounded corners. Ensures that children of this panel do not draw outside the rounded bounds.
     *
     * @param g The Graphics object used for painting the panel's children.
     */
    @Override
    protected void paintChildren(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setClip(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), radius, radius2));
        super.paintChildren(g2);
        g2.dispose();
    }
}
