package main;
import logic.*;

/**
 * The PhoneBookTest class contains a suite of static methods to test the functionality
 * of the Phonebook application. It covers user and person management features including
 * adding, searching, editing, and deleting users and persons.
 * @author Leuti Michele
 */
public class PhoneBookTest {
	
	 /**
     * The main method executes a series of test cases for the Phonebook application.
     * It tests functionalities such as adding, searching, editing, and deleting both users and persons.
     * Any exceptions thrown during the tests are caught and their stack trace is printed.
     *
     * @param args The command-line arguments. Not used in this application.
     */
    public static void main(String[] args) {
        try {
            testAddUser();
            testSearchUser();
            testEditUser();
            testAddPerson();
            testEditPerson();
            testDeletePerson();
            testDeleteUser();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Tests the addition of a user to the Phonebook. 
     * It prints a confirmation message upon successful addition.
     */
    private static void testAddUser() {
        System.out.println("Testing addUser...");
        String[] userInfo = {"Test", "User", "testuser", "Password123!"};
        User testUser = new User(userInfo);
        PhoneAction.addUser(testUser);
        System.out.println("Add user: DONE");
    }

    /**
     * Tests searching for a user in the Phonebook.
     * It prints a success message if the user is found, otherwise, it prints a failure message.
     */
    private static void testSearchUser() {
        System.out.println("Testing searchUser...");
        User foundUser = PhoneAction.searchUser("testuser");
        System.out.println(foundUser != null ? "Search user: SUCCESS" : "Search user: FAILED");
    }

    /**
     * Tests editing a user's information in the Phonebook.
     * It prints a confirmation message upon successful editing.
     */
    private static void testEditUser() {
        System.out.println("Testing editUser...");
        String[] updatedUserInfo = {"Test", "User", "testuser", "newPassword123!"};
        User updatedUser = new User(updatedUserInfo);
        PhoneAction.editUser(updatedUser);
        System.out.println("Edit user: DONE");
    }

    /**
     * Tests adding a person associated with a user to the Phonebook.
     * It prints a confirmation message upon successful addition.
     */
    private static void testAddPerson() {
        System.out.println("Testing addPerson...");
        User user = PhoneAction.searchUser("testuser");
        String[] personInfo = {"John", "Doe", "123 Main St", "+39555 1234", "30"};
        Person newPerson = new Person(personInfo, user);
        PhoneAction.addPerson(newPerson, user);
        System.out.println("Add person: DONE");
    }

    /**
     * Tests editing a person's information in the Phonebook.
     * This test assumes that a reference to the Person object to be edited is available.
     * It prints a confirmation message upon successful editing.
     */
    private static void testEditPerson() {
        System.out.println("Testing editPerson...");
        // Assuming you can get a reference to the Person object you want to edit
        User user = PhoneAction.searchUser("testuser");
        String[] personInfo = {"Jane", "Doe", "456 Elm St", "+(39555 6789", "32"};
        Person editedPerson = new Person(personInfo, user); // Assuming the person to be edited
        PhoneAction.editPerson(user, editedPerson);
        System.out.println("Edit person: DONE");
    }

    /**
     * Tests deleting a person from the Phonebook.
     * This test assumes that a reference to the Person object to be deleted is available.
     * It prints a confirmation message upon successful deletion.
     */
    private static void testDeletePerson() {
        System.out.println("Testing deletePerson...");
        // Assuming you can get a reference to the Person object you want to delete
        User user = PhoneAction.searchUser("testuser");
        String[] personInfo = {"Jane", "Doe", "456 Elm St", "+39555 6789", "32"};
        Person personToDelete = new Person(personInfo, user); // Assuming the person to be deleted
        PhoneAction.deletePerson(personToDelete, user);
        System.out.println("Delete person: DONE");
    }

    /**
     * Tests deleting a user from the Phonebook.
     * It prints a confirmation message upon successful deletion.
     */
    private static void testDeleteUser() {
        System.out.println("Testing deleteUser...");
        User userToDelete = PhoneAction.searchUser("testuser");
        PhoneAction.deleteUser(userToDelete);
        System.out.println("Delete user: DONE");
    }
}
