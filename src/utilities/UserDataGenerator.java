package utilities;

import java.util.Random;
import logic.Person;
import logic.PhoneAction;
import logic.User;

/**
 * This utility class generates a set of test data including a user and associated persons.
 * It's designed to facilitate testing by providing a method to automatically populate the system
 * with a user and a predefined number of persons with randomized data.
 * @author Leuti Michele
 */
public class UserDataGenerator {
    private static final String[] firstNames = {
        "James", "Mary", "John", "Patricia", "Robert", "Jennifer", "Michael", "Linda",
        "William", "Elizabeth", "David", "Barbara", "Richard", "Susan", "Joseph", "Jessica",
        "Thomas", "Sarah", "Charles", "Karen", "Christopher", "Nancy", "Daniel", "Lisa",
        "Matthew", "Margaret", "Anthony", "Betty", "Mark", "Sandra"
    };
    private static final String[] lastNames = {
        "Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia", "Miller", "Davis",
        "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
        "Thomas", "Taylor", "Moore", "Jackson", "Martin", "Lee", "Perez", "Thompson",
        "White", "Harris", "Sanchez", "Clark", "Ramirez", "Lewis", "Robinson"
    };
    private static final Random random = new Random();
    
    /**
     * Creates a single user and 100 associated random persons.
     * This method simulates adding data to a system for testing purposes.
     */
    public static void create() {
        User user = createUser();
        PhoneAction.addUser(user);
        // Create and add 100 random persons associated with the user
        for (int i = 0; i < 100; i++) {
            String[] personInfo = generateRandomPersonInfo();
            Person person = new Person(personInfo, user);
            PhoneAction.addPerson(person, user);
        }

        System.out.println("Added 1 user and 100 random persons successfully.");
    }
    
    /**
     * Creates a user with predefined information.
     * 
     * @return User object with set information.
     */
    private static User createUser() {
        String[] userInfo = {
            "Michele", "Leuti", "UserTest", "Password123!"
        };
        return new User(userInfo); 
    }
    
    /**
     * Generates random information for a person.
     * 
     * @return An array containing random person information including name, address, phone number, and age.
     */
    private static String[] generateRandomPersonInfo() {
        String firstName = firstNames[random.nextInt(firstNames.length)];
        String lastName = lastNames[random.nextInt(lastNames.length)];
        String address = "Random Address " + (random.nextInt(900) + 100); // Generates an address number between 100 and 999
        String phone = String.format("%03d-%03d-%04d", random.nextInt(1000), random.nextInt(1000), random.nextInt(10000)); // Random US-like phone number
        String age = String.valueOf(random.nextInt(60) + 20); // Random age between 20 and 79

        return new String[]{firstName, lastName, address, phone, age};
    }
    
    /**
     * Main method to run the data generation process.
     * 
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
    	create();
	}
}