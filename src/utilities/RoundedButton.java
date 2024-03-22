package utilities;

import java.awt.*;

import javax.swing.JButton;

/**
 * Utility class for creating customized JButton with rounded corners and gradient backgrounds.
 * @author Leuti Michele
 */
public class RoundedButton {
	
	/**
     * Creates a JButton with rounded corners, custom background and border colors, and a gradient fill. 
     * The button's appearance is further customized with a specified foreground color for the text.
     *
     * @param text The text to be displayed on the button.
     * @param color The color used for the button's gradient fill.
     * @param borderColor The color used for the button's border.
     * @param ForegColor The color used for the button's text.
     * @return A customized JButton with rounded corners, gradient background, and specified text color.
     */
    public static JButton createRoundedButton(String text, Color color, Color borderColor, Color ForegColor) {
        JButton button = new JButton(text) {
			private static final long serialVersionUID = 4909362517387200965L;

			@Override
            protected void paintComponent(Graphics g) {
                if (!isOpaque() && getBorder() instanceof RoundedCornerBorder) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

                    // Gradient
                    GradientPaint gp = new GradientPaint(0, 0, getBackground().brighter(), 0, getHeight(), getBackground().darker());
                    g2.setPaint(gp);

                    // Fill with rounded corners
                    g2.fill(((RoundedCornerBorder) getBorder()).getBorderShape(
                        0, 0, getWidth() - 1, getHeight() - 1));
                    
                    g2.dispose();
                }
                super.paintComponent(g);
            }

			 // Overridden to ensure that the button's content area is not filled, allowing for custom painting
            @Override
            public void setContentAreaFilled(boolean b) {
            }
        };
        
        button.setOpaque(false);
        button.setBorder(new RoundedCornerBorder(color));
        button.setBackground(borderColor); // Set the button background color
        button.setForeground(ForegColor); // Set the text color to white
        return button;
    }
}