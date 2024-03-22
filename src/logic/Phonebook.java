 package logic;

import java.util.Scanner;

/**
 * The Phonebook class provides a console-based interface for managing a phonebook application.
 * This class allows users to login or sign-in, manage contacts (add, modify, delete), and delete their account.
 * @author Leuti Michele
 */
public class Phonebook {
	private User user;
	
	/**
     * Constructs a new Phonebook instance associated with a specific user.
     *
     * @param u The user who will be interacting with the phonebook.
     */
	public Phonebook(User u) {
		this.user = u;
	}
	
	/**
     * Default constructor for Phonebook. Initializes a new Phonebook with no user.
     */
	public Phonebook() {
	}
	
	/**
     * Runs the main loop of the phonebook application, handling user input for various actions
     * such as logging in, signing in, managing contacts, and deleting the account.
     */
	public void run(){
		Scanner s = new Scanner(System.in);
		while(true) {
			System.out.println("Type \"1\" to login \nType \"2\" to sign-in");
			String input = s.nextLine();
			if (input.equals("1")) {
				System.out.println("Insert Username: ");
				String user = s.nextLine();
				System.out.println("Insert Password: ");
				input = s.nextLine();
				try{
					User u = PhoneAction.searchUser(user);
					if (u != null) {
						if (u.checkPassword(input)) {
							this.user = u;
						}
						else {
							System.out.println("Password is not correct.");
							continue;
						}
					}
					else {
						System.out.println("Invalid username or password.");
						continue;
					}
				}
				catch(Exception e) {
					e.printStackTrace();
					continue;
				}
				
			}
			else if (input.equals("2")) {
				System.out.println("Please add the contact info: NAME; SURNAME; USERNAME; PASSWORD \nNote: Password must contains at least 8 characters and at most 20 characters.\n"
						+ "at least one digit.\n"
						+ "at least one upper case alphabet.\n"
						+ "at least one lower case alphabet.\n"
						+ "at least one special character which includes !@#$%&*()-+=^.\n"
						+ "doesn’t contain any white space.");
				String[] info = new String[4];
				info = s.nextLine().split("; ");
				
				try {
					this.user = new User(info);
					PhoneAction.addUser(this.user);
				}
				catch (Exception e){
					System.out.println(e.getMessage());
					continue;
				}
			}
			else {
				System.out.println("Invalid command.");
				continue;
			}
			break;
		}
		while(true) {
			System.out.println(this.user.getTable().toString());
			System.out.println("Type \"add\" for add a new contact \nType \"delete\" for delete a contact \nType \"modify\" for update a contact information\nType \"delete account\" for delete the account \nType \"exit\" for exit the program");
			String input = s.nextLine();
			if (input.toLowerCase().equals("add")) {
				String[] info = new String[5];
				System.out.println("Please add the contact info: NAME; SURNAME; ADDRESS; PHONE; AGE");
				info = s.nextLine().split("; ");
				try{
					Person p = new Person(info, this.user);
					PhoneAction.addPerson(p, this.user);
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
					continue;
				}
			}
			else if (input.toLowerCase().equals("modify")) {
				try {
					System.out.println("Please add the row index of the contact to modify");
					int index = Integer.parseInt(s.nextLine());
					Person p = this.user.getTable().getPersons().get(index - 1);
					while (true) {
						System.out.println("Contact\n" + p.toString());
						System.out.println("Type \"1\" for edit the name\nType \"2\" for edit the surname\nType \"3\" for edit the address\nType \"4\" for edit the phone number\nType \"5\" for edit the age\nType \"exit\" to exit the contact card");
						String index1 = s.nextLine();
						String newInfo = "";
						switch (index1) {
						case "1": 
							System.out.println("Please enter the new name");
							newInfo = s.nextLine();
							p.setName(newInfo);
							continue;
						case "2":
							System.out.println("Please enter the new surname");
							newInfo = s.nextLine();
							p.setSurname(newInfo);
							continue;
						case "3":
							System.out.println("Please enter the new address");
							newInfo = s.nextLine();
							p.setAddress(newInfo);
							continue;
						case "4":
							System.out.println("Please enter the new phone number");
							newInfo = s.nextLine();
							p.setPhone(newInfo);
							continue;
						case "5":
							System.out.println("Please enter the new age");
							newInfo = s.nextLine();
							p.setAge(Integer.parseInt(newInfo));
							continue;
						case "exit":
							System.out.println("exit ended correctly");
							break;
						default: ;
							System.out.println("Choose a correct option");
							continue;
						}
					PhoneAction.editPerson(user, p);;
					break;
					}
				}
				catch(Exception e) {
					System.out.println(e.getMessage());
				}
			}
			else if (input.toLowerCase().equals("delete")) {
				try {
					System.out.println("Please add the row index/indexes of the person/persons to delete: (Ex: 1; 3; 5..etc.)");
					String rows[] = s.nextLine().split("; ");
					int[] indexes = new int[rows.length];
					for (int i = 0; i < indexes.length; i++) {
						indexes[i] = Integer.parseInt(rows[i]) - 1;
					}
					String[] ids = this.user.getTable().selectContacts(indexes);
					for (String id : ids) {
						Person p = PhoneAction.searchPerson(user, id);
						PhoneAction.deletePerson(p, user);
					}
				}
				
				catch(Exception e){
					System.out.println(e.getMessage());
				}
			}
			else if (input.toLowerCase().equals("delete account")) {
				System.out.println("Are you sure? Y/N");
				input = s.nextLine();
				if (input.toLowerCase().equals("y")) {
					PhoneAction.deleteUser(user);
					break;
				}
				else {
					continue;
				}
			}
			else if (input.toLowerCase().equals("exit")) {
				System.out.println("PhoneBook ended successfully.");
				break;
			}
			else if (input.toLowerCase().equals("")) {
				continue;
			}
			else {
				System.out.println("Invalid command");
			}
		}
		s.close();
	}
	
	/**
     * The entry point for the Phonebook application.
     * 
     * @param args The command-line arguments (not used).
     */
	public static void main(String[] args) {
		Phonebook phone = new Phonebook();
		phone.run();
	}
}
