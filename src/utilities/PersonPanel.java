package utilities;

import javax.swing.JPanel;

import logic.Person;

/**
 * A custom JPanel that represents a single person's information along with interactive SVG buttons for contact management.
 * @author Leuti Michele
 */
public class PersonPanel extends JPanel{
	private static final long serialVersionUID = -8945147436238603685L;
	private Person p;
	private boolean isSelected;
	private SvgButton contactSvgButton;
	private SvgButton deleteSvgButton;
	private SvgButton editSvgButton;
	
	/**
     * Constructs a PersonPanel for the specified Person object.
     * 
     * @param p The person to be displayed in this panel.
     */
	public PersonPanel(Person p) {
		this.p = p;
		this.isSelected = false;
	}
	
	/**
     * Sets the selection state of this panel.
     * 
     * @param isSelected True if the panel is selected, false otherwise.
     */
	public void setSelected(boolean is) {
		this.isSelected = is;
	}
	
	/**
     * Retrieves the selection state of this panel.
     * 
     * @return True if the panel is selected, false otherwise.
     */
	public boolean getSelected() {
		return this.isSelected;
	}
	
	/**
     * Sets the SVG button used for delete actions associated with the person.
     * 
     * @param deleteSvgButton The SVG button for delete actions.
     */
	public void setDeleteSvgButton(SvgButton deleteSvgButton) {
		this.deleteSvgButton = deleteSvgButton;
	}

	/**
     * Retrieves the delete SVG button.
     * 
     * @return The SVG button used for delete actions.
     */
	public SvgButton getDeleteButton() {
		return this.deleteSvgButton;
	}
	
	/**
     * Sets the SVG button used for displaying contact information.
     * 
     * @param contactSvgButton The SVG button for contact information.
     */
	public void setContactSvgButton(SvgButton contactSvgButton) {
		this.contactSvgButton = contactSvgButton;
	}
	
	/**
     * Retrieves the contact SVG button.
     * 
     * @return The SVG button used for displaying contact information.
     */
	public SvgButton getContactButton() {
		return this.contactSvgButton;
	}
	
	/**
     * Sets the SVG button used for editing actions associated with the person.
     * 
     * @param editSvgButton The SVG button for edit actions.
     */
	public void setEditSvgButton(SvgButton editSvgButton) {
		this.editSvgButton = editSvgButton;
	}
	
	/**
     * Retrieves the edit SVG button.
     * 
     * @return The SVG button used for edit actions.
     */
	public SvgButton getEditButton() {
		return this.editSvgButton;
	}
	
	/**
     * Retrieves the Person object associated with this panel.
     * 
     * @return The Person object represented by this panel.
     */
	public Person getPerson() {
		return this.p;
	}
}
