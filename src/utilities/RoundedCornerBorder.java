package utilities;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import javax.swing.border.AbstractBorder;

/**
 * A custom border class that creates a border with rounded corners.
 * This class allows for the customization of the border radius and color, providing a modern look to Swing components.
 * @author Leuti Michele
 */
public class RoundedCornerBorder extends AbstractBorder {
	private static final long serialVersionUID = -5267502490819899504L;
	private final int radius;
    private final Color color;
       
    /**
     * Constructs a RoundedCornerBorder with the specified radius and color.
     *
     * @param radius The radius of the border's rounded corners.
     * @param color The color of the border.
     */
    public RoundedCornerBorder(int radius, Color color) {
        this.radius = radius;
        this.color = color;
    }
    
    /**
     * Constructs a RoundedCornerBorder with a default radius of 25 and the specified color.
     *
     * @param color The color of the border.
     */
    public RoundedCornerBorder(Color color) {
        this.radius = 25; // Default radius.
        this.color = color;
    }
    
    /**
     * Paints the border for the specified component with the defined rounded corners.
     *
     * @param c The component for which this border is being painted.
     * @param g The graphics context.
     * @param x The x position of the border.
     * @param y The y position of the border.
     * @param width The width of the border.
     * @param height The height of the border.
     */
    @Override
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Define a thinner border thickness here
        int thickness = 1; // Adjust this to make the border thinner
        float halfThickness = thickness / 2.0f;

        // Calculate adjusted dimensions to ensure the border is drawn within the component's bounds
        float w = width - thickness;   // Adjust width for the border thickness
        float h = height - thickness;  // Adjust height for the border thickness

        // Draw the border using the adjusted dimensions to fit within the component's area
        Shape borderShape = new RoundRectangle2D.Float(x + halfThickness, y + halfThickness, w, h, radius, radius);

        g2.setPaint(this.color);
        g2.setStroke(new BasicStroke(thickness)); // Use the adjusted thickness
        g2.draw(borderShape); // Draw the border shape

        g2.dispose();
    }

    
    /**
     * Returns the insets of the border.
     *
     * @param c The component for which the border insets are being requested.
     * @return The insets of the border.
     */
    @Override
    public Insets getBorderInsets(Component c) {
        return new Insets(4, 8, 4, 8);
    }
    
    /**
     * Returns the insets of the border given an existing Insets object.
     *
     * @param c The component for which the border insets are being requested.
     * @param insets An existing Insets object to be filled with the correct values.
     * @return The insets of the border.
     */
    @Override
    public Insets getBorderInsets(Component c, Insets insets) {
        insets.left = insets.right = 8;
        insets.top = insets.bottom = 4;
        return insets;
    }
    
    /**
     * Returns the Shape object for the border based on the specified dimensions.
     * This method is useful for creating a clip shape for the component's content.
     *
     * @param x The x position of the border.
     * @param y The y position of the border.
     * @param w The width of the border.
     * @param h The height of the border.
     * @return The Shape object representing the border.
     */
    public Shape getBorderShape(int x, int y, int w, int h) {
        return new RoundRectangle2D.Float(x, y, w, h, radius, radius);
    }
    
}
    
