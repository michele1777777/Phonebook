package utilities;

import java.awt.Color;
import javax.swing.JPasswordField;

/**
 * A customized version of JPasswordField that supports placeholder text and rounded corners. 
 * The placeholder text appears when the field is empty and loses focus. The text and border color can be customized.
 * @author Leuti Michele
 */
public class RoundedPasswordField extends JPasswordField{
	private static final long serialVersionUID = -5187072269785699916L;
	
	/**
     * Constructs a RoundedPasswordField with placeholder text. The placeholder disappears when the field gains focus 
     * and reappears when it loses focus if the field is empty.
     *
     * @param placeholder The placeholder text to display when the field is empty and not focused.
     */
	public RoundedPasswordField(String placeholder) {
        // Set placeholder text
        setText(placeholder);
        // Add a focus listener to manage placeholder text
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (String.valueOf(getPassword()).equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (String.valueOf(getPassword()).trim().equals("")) {
                    setText(placeholder);
                    setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }
    
	/**
     * Factory method for creating a JPasswordField with rounded corners and placeholder text.
     * This method applies a custom border to the password field and initializes it with placeholder text.
     *
     * @param placeholder The placeholder text for the password field.
     * @param borderColor The color of the rounded border.
     * @return A JPasswordField instance with rounded corners and placeholder functionality.
     */
    public static JPasswordField createRoundedPasswordField(String placeholder, Color borderColor) {
        JPasswordField passwordField = new RoundedPasswordField(placeholder);
        passwordField.setForeground(Color.LIGHT_GRAY);
        passwordField.setCaretColor(Color.BLACK);
        passwordField.setBorder(new RoundedCornerBorder(borderColor));
        return passwordField;
    }
}
