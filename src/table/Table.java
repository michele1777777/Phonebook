package table;
import logic.Person;
import java.util.ArrayList;
import logic.User;

/**
 * Represents a table containing rows of data associated with a specific user and their contacts.
 * This class allows for operations on the table such as adding, modifying, and deleting contacts,
 * as well as retrieving the user's contacts as rows or persons.
 * @author Leuti Michele
 */
public class Table {
	private User user;
	private ArrayList<Row> rows;
	
	/**
     * Constructs a table for the specified user without any initial contacts.
     *
     * @param u The user associated with this table.
     */
	public Table(User u) {
		this.rows = new ArrayList<Row>();
	}
	
	/**
     * Constructs a table for the specified user, initializing it with a list of contacts.
     * Each contact is represented as a row in the table.
     *
     * @param u The user associated with this table.
     * @param contacts A list of Person objects to be added as rows in the table.
     */
	public Table(User u, ArrayList<Person> contacts) {
		this.rows = new ArrayList<Row>();
		for (int i = 0; i < contacts.size(); i++) {
			Row r = new Row(contacts.get(i));
			this.rows.add(r);
		}
	}
	
	/**
     * Retrieves the list of rows in the table.
     *
     * @return A list of Row objects representing the user's contacts.
     */
	public ArrayList<Row> getRows() {
		return this.rows;
	}
	
	/**
     * Retrieves a list of Person objects from the rows in the table.
     * This method extracts and returns the contacts as a list of Person objects.
     *
     * @return A list of Person objects representing the contacts in the table.
     */
	public ArrayList<Person> getPersons(){
		ArrayList <Person> persons = new ArrayList<Person>();
		for (Row row : this.rows) {
			persons.add(row.getPerson());
		}
		return persons;
	}
	
	/**
     * Retrieves the user associated with this table.
     *
     * @return The User object associated with this table.
     */
	public User getUser() {
		return this.user;
	}
	
	/**
     * Adds a new contact to the table.
     * This method creates a new row for the specified Person object and adds it to the table.
     *
     * @param p The Person object to be added as a new contact.
     */
	public void addContact(Person p) {
		Row r = new Row(p);
		this.rows.add(r);
	}
	
	/**
     * Modifies the information of an existing contact in the table.
     * This method finds the row associated with the specified Person object and updates its information.
     *
     * @param p The Person object with updated information.
     */
	public void modifyContact(Person p) {
		for (int i = 0 ; i < this.rows.size(); i++){
			if (this.rows.get(i).getPerson().getId() == p.getId()) {
				this.rows.get(i).modifyInfo(p);
			}
		}
	}
	
	/**
     * Deletes a contact from the table.
     * This method removes the row associated with the specified Person object from the table.
     *
     * @param p The Person object to be removed from the table.
     */
	public void deleteContact(Person p) {
		for (int i = 0 ; i < this.rows.size(); i++){
			if (this.rows.get(i).getPerson().getId() == p.getId()) {
				this.rows.remove(i);
			}
		}
	}
	
	/**
     * Selects contacts by their row indexes and returns their IDs.
     * This method is useful for operations that require identifying specific contacts by their position in the table.
     *
     * @param rowIndexes An array of integers representing the indexes of the rows to be selected.
     * @return An array of String objects representing the IDs of the selected contacts.
     */
	public String[] selectContacts(int[] rowIndexes) {
		StringBuilder b = new StringBuilder();
		for (int index : rowIndexes) {
			b.append(this.rows.get(index).getPerson().getId().toString() + "/");
		}
		return b.toString().split("/"); 
	}
	
	/**
     * Updates the user associated with this table.
     * This method allows for changing the user and, implicitly, the context of the table.
     *
     * @param user The new User object to be associated with this table.
     */
	public void modifyUser(User user) {
		this.user = user;
	}
	
	/**
     * Provides a string representation of the table, formatted for display.
     * This method organizes the table's data into a readable format, listing each contact's information.
     *
     * @return A string representation of the table, including headers and each row's data.
     */
	@Override
	public String toString(){
		StringBuilder str = new StringBuilder("Name | Surname | Phone\n"); 
		for (Integer i = 0; i < this.rows.size(); i++) {
			Integer t = i + 1;
			String rowIndex = t.toString();
			str.append(rowIndex + ". ");
			for (Integer j = 0; j < 3; j++) {
				if (j == 2) {
					str.append(this.rows.get(i).getCell(j).toString() + "\n");
				}
				else{
					str.append(this.rows.get(i).getCell(j).toString() + " ");
				}
			}
		}
		return str.toString();
	}

}
