package logic;

import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.util.UUID;

/**
 * Represents a person with contact information. This class encapsulates details such as name, surname,
 * address, phone number, and age, along with unique identifiers for the person and their associated user.
 * It provides constructors for creating a new person instance either with a new unique identifier or with an existing one,
 * and includes input validation for the contact information.
 * @author Leuti Michele
 */
public class Person {
	private String name;
	private String surname;
	private String address;
	private String phone;
	private Integer age;
	private UUID id;
	private UUID userID;
	
	 /**
     * Constructs a new Person instance with automatically generated unique identifier (UUID) for the person and their associated user.
     * Validates the provided information before assigning it to the person.
     * 
     * @param personInfo An array of strings containing the person's name, surname, address, phone number, and age.
     * @param user The user associated with this person, used to set the userID to the user's UUID.
     * @throws IllegalArgumentException If any of the personInfo elements are empty or if the phone number and age are not valid.
     */
	public Person(String[] personInfo, User user) {
		this.checkValidity(personInfo);
		this.name = personInfo[0];
		this.surname = personInfo[1];
		this.address = personInfo[2];
		this.phone = personInfo[3];
		this.age = Integer.parseInt(personInfo[4]);
		this.userID = user.getId();
		this.id = UUID.randomUUID();
	}
	
	/**
     * Constructs a new Person instance with a specified unique identifier for the person.
     * This constructor is typically used for creating person instances based on data retrieved from a database or external source.
     * 
     * @param personInfo An array of strings containing the person's name, surname, address, phone number, and age.
     * @param user The user associated with this person.
     * @param id The unique identifier of the person in string format.
     */
	public Person(String[] personInfo, User user, String id) {
		this.name = personInfo[0];
		this.surname = personInfo[1];
		this.address = personInfo[2];
		this.phone = personInfo[3];
		this.age = Integer.parseInt(personInfo[4]);
		this.id = UUID.fromString(id);
		this.userID = user.getId();
	}
	
	/**
     * Constructs a new Person instance intended for creating a new user with only name and surname specified.
     * This constructor is used when minimal information is provided, and a new unique identifier is automatically generated.
     * 
     * @param isUser Indicates that this constructor is being used to create a new user.
     * @param info An array containing the person's (or user's) name and surname.
     * @throws IllegalArgumentException If the name or surname fields are empty.
     */
	public Person(boolean isUser, String[] info) {
			checkIfNotEmpty(info[0]);
			checkIfNotEmpty(info[1]);
			this.name = info[0];
			this.surname = info[1];
			this.id = UUID.randomUUID();
	}
	
	/**
     * Constructs a new Person instance with a specified unique identifier, used for users with known IDs.
     * This constructor initializes a person with their name, surname, and an existing unique identifier, without validating the input.
     * 
     * @param info An array containing the person's name and surname.
     * @param id The unique identifier of the person in string format.
     */
	public Person(String[] info, String id) {
			this.name = info[0];
			this.surname = info[1];
			this.id = UUID.fromString(id);
		}
	
	/**
     * Validates the person's contact information, ensuring that no fields are empty, and that the phone number and age are valid.
     * 
     * @param personInfo An array of strings containing the person's contact information to be validated.
     * @throws IllegalArgumentException If any validation checks fail.
     */
	private void checkValidity(String[] personInfo) {
		checkIfNotEmpty(personInfo[0]);
		checkIfNotEmpty(personInfo[1]);
		checkIfNotEmpty(personInfo[2]);
		checkIfNotEmpty(personInfo[3]);
		checkIfNotEmpty(personInfo[4]);
		checkIfValidPhone(personInfo[3]);
		checkIfValidAge(Integer.parseInt(personInfo[4]));
	}
	
	/**
     * Retrieves the name of the person.
     * 
     * @return The name of the person.
     */
	public String getName() {
	    return this.name;
	}
	
	/**
     * Sets the name of the person after validating that the input is not empty.
     * 
     * @param name The new name to be assigned to the person.
     * @throws IllegalArgumentException If the name is empty or consists only of whitespace characters.
     */
	public void setName(String name) {
		checkIfNotEmpty(name);
		this.name = name;
	}
	
	/**
     * Retrieves the surname of the person.
     * 
     * @return The surname of the person.
     */
	public String getSurname() {
	    return this.surname;
	}
	
	 /**
	 * Sets the surname of the person after validating that the input is not empty.
	 * 
	 * @param surname The new surname to be assigned to the person.
	 * @throws IllegalArgumentException If the surname is empty or consists only of whitespace characters.
	 */
	public void setSurname(String surname) {
    	checkIfNotEmpty(surname);
    	this.surname = surname;
	}
	
	/**
     * Retrieves the address of the person.
     * 
     * @return The address of the person.
     */
	public String getAddress() {
	    return this.address;
	}
	
	/**
     * Sets the address of the person after validating that the input is not empty.
     * 
     * @param address The new address to be assigned to the person.
     * @throws IllegalArgumentException If the address is empty or consists only of whitespace characters.
     */
	public void setAddress(String address) {
    	checkIfNotEmpty(address);
    	this.address = address;
	}
	
	 /**
     * Retrieves the phone number of the person.
     * 
     * @return The phone number of the person.
     */
	public String getPhone() {
	    return this.phone;
	}
	
	/**
     * Sets the phone number of the person after validating that the input is not empty and conforms to a valid phone number format.
     * 
     * @param phone The new phone number to be assigned to the person.
     * @throws IllegalArgumentException If the phone number is empty, consists only of whitespace characters, or does not match a valid phone number pattern.
     */
	public void setPhone(String phone) {
    	checkIfNotEmpty(phone);
	 	checkIfValidPhone(phone);
    	this.phone = phone;	
	}
	
	 /**
     * Retrieves the age of the person.
     * 
     * @return The age of the person.
     */
	public Integer getAge() {
	    return this.age;
	}
	
	/**
     * Sets the age of the person after validating that the input is not negative and not empty.
     * 
     * @param age The new age to be assigned to the person.
     * @throws IllegalArgumentException If the age is negative or the input string is empty.
     */
	public void setAge(int age) {
    	checkIfNotEmpty(Integer.toString(age));
    	checkIfValidAge(age);
    	this.age = age;
	}
	
	/**
     * Retrieves the unique identifier (UUID) of the person.
     * 
     * @return The UUID of the person.
     */
	public UUID getId() {
		return this.id;
	}
	
	/**
     * Retrieves the unique identifier (UUID) of the user associated with this person.
     * 
     * @return The UUID of the associated user.
     */
	public UUID getUserId() {
		return this.userID;
	}
	
	 /**
     * Checks if a given string is not empty or does not consist solely of whitespace characters.
     * This method is used to validate that input fields are not left blank.
     *
     * @param info The string to be checked for emptiness.
     * @return True if the string is not empty; otherwise, it throws an IllegalArgumentException.
     * @throws IllegalArgumentException if the input string is empty or consists only of whitespace characters.
     */
	public boolean checkIfNotEmpty(String info) {
		if (info.equals("") || info.trim().isEmpty()) {
	        throw new IllegalArgumentException("All fields must not be empty!");
	    }
		return true;
	}
	
	/**
     * Validates that a given phone number string matches a general phone number pattern.
     * This method supports international phone number formats, including optional country codes.
     *
     * @param phone The phone number string to be validated.
     * @return True if the phone number is valid; otherwise, it throws an IllegalArgumentException.
     * @throws IllegalArgumentException if the phone number does not match the expected format.
     */
	public boolean checkIfValidPhone(String phone) {
	    String allCountryRegex = "(?:\\+\\d{1,3}[-\\s]?)?(?:\\(\\d{1,3}\\)[-\\.\\s]?)?[\\d-\\s]{3,}";

	    Pattern p = Pattern.compile(allCountryRegex);
	    Matcher m = p.matcher(phone);
	    if (!m.find()) {
	    	throw new IllegalArgumentException("The phone field is invalid");
	    }
	    return true;
	}
	
	/**
     * Validates that a given age is a non-negative integer.
     * This method ensures that the age value is logically valid, representing a real, non-negative age.
     *
     * @param age The age to be validated.
     * @return True if the age is non-negative; otherwise, it throws an IllegalArgumentException.
     * @throws IllegalArgumentException if the age is a negative value.
     */
	public boolean checkIfValidAge(int age) {
		if (age < 0) {
			throw new IllegalArgumentException("The age field must not be a negative value!");
		}
		return true;
	}
	
	 /**
     * Returns a string representation of the person's information.
     * The information includes the person's name, surname, address, phone number, and age, each separated by a newline.
     *
     * @return A string representation of the person.
     */
	@Override 
	public String toString() {
		return this.name + "\n" + this.surname + "\n" + this.address + "\n" + this.phone + "\n" + this.age.toString();
	}
	
}

