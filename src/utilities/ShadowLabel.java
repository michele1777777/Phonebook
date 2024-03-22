package utilities;
import javax.swing.*;
import java.awt.*;

/**
 * A custom JLabel that renders a shadow beneath its text. This class enhances the visual presentation of labels 
 * by adding a shadow effect, which can improve readability and aesthetic appeal.
 * @author Leuti Michele
 */
public class ShadowLabel extends JLabel {
	private static final long serialVersionUID = -9041708060600569626L;
	private Color shadowColor = new Color(0,0,0,15);
    private int shadowOffset = 6;

    /**
     * Constructs a ShadowLabel with specified text.
     *
     * @param text The text to be displayed by this label.
     */
    public ShadowLabel(String text) {
        super(text);
    }
    
    /**
     * Overrides the paintComponent method to add a shadow effect to the text of the label. 
     * The shadow is rendered first, followed by the label's text itself.
     *
     * @param g The Graphics object to protect.
     */
    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(shadowColor);
        g2d.drawString(getText(), getInsets().left + shadowOffset, getFontMetrics(getFont()).getAscent() + shadowOffset);
        g2d.dispose();
        super.paintComponent(g);
    }

    /**
     * Sets the color of the shadow.
     *
     * @param shadowColor The new color to be used for the shadow.
     */
    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
    }
    
    /**
     * Sets the offset distance for the shadow.
     *
     * @param shadowOffset The new offset distance for the shadow.
     */
    public void setShadowOffset(int shadowOffset) {
        this.shadowOffset = shadowOffset;
    }
}
