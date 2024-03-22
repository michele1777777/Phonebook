package logic;

import java.util.ArrayList;

import database.DatabaseManager;

/**
 * Provides static utility methods for actions related to Users and Persons, 
 * such as searching, adding, editing, and deleting, by interfacing with the DatabaseManager.
 * @author Leuti Michele
 */
public class PhoneAction {
	
	/**
     * Searches for a user by their username.
     *
     * @param userName The username of the user to search for.
     * @return The User object if found; otherwise, returns null.
     */
	public static User searchUser(String userName) {
		return DatabaseManager.selectUserByUsername(userName);
	}
	
	 /**
     * Adds a new user to the database.
     *
     * @param u The User object to be added.
     */
	public static void addUser(User u) {
		DatabaseManager.insertUser(u);
	}
	
	/**
     * Deletes a user from the database.
     *
     * @param u The User object to be deleted.
     */
	public static void deleteUser(User u) {
		DatabaseManager.deleteUser(u);
	}
	
	/**
     * Checks if a username already exists in the database.
     *
     * @param username The username to check for existence.
     * @throws IllegalArgumentException If the username already exists in the database.
     */
	public static void checkUsername(String username) {
		boolean flag = DatabaseManager.doesUsernameExist(username);
		if (flag) {
			throw new IllegalArgumentException("The username " + username + " already exists!");
		}
	}
	
	/**
     * Updates a user's information in the database.
     *
     * @param u The User object with updated information to be saved.
     */
	public static void editUser(User u) {
		DatabaseManager.updateUser(u);
	}
	
	/**
     * Adds a person associated with a user to the database.
     *
     * @param p The Person object to be added.
     * @param u The User object associated with the person.
     */
	public static void addPerson(Person p, User u) {
		DatabaseManager.insertPerson(p, u);
	}
	
	/**
     * Deletes a person from the database.
     *
     * @param p The Person object to be deleted.
     * @param u The User object associated with the person.
     */
	public static void deletePerson(Person p, User u) {
		DatabaseManager.deletePerson(p, u);
	}
	
	 /**
     * Updates a person's information in the database.
     *
     * @param u The User object associated with the person.
     * @param p The Person object with updated information to be saved.
     */
	public static void editPerson(User u, Person p) {
		DatabaseManager.updatePerson(p, u);
	}
	
	/**
     * Searches for a person by their ID and associated user.
     *
     * @param u The User object associated with the person.
     * @param id The ID of the person to search for.
     * @return The Person object if found; otherwise, returns null.
     */
	public static Person searchPerson(User u, String id) {
		return DatabaseManager.selectPersonById(id, u);
	}
	
	/**
     * Searches for persons matching a specified search string under a specific user.
     *
     * @param u The User object associated with the persons.
     * @param str The search string to match against person names and other attributes.
     * @return An ArrayList of Person objects matching the search criteria.
     */
	public static ArrayList<Person> Search(User u, String str) {
		ArrayList<Person> contacts = DatabaseManager.searchPersons(str, u);
		return contacts;
	}
}
