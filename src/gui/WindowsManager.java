package gui;
import javax.swing.SwingUtilities;

/**
 * Manages the transitions between the Login and Phonebook frames of the application. This class controls
 * the visibility and instantiation of these frames, ensuring that only one frame is displayed at a time.
 * It offers static methods to start the application, switch to the login frame, switch to the phonebook frame,
 * and to close each frame when transitioning or ending their use.
 * @author Leuti Michele
 */
public class WindowsManager {
    private static LoginFrame login = null;
    private static PhonebookFrame phonebook = null;
    
    /**
     * Initializes and displays the LoginFrame as the entry point of the application.
     * This method should be called to start the GUI application.
     */
    public static void start() {
    	 SwingUtilities.invokeLater(() -> {
             login = new LoginFrame();
         });
    }
    
    /**
     * Closes the PhonebookFrame, if open, and initializes and displays the LoginFrame.
     * This method facilitates the transition back to the login screen from the phonebook view.
     */
    public static void switchToLoginFrame() {
        closePhonebook();
        SwingUtilities.invokeLater(() -> {
            login = new LoginFrame();
        });
    }
    
    /**
     * Closes the LoginFrame, if open, and initializes and displays the PhonebookFrame.
     * This method allows transitioning to the phonebook view after successful login or when navigating
     * from the login screen to the phonebook.
     */
    public static void switchToPhonebookFrame() {
        closeLogin();
        SwingUtilities.invokeLater(() -> {
            phonebook = new PhonebookFrame();
        });
    }
    
    /**
     * Closes and disposes of the PhonebookFrame, effectively freeing up resources used by the frame.
     * This method ensures that the phonebook view is properly closed before opening a new frame or exiting the application.
     */
    private static void closePhonebook() {
    	phonebook.setVisible(false);
        phonebook.dispose();
        phonebook = null;
    }
    
    /**
     * Closes and disposes of the LoginFrame, similar to {@code closePhonebook}, ensuring resources are freed
     * and the frame is properly closed before transitioning to another view or exiting.
     */
    private static void closeLogin() {
    	login.setVisible(false);
        login.dispose();
        login = null;
    }
}
