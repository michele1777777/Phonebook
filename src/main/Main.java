package main;
import gui.*;

/**
 * The Main class serves as the entry point for the application.
 * It initializes and starts the application by invoking the WindowsManager.
 */
public class Main {
	
	/**
     * The main method is the entry point of the application. It calls the start method
     * of the WindowsManager to initialize and display the initial user interface.
     * 
     * @param args The command-line arguments passed to the application (not used).
     */
	public static void main(String[] args) {
		WindowsManager.start();
	}
}
