package table;

/**
 * Represents a single cell that can hold additional information in the form of a String.
 * This class can be used in various contexts where a basic unit of information storage is needed,
 * encapsulating the concept of a 'cell' that can contain any textual data.
 * @author Leuti Michele
 */
public class Cell {
	private String info;
	
	/** 
     * Constructs an empty cell with no initial information.
     */
	public Cell() {
		this.info = "";
	}
	
	/**
     * Constructs a cell with the given additional information.
     * This constructor allows for immediate association of information with the cell upon its creation.
     * 
     * @param info The additional information to associate with the cell. This could be any text, such as a name, a number, etc.
     */
	public Cell(String info) {
	    this.info = info;
	}
	
	/**
     * Retrieves the additional information associated with the cell.
     * This method allows for accessing the content stored within the cell.
     * 
     * @return The additional information stored in the cell, returned as a String.
     */
	public String getInfo() {
	    return info;
	}

	/**
     * Sets or updates the additional information associated with the cell.
     * This method allows for modifying the content of the cell post-creation.
     * 
     * @param info The new additional information to be stored in the cell. Previous information will be overwritten.
     */
	public void setInfo(String info) {
	    this.info = info;
	}

	 /**
     * Generates a string representation of the cell's content.
     * This override of the toString method allows for easy printing or logging of the cell's information.
     * 
     * @return A string representation of the cell, specifically the information it contains.
     */
	@Override
	public String toString(){
		return this.info.toString();
	}
}
