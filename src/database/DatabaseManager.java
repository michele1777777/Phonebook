package database;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;
import logic.*;

/**
 * Provides functionalities for managing database operations related to Users and Persons.
 * It encapsulates the logic for connecting to the database, as well as adding, updating, 
 * selecting, and deleting Users and Persons from the database.
 * 
 * This manager uses a properties file (database_credentials.properties) for retrieving 
 * database connection details such as URL, username, and password.
 * 
 * @author Leuti Michele
 */

public class DatabaseManager {
    private static final String PROPERTIES_FILE = "database_credentials.properties";
    private static String url;
    private static String username;
    private static String password;
    
    // Static initializer for database connection properties
    static {
        Properties prop = new Properties();
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            prop.load(input);
            url = prop.getProperty("db_url");
            username = prop.getProperty("username");
            password = prop.getProperty("password");
        } catch (IOException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Error reading database properties file", ex);
        }
    }
    
    /**
     * Establishes a connection to the database using credentials obtained from the properties file.
     * 
     * @return Connection to the database
     * @throws SQLException If a database access error occurs or the url is {@code null}
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }
    
    /**
     * Inserts a new user into the database. Before insertion, it checks if the username already exists
     * to prevent duplicates.
     * 
     * @param user The user to be inserted into the database.
     * @throws IllegalArgumentException If the username already exists in the database.
     * @throws SQLException If a database access error occurs or the SQL statement is incorrect.
     */
    public static void insertUser(User user) {
        String selectSql = "SELECT COUNT(*) FROM User WHERE username = ?";
        String insertSql = "INSERT INTO User (id, name, surname, username, password) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(selectSql);
             PreparedStatement insertStatement = connection.prepareStatement(insertSql)) {

            selectStatement.setString(1, user.getUsername());
            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    if (count > 0) {
                        throw new IllegalArgumentException("This username already exists"); 
                    }
                }
            }

            insertStatement.setString(1, user.getId().toString());
            insertStatement.setString(2, user.getName());
            insertStatement.setString(3, user.getSurname());
            insertStatement.setString(4, user.getUsername());
            insertStatement.setString(5, user.getPassword());
            insertStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Deletes a user and all associated persons from the database.
     * 
     * @param user The user to be deleted.
     * @throws SQLException If a database access error occurs or the SQL statement is incorrect.
     */
    public static void deleteUser(User user) {
    	deleteAllPersonsByUserId(user);
        String sql = "DELETE FROM User WHERE id = ?";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Selects a user by username from the database.
     * 
     * @param username The username of the user to be selected.
     * @return User object if the user exists, null otherwise.
     * @throws SQLException If a database access error occurs or the SQL statement is incorrect.
     */
    public static User selectUserByUsername(String username) {
        String sql = "SELECT * FROM User WHERE username = ?";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	String[] info = new String[4];
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String password = resultSet.getString("password");
                    info[0] = name;
                    info[1] = surname;
                    info[2] = username;
                    info[3] = password;
                    User u = new User(info, null, id);
                    return new User(info, selectAllPersonsByUserId(u), id);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Retrieves a person by their ID from the database.
     * 
     * @param id The unique identifier of the person to retrieve.
     * @param user The user associated with the person. This is used to establish relational context.
     * @return The Person object if found, null otherwise.
     * @throws SQLException If a database access error occurs or the SQL statement is incorrect.
     */
    public static Person selectPersonById(String id, User user) {
        String sql = "SELECT * FROM Person WHERE id = ?";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                	String[] info = new String[5];
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String address = resultSet.getString("address");
                    String phone = resultSet.getString("phone");
                    int age = resultSet.getInt("age");
                    
                    info[0] = name;
                    info[1] = surname;
                    info[2] = address;
                    info[3] = phone;
                    info[4] = Integer.toString(age);
                    
                    return new Person(info, user, id);
                    
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Inserts a new person into the database and associates it with a user.
     * 
     * @param person The person to insert into the database.
     * @param u The user to associate with the person.
     * @throws SQLException If a database access error occurs or the SQL statement is incorrect.
     */
    public static void insertPerson(Person person, User u) {
        String sql = "INSERT INTO Person (id, userId, name, surname, address, phone, age) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, person.getId().toString());
            preparedStatement.setString(2, person.getUserId().toString());
            preparedStatement.setString(3, person.getName());
            preparedStatement.setString(4, person.getSurname());
            preparedStatement.setString(5, person.getAddress());
            preparedStatement.setString(6, person.getPhone());
            preparedStatement.setInt(7, person.getAge());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        u.getTable().addContact(person);
    }
    
    /**
     * Deletes a person from the database based on their ID.
     * 
     * @param person The person to delete.
     * @param u The user associated with the person. This parameter is used to update the user's local data.
     * @throws SQLException If a database access error occurs or the SQL statement is incorrect.
     */
    public static void deletePerson(Person person, User u) {
        String sql = "DELETE FROM Person WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, person.getId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        u.getTable().deleteContact(person);
    }
    
    /**
     * Deletes all persons associated with a given user from the database.
     * 
     * @param user The user whose associated persons are to be deleted.
     */
    private static void deleteAllPersonsByUserId(User user){
        if (user.getTable().getPersons().isEmpty()) {
            return; 
        }
    	for (int i = user.getTable().getPersons().size() - 1; i >= 0; i--) {
    		deletePerson(user.getTable().getPersons().get(i), user);
    	}
    }
    
    /**
     * Selects all persons associated with a given user from the database.
     * 
     * @param user The user whose associated persons are to be selected.
     * @return A list of Person objects associated with the user.
     * @throws SQLException If a database access error occurs or the SQL statement is incorrect.
     */
    public static ArrayList<Person> selectAllPersonsByUserId(User user) {
    	ArrayList<Person> persons = new ArrayList<>();
        String sql = "SELECT * FROM Person WHERE userId = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getId().toString());
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                	String[] info = new String[6];
                    String id = resultSet.getString("id");
                    String name = resultSet.getString("name");
                    String surname = resultSet.getString("surname");
                    String address = resultSet.getString("address");
                    String phone = resultSet.getString("phone");
                    int age = resultSet.getInt("age");
                    info[0] = name;
                    info[1] = surname;
                    info[2] = address;
                    info[3] = phone;
                    info[4] = Integer.toString(age);
                    persons.add(new Person(info, user, id));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persons;
    }
    
    /**
     * Updates the details of an existing person in the database.
     * 
     * @param person The person with updated details.
     * @param user The user associated with the person. This parameter is used to update the user's local data.
     * @throws SQLException If a database access error occurs or the SQL statement is incorrect.
     */
    public static void updatePerson(Person person, User user) {
        String sql = "UPDATE Person SET name = ?, surname = ?, address = ?, phone = ?, age = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, person.getName());
            preparedStatement.setString(2, person.getSurname());
            preparedStatement.setString(3, person.getAddress());
            preparedStatement.setString(4, person.getPhone());
            preparedStatement.setInt(5, person.getAge());
            preparedStatement.setString(6, person.getId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        user.getTable().modifyContact(person);
    }
    
    /**
     * Updates the details of an existing user in the database.
     * 
     * @param user The user with updated details.
     * @throws SQLException If a database access error occurs or the SQL statement is incorrect.
     */
    public static void updateUser(User user) {
        String sql = "UPDATE User SET name = ?, surname = ?, username = ?, password = ? WHERE id = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.setString(5, user.getId().toString());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Checks if a username already exists in the database.
     * 
     * @param username The username to check.
     * @return true if the username exists, false otherwise.
     * @throws SQLException If a database access error occurs or the SQL statement is incorrect.
     */
    public static boolean doesUsernameExist(String username) {
        String sql = "SELECT COUNT(*) FROM User WHERE username = ?";
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, username);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    // Check the count of usernames found
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /**
     * Searches for persons in the database based on a given input string, which can be a name or surname.
     * This method allows for partial matches.
     * 
     * @param input The input string to match against person names or surnames.
     * @param user The user context for the search. Only persons associated with this user are considered.
     * @return A list of persons that match the search criteria.
     * @throws SQLException If a database access error occurs or the SQL statement is incorrect.
     */
    public static ArrayList<Person> searchPersons(String input, User user) {
        ArrayList<Person> matchingPersons = new ArrayList<>();
        String sql;
        String[] parts = input.split("\\s+"); // Split input by spaces
        
        try (Connection connection = getConnection()) {
            if (parts.length > 1) { // Name and surname provided
                sql = "SELECT * FROM Person WHERE name LIKE ? AND surname LIKE ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, parts[0] + "%");
                    preparedStatement.setString(2, parts[1] + "%");
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                    	String[] info = new String[6];
                        String id = resultSet.getString("id");
                        String name = resultSet.getString("name");
                        String surname = resultSet.getString("surname");
                        String address = resultSet.getString("address");
                        String phone = resultSet.getString("phone");
                        int age = resultSet.getInt("age");
                        info[0] = name;
                        info[1] = surname;
                        info[2] = address;
                        info[3] = phone;
                        info[4] = Integer.toString(age);
                        matchingPersons.add(new Person(info, user, id));
                    }
                }
            } else { // Single name or surname provided
                sql = "SELECT * FROM Person WHERE name LIKE ? OR surname LIKE ?";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, input + "%");
                    preparedStatement.setString(2, input + "%");
                    ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                    	String[] info = new String[6];
                        String id = resultSet.getString("id");
                        String name = resultSet.getString("name");
                        String surname = resultSet.getString("surname");
                        String address = resultSet.getString("address");
                        String phone = resultSet.getString("phone");
                        int age = resultSet.getInt("age");
                        info[0] = name;
                        info[1] = surname;
                        info[2] = address;
                        info[3] = phone;
                        info[4] = Integer.toString(age);
                        matchingPersons.add(new Person(info, user, id));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return matchingPersons;
    }
}
