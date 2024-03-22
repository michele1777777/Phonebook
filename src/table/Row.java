package table;
import logic.Person;

/**
 * Represents a row in a table, which can hold information about a person across multiple cells.
 * A row is essentially a collection of cells, each cell designed to hold a specific piece of data about a person,
 * such as their name, surname, or phone number. Rows can be initialized with or without person data.
 * @author Leuti Michele
 */
public class Row {
	private Cell[] cells;
	private Person p;

	/**
     * Constructs a row and initializes each cell with data from the given person.
     * If the person is not null, the row is initialized with the person's name, surname, and phone number.
     * 
     * @param p The person whose data is to be used to initialize the cells in the row. Can be null.
     */
	public Row(Person p) {
		if (p != null) {
			this.p = p;
		    this.cells = new Cell[3]; // Initializes the array with a fixed length of 3
		    for (int i = 0; i < 3; i++) {
		        this.cells[i] = new Cell(); // Initializes each cell in the row
		    }
		    this.cells[0].setInfo(p.getName());
		    this.cells[1].setInfo(p.getSurname());
		    this.cells[2].setInfo(p.getPhone());
		}	
	}
	
	/**
     * Constructs an empty row with a predefined number of cells.
     * This constructor initializes a row without associating it with a specific person,
     * creating empty cells ready to be populated with data.
     */
	public Row() {
	    this.cells = new Cell[3]; // Initializes the array with a fixed length of 3
	    for (int i = 0; i < 3; i++) {
	        this.cells[i] = new Cell(); // Initializes each cell in the row
	    }
	}
	
	/**
     * Retrieves the cell at the specified index within the row.
     * This method allows for direct access to a specific cell's data.
     * 
     * @param i The index of the cell to retrieve, where the first cell is at index 0.
     * @return The Cell object at the specified index.
     */
	public Cell getCell(int i) {
		return this.cells[i];
	}
	
	/**
     * Retrieves the array of cells that constitute the row.
     * This method provides access to all cells within the row, allowing for iteration or manipulation.
     * 
     * @return An array of Cell objects representing the data held within the row.
     */
	public Cell[] getCells() {
	    return this.cells;
	}
	
	/**
     * Retrieves the person associated with this row.
     * If the row was initialized with person data, this method returns the corresponding Person object.
     * 
     * @return The Person object associated with the row, or null if the row was not initialized with a person.
     */
	public Person getPerson() {
		return this.p;
	}
	
	/**
     * Modifies the information contained within the row based on the data of a given person.
     * This method updates the cells of the row with the new person's name, surname, and phone number.
     * 
     * @param p The new person whose data should be used to update the row's cells.
     */
	public void modifyInfo(Person p) {
	    this.cells[0].setInfo(p.getName());
	    this.cells[1].setInfo(p.getSurname());
	    this.cells[2].setInfo(p.getPhone());
	}
	
}
