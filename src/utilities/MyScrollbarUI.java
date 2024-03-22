package utilities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.plaf.basic.BasicScrollBarUI;

/**
 * Custom scrollbar UI that extends {@link BasicScrollBarUI}. This UI customization 
 * changes the colors of the scrollbar thumb and track, and modifies the increase and decrease buttons.
 * It introduces a hover effect on the scrollbar thumb for better user interaction feedback.
 * @author Leuti Michele
 */
public class MyScrollbarUI extends BasicScrollBarUI {
	private Color thumbHoverColor = new Color(245,245,245,145);
    
	 /**
     * Configures the colors of the scrollbar components. Overrides default scrollbar colors 
     * with custom colors for the thumb and track.
     */
	@Override
    protected void configureScrollBarColors() {
        this.thumbColor = new Color (70, 130, 160, 155);
        this.trackColor = new Color (90, 150, 180, 155);
    }
	
	/**
     * Creates and returns a customized decrease button with custom background and foreground colors.
     *
     * @param orientation The button's orientation.
     * @return A JButton instance representing the decrease button.
     */
    @Override
    protected JButton createDecreaseButton(int orientation) {
        JButton button = super.createDecreaseButton(orientation);
        button.setBackground(Color.white); // Example customization
        button.setForeground(Color.white);
        return button;
    }

    /**
     * Creates and returns a customized increase button with custom background and foreground colors.
     *
     * @param orientation The button's orientation.
     * @return A JButton instance representing the increase button.
     */
    @Override
    protected JButton createIncreaseButton(int orientation) {
        JButton button = super.createIncreaseButton(orientation);
        button.setBackground(Color.white); // Example customization
        button.setForeground(Color.white);
        return button;
    }
    
    /**
     * Customizes the painting of the scrollbar's thumb. If the mouse hovers over the thumb, a hover color is used.
     *
     * @param g The {@link Graphics} context used for painting.
     * @param c The component being painted.
     * @param thumbBounds The bounds of the scrollbar thumb.
     */
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        if (!thumbBounds.isEmpty() && scrollbar.isEnabled()) {
            g.setColor(isThumbRollover() ? thumbHoverColor : thumbColor); // Use hover color if mouse is over the thumb
            g.fillRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height);
        }
    }
    
    /**
     * Customizes the painting of the scrollbar's track with a custom color.
     *
     * @param g The {@link Graphics} context used for painting.
     * @param c The component being painted.
     * @param trackBounds The bounds of the scrollbar track.
     */
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(trackColor);
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }
}
