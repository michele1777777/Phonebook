package utilities;

import java.awt.Color;
import javax.swing.JTextField;

/**
 * A custom JTextField that includes support for placeholder text and rounded corners. The placeholder text is displayed 
 * when the field is unfocused and empty. It provides a visually appealing design for UIs that require input fields with enhanced aesthetics.
 * @author Leuti Michele
 */
public class RoundedTextField extends JTextField {
	private static final long serialVersionUID = 2718400986499682759L;
	
	/**
     * Constructs a RoundedTextField with specified placeholder text. The placeholder is hidden when the field gains focus 
     * and shown when it loses focus if no text has been entered.
     *
     * @param placeholder The placeholder text to be displayed in the text field when it is unfocused and empty.
     */
	public RoundedTextField(String placeholder) {
        setText(placeholder);
        addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (getText().equals(placeholder)) {
                    setText("");
                    setForeground(Color.BLACK);
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (getText().trim().equals("")) {
                    setText(placeholder);
                    setForeground(Color.LIGHT_GRAY);
                }
            }
        });
    }
    
	 /**
     * Factory method to create a JTextField with rounded corners and initialized with placeholder text. 
     * This method applies a custom border to the text field, enhancing its visual appearance.
     *
     * @param placeholder The placeholder text for the text field.
     * @param borderColor The color of the border to be applied for rounded corners.
     * @return A JTextField instance with rounded corners and placeholder functionality.
     */
    public static JTextField createRoundedTextField(String placeholder, Color borderColor) {
   	 	JTextField textField = new RoundedTextField(placeholder);
        textField.setForeground(Color.LIGHT_GRAY);
        textField.setCaretColor(Color.BLACK);
        textField.setBorder(new RoundedCornerBorder(borderColor));
        return textField;
    }
}