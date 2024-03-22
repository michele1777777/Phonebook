package logic;

import java.util.regex.Pattern;
import java.util.ArrayList;
import java.util.regex.Matcher;
import table.Table;

/**
 * Represents a user with additional credentials such as username and password, extending the Person class.
 * This class also manages a table of contacts associated with the user, offering functionality to manage user details
 * and their contacts.
 * @author Leuti Michele
 */
public class User extends Person{
	private String username;
	private String password;
	private Table t;
	
	/**
     * Constructs a User instance with basic information and initializes a new table of contacts.
     * Validates the username and password for non-emptiness and password complexity.
     *
     * @param userInfo An array containing the user's name, surname, username, and password.
     * @throws IllegalArgumentException If any validation fails.
     */
	public User(String[] userInfo) {
		super(true, userInfo);
		super.checkIfNotEmpty(userInfo[2]);
		checkIfValidPassword(userInfo[3]);
		this.username = userInfo[2];
		this.password = userInfo[3];
		this.t = new Table(this);
	}
	
	/**
     * Constructs a User instance with basic information, a list of existing contacts, and a specific ID.
     * Initializes the user's table of contacts either with existing contacts or as a new table.
     *
     * @param userInfo An array containing the user's name, surname, username, and password.
     * @param contacts An ArrayList of Person objects representing existing contacts.
     * @param id The user's unique identifier.
     * @throws IllegalArgumentException If any validation fails.
     */
	public User(String[] userInfo, ArrayList <Person> contacts, String id) {
		super(userInfo, id);
		super.checkIfNotEmpty(userInfo[2]);
		checkIfValidPassword(userInfo[3]);
		this.username = userInfo[2];
		this.password = userInfo[3];
		if (contacts != null) {
			this.t = new Table(this, contacts);
		}
		else {
			this.t = new Table(this);
		}
	}
	
	 /**
     * Gets the list of contacts associated with this user.
     *
     * @return An ArrayList of Person objects representing the user's contacts.
     */
	public ArrayList<Person> getContacts() {
		return this.t.getPersons();
	}
	
	/**
     * Gets the username of this user.
     *
     * @return The username.
     */
	public String getUsername() {
		return this.username;
	}
	
	/**
     * Sets the username of this user after validating for non-emptiness.
     *
     * @param username The new username to be set.
     * @throws IllegalArgumentException If the username is empty.
     */
	public void setUsername(String username) {	
		super.checkIfNotEmpty(username);
		this.username = username;
	}
	
	/**
     * Gets the password of this user.
     *
     * @return The password.
     */
	public String getPassword() {
		return this.password;
	}
	
	/**
     * Sets the password of this user after validating for complexity requirements.
     *
     * @param password The new password to be set.
     * @throws IllegalArgumentException If the password does not meet complexity requirements.
     */
	public void setPassword(String password) {
		checkIfValidPassword(password);
		this.password = password;
	}
	
	/**
     * Checks if a given password matches the user's current password.
     *
     * @param password The password to check against the user's password.
     * @return True if the passwords match; false otherwise.
     */
	public boolean checkPassword(String password) {
		if (password.equals(this.password)) {
			return true;
		}
		return false;
	}
	
	/**
     * Validates a password against a set of complexity requirements.
     *
     * @param password The password to validate.
     * @return True if the password meets the complexity requirements; otherwise, throws an exception.
     * @throws IllegalArgumentException If the password does not meet the complexity requirements.
     */
	public static boolean checkIfValidPassword(String password) {
		String allPassRegex =  "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#$%^&-+=()])(?=\\S+$).{8,20}$";
	    Pattern p = Pattern.compile(allPassRegex);
	    Matcher m = p.matcher(password);
	    if (!m.find()) {
	    	throw new IllegalArgumentException("Password must contains at least 8 characters and at most 20 characters.\r\n"
	    			+ "at least one digit.\r\n"
	    			+ "at least one upper case alphabet.\r\n"
	    			+ "at least one lower case alphabet.\r\n"
	    			+ "at least one special character which includes !@#$%&*()-+=^.\r\n"
	    			+ "doesn’t contain any white space.");
	    }
	    return true;
	}
	
	 /**
     * Gets the Table object associated with this user, containing their contacts.
     *
     * @return The Table of contacts.
     */
	public Table getTable() {
		return this.t;
	}
	
	/**
     * Compiles the user's information into an array.
     *
     * @return An array of strings containing the user's information.
     */
	public String[] getUserInfo() {
		String[] out = new String[7];
		out[0] = super.getName();
		out[1] = super.getSurname();
		out[2] = super.getAddress();
		out[3] = super.getPhone();
		out[4] = super.getAge().toString();
		out[5] = this.username;
		out[6] = this.password;
		return out;
	}
	
	/**
     * Generates a string representation of the user, including their personal information and credentials.
     *
     * @return A string representation of the user.
     */
	@Override 
	public String toString() {
		return super.toString();
	}
	

}
