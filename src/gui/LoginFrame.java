package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import logic.*;
import utilities.*;


/**
 * The {@code LoginFrame} class creates the GUI for user login and registration. It extends {@code JFrame} and implements {@code ActionListener}
 * to handle user interactions. The interface includes a login panel for existing users to log in and a sign-in panel for new users to create an account.
 * It uses custom components like {@code RoundedPanel}, {@code RoundedTextField}, {@code RoundedPasswordField}, and {@code RoundedButton}
 * for a more appealing user interface. Additionally, it employs {@code SvgIconPanel} and {@code SvgButton} for SVG icon support.
 * 
 * This class manages user actions such as login, sign-in (registration), and navigation between the login and sign-in panels.
 * It communicates with the logic layer to perform operations like user authentication and registration.
 * 
 * @author Leuti Michele
 */
public class LoginFrame extends JFrame implements ActionListener {
	private static final long serialVersionUID = 8806835906377853950L;
	private RoundedPanel loginPanel;
	private RoundedPanel signInPanel;
	private JTextField userField;
	private JPasswordField passwordField;
	private JTextField userNameField;
	private JTextField userSurnameField;
	private JTextField userUsernameField;
	private JTextField newPasswordField;
	private JTextField ConfirmPasswordField;
    private JButton loginButton;
    private JButton signInButton;
    private JButton doneButton;
    private SvgButton backButton;
    
    /**
     * Constructs a new {@code LoginFrame} instance and sets up the GUI components for the login screen.
     */
    public LoginFrame() {
        this.setup();
    }
    
    /**
     * Sets up the initial frame properties and creates the login panel.
     * This method configures the frame title, size, default close operation, layout, and background color.
     * It also makes the frame visible and not resizable.
     */
    private void setup() {
    	setTitle("User Login");
        setSize(330, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridBagLayout());
        getContentPane().setBackground(new Color (50, 110, 150)); // grey background
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        this.createLoginPanel();	
    }
    
    /**
     * Creates and configures the login panel with all its components, including text fields for username and password,
     * login and sign-up buttons, and labels. It also applies custom styling to the components.
     */
    private void createLoginPanel() {
    	this.loginPanel = new RoundedPanel(25); // 25 is the radius for rounded corners
        this.loginPanel.setBackground(Color.white);
        this.loginPanel.setPreferredSize(new Dimension(300,400));
        this.loginPanel.setMaximumSize(new Dimension(500,500));
        this.loginPanel.setMinimumSize(new Dimension(500,500));
        this.loginPanel.setBounds(50,50,500,500);
        this.loginPanel.setLayout(null); 
        // Set the bounds or layout constraints as necessary

        // User icon
        try {
        	String filePath = "Images/icon2.svg";;
            SvgIconPanel panel = new SvgIconPanel(filePath);
            panel.setBounds(10,10,80,80);
            panel.setBorder(null);
            panel.setBackground(null);
            this.loginPanel.add(panel);
        }
        catch (Exception e) {
        e.printStackTrace();
        }
        
        ShadowLabel contactsLabel = new ShadowLabel("CONTACTS");
        contactsLabel.setFont(new Font("ITALIC", Font.BOLD, 26));
        contactsLabel.setForeground(new Color  (50, 110, 140) );
        contactsLabel.setBounds(115, 25, 150, 50);
        
        JLabel titleLabel = new JLabel("User Login");
        titleLabel.setForeground(new Color(50, 110, 140));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(100, 90, 350, 30);
        this.loginPanel.add(contactsLabel);
        this.loginPanel.add(titleLabel);

        this.userField = RoundedTextField.createRoundedTextField("Username", new Color (50, 110, 140));
        this.userField.setBounds(50, 130, 200, 35);
        this.loginPanel.add(this.userField);

        this.passwordField = RoundedPasswordField.createRoundedPasswordField("Password", new Color (50, 110, 140));
        this.passwordField.setBounds(50, 180, 200, 35);
        loginPanel.add(this.passwordField);

        this.loginButton = RoundedButton.createRoundedButton("LOGIN", new Color (50, 110, 140),new Color (50, 110, 140), Color.white);
        this.loginButton.setBounds(50, 230, 200, 40);
        this.loginButton.addActionListener(this);
        this.loginPanel.add(loginButton);

        JLabel accountLabel = new JLabel("Don't have an");
        accountLabel.setForeground(Color.black);
        accountLabel.setBounds(50, 280, 150, 30);
        this.loginPanel.add(accountLabel);
        
        JLabel accountLabel_1 = new JLabel(" account?");
        accountLabel_1.setForeground(new Color (50, 110, 140));
        accountLabel_1.setBounds(130, 280, 100, 30);
        this.loginPanel.add(accountLabel_1);
        
        this.signInButton = RoundedButton.createRoundedButton("SIGN UP", new Color (50, 110, 140),new Color (50, 110, 140), Color.white);
        this.signInButton.setBounds(50, 320, 200, 40);
        this.signInButton.addActionListener(this);
        this.loginPanel.add(this.signInButton);
        add(this.loginPanel);
    }
    
    /**
     * Creates and configures the sign-in panel for new user registration. This method is called when a user wishes to create a new account.
     * It configures text fields for user information input, including name, surname, username, password, and password confirmation.
     * It also adds a sign-in button and a back button to return to the login screen.
     */
    private void createSigninPanel() {
    	setTitle("User Sign in");
    	this.signInPanel = new RoundedPanel(25);
		signInPanel.setBackground(Color.white);
		signInPanel.setPreferredSize(new Dimension(this.loginPanel.getWidth(),this.loginPanel.getHeight()));
		signInPanel.setBounds(this.loginPanel.getX(),this.loginPanel.getY(),this.loginPanel.getWidth(),this.loginPanel.getHeight());
		signInPanel.setLayout(null);
		try {
	        	String filePath = "Images/icon2.svg";
	            SvgIconPanel panel = new SvgIconPanel(filePath);
	            panel.setBounds(this.loginPanel.getWidth() - 90,10,80,80);
	            panel.setBorder(null);
	            panel.setBackground(null);
	            this.signInPanel.add(panel);
	    }
	 	catch (Exception e) {
        e.printStackTrace();
        }
		 
		ShadowLabel contactsLabel = new ShadowLabel ("CONTACTS");
        contactsLabel.setFont(new Font("Arial", Font.BOLD, 26));
        contactsLabel.setForeground(new Color  (50, 110, 140) );
        contactsLabel.setBounds(35, 35, 150, 50);
        this.signInPanel.add(contactsLabel);
        
        JLabel titleLabel = new JLabel("User Sign in");
        titleLabel.setForeground(new Color(50, 110, 140));
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        titleLabel.setBounds(100, 90, 350, 30);
        this.signInPanel.add(titleLabel);
        
        this.userNameField = RoundedTextField.createRoundedTextField("Name", new Color (50, 110, 140));
        this.userNameField.setBounds(50, 130, 200, 25);
        this.signInPanel.add(this.userNameField);
        
        this.userSurnameField = RoundedTextField.createRoundedTextField("Surname", new Color (50, 110, 140));
        this.userSurnameField.setBounds(50, 170, 200, 25);
        this.signInPanel.add(this.userSurnameField);
        
        this.userUsernameField = RoundedTextField.createRoundedTextField("Username", new Color (50, 110, 140));
        this.userUsernameField.setBounds(50, 210, 200, 25);
        this.signInPanel.add(this.userUsernameField);

        this.newPasswordField = RoundedTextField.createRoundedTextField("Password", new Color (50, 110, 140));
        this.newPasswordField.setBounds(50, 250, 200, 25);
        this.signInPanel.add(this.newPasswordField);
        
        this.ConfirmPasswordField = RoundedTextField.createRoundedTextField("Confirm Password", new Color (50, 110, 140));
        this.ConfirmPasswordField.setBounds(50, 290, 200, 25);
        this.signInPanel.add(this.ConfirmPasswordField);
       
        this.doneButton = RoundedButton.createRoundedButton("SIGN IN", new Color (50, 110, 140),new Color (50, 110, 140), Color.white);
        this.doneButton.setPreferredSize(new Dimension(200,35));
        this.doneButton.setBounds(50, 330, 200, 35);
        this.doneButton.addActionListener(this);
        this.signInPanel.add(this.doneButton);
        
        String filePath = "Images/undo2.svg";
        this.backButton = new SvgButton(filePath);
        this.backButton.setBounds(10,8,35,35);
        this.backButton.setBorder(null);
        this.backButton.setBackground(null);
        this.backButton.addActionListener(this);
        this.signInPanel.add(this.backButton);
        
        
		this.loginPanel.setVisible(false);
		add(this.signInPanel);
    }
    
    /**
     * Handles action events triggered by user interactions with GUI components.
     * This method responds to events from the login button, sign-in button, done button, and back button,
     * facilitating user login, navigation to the sign-in panel, user registration, and navigation back to the login panel, respectively.
     * 
     * @param e The action event triggered by the user.
     */
	@SuppressWarnings("deprecation")
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.loginButton) {
			if (this.passwordField.getText().equals("Password")|| this.passwordField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "The password field must not be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			if (this.userField.getText().equals("Username")|| this.userField.getText().isEmpty()) {
				JOptionPane.showMessageDialog(this, "The username field must not be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			String username = this.userField.getText();
			User u = PhoneAction.searchUser(username);
			
			if (u == null) {
				JOptionPane.showMessageDialog(this, "User not found!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			else if (u.checkPassword((this.passwordField.getText()))){
				PhonebookFrame.user = u;
				WindowsManager.switchToPhonebookFrame();
			}
			else {
				JOptionPane.showMessageDialog(this, "The password is incorrect!", "Error", JOptionPane.ERROR_MESSAGE);
			}
		}
		
		else if(e.getSource() == this.signInButton) {
			this.createSigninPanel();
		}
		
		else if(e.getSource() == this.doneButton) {
			if (!this.newPasswordField.getText().equals(this.ConfirmPasswordField.getText())) {
				JOptionPane.showMessageDialog(this, "Password and confirm password doesn't match!", "Error", JOptionPane.ERROR_MESSAGE);
			}
			
			if (this.userNameField.getText().equals("Name") ||this.userField.getText().equals("Surname") || this.userUsernameField.getText().equals("Username")||this.newPasswordField.getText().equals("Password") || this.ConfirmPasswordField.getText().equals("Confirm password")) {
				JOptionPane.showMessageDialog(this, "All fields must not be empty!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			
			User u = PhoneAction.searchUser(this.userUsernameField.getText());
			if(u != null) {
				JOptionPane.showMessageDialog(this, "The username: \"" + this.userUsernameField.getText() + "\"" + " already exists!", "Warning", JOptionPane.WARNING_MESSAGE);
			}
			
			else{
				try {
					String info[] = new String[4];
					info[0] = this.userNameField.getText();
					info[1] = this.userSurnameField.getText();
					info[2] = this.userUsernameField.getText();
					info[3] = this.newPasswordField.getText();
					User user = new User(info);
					PhoneAction.addUser(user);
					JOptionPane.showMessageDialog(this, "User added successfully!", "Info", JOptionPane.INFORMATION_MESSAGE);
					this.signInPanel.setVisible(false);
					setTitle("User login");
					this.loginPanel.setVisible(true);
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
			
		}
		
		else if(e.getSource() == this.backButton) {
			setTitle("User login");
			this.signInPanel.setVisible(false);
			this.loginPanel.setVisible(true);
			
		}
	}
	
}

