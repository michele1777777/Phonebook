package gui;
import javax.swing.*;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import logic.*;
import utilities.*;


public class PhonebookFrame extends JFrame implements ActionListener{
	private static final long serialVersionUID = 2059638999146781050L;
	protected static User user;
	private JPanel contactsPanel;
	private JPanel topPanel;
	private RoundedPanel personPanel;
	private JPanel coverPanel;
	private JPanel deletePanel;
	private RoundedPanel addPanel;
	private RoundedPanel userPanel;
	private ArrayList<PersonPanel> personsPanel;
	private SvgButton userButton;
	private SvgButton deleteButton;
	private SvgButton addButton;
	private SvgButton searchButton;
	private SvgButton doneButton;
	private SvgButton undoButton;
	private SvgButton pEditButton;
	private SvgButton uEditButton;
	private SvgButton uConfirmEdit;
	private SvgButton uCancelEdit;
	private SvgButton uEditPasswordButton;
	private SvgButton uEliminate;
	private SvgButton pBackButton;
	private SvgButton uBackButton;
	private SvgButton deletePerson;
	private SvgButton pConfirmEdit;
	private SvgButton pCancelEdit;
	private SvgButton logout;
	private SvgButton addCancelButton;
	private SvgButton addConfirmButton;
	private JTextField searchField;
	private JTextField pName;
	private JTextField pSurname;
	private JTextField pAddress;
	private JTextField pPhone;
	private JTextField pAge;
	private JTextField uName;
	private JTextField uSurname;
	private JTextField uUsername;
	private int selected;
	private Font pFont = new Font("a", Font.BOLD, 12);
	private Color background;
	private Color steelBlue;

	/**
     * Constructs a {@code PhonebookFrame} and initializes the user interface components.
     */
	public PhonebookFrame() {
		this.background = new Color(245,245,245);
		this.steelBlue = new Color(50,110,140);
		this.setup();
	}
	
	/**
     * Initializes the frame, setting up the main components and layout.
     */
	private void setup() {
		setTitle("Contacts");
		setSize(348, 450);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		getContentPane().setBackground(new Color (50, 110, 140)); // grey background
		setLocationRelativeTo(null);
		setVisible(true);
		setResizable(false);
		addMainComponent(false, false, user.getContacts());
	}
	
	/**
     * Adds the main components to the frame, including the top panel with action buttons,
     * a scrollable contacts panel, and an optional cover panel.
     * 
     * @param flag1 Indicates if certain UI elements should be disabled or made transparent (used for edit mode).
     * @param flag2 Indicates if the deletion mode is enabled, altering how contacts are displayed.
     * @param contacts The list of {@code Person} objects to display.
     */
	private void addMainComponent(boolean flag1, boolean flag2, ArrayList<Person> contacts) {
		this.createTopPanel(flag1);
		this.createScrollableContactPanel(contacts, flag2);
		this.createCoverPanel();
		if (!flag2) {
			add(coverPanel);
		}
        add(topPanel);
        add(contactsPanel);
	}
	
	/**
     * Removes the main components from the frame, typically in preparation for refreshing the UI or switching modes.
     */
	private void removeMainComponent() {
		this.contactsPanel.setVisible(false);
		this.topPanel.setVisible(false);
		this.coverPanel.setVisible(false);
		remove(topPanel);
		remove(coverPanel);
        remove(contactsPanel);
        repaint();
	}
	
	/**
     * Creates the cover panel that appears at the bottom part of the frame.
     * This panel is used to cover any unused space in the frame below the contacts list.
     * It is styled to match the overall application theme.
     */
	private void createCoverPanel() {
		coverPanel = new JPanel();
		coverPanel.setBackground(steelBlue);
		coverPanel.setBounds(0, this.contactsPanel.getY() + this.contactsPanel.getHeight(), 350, 450 - (this.contactsPanel.getY() + this.contactsPanel.getHeight()));
	}
	
	 /**
     * Creates the top panel containing the application's action buttons such as the user profile button, search, add, and delete contacts buttons.
     * This panel appears at the top of the frame and provides the primary interaction mechanisms for the user.
     *
     * @param flag Determines the state of the top panel; when true, certain elements may be disabled or made less visible, typically used in an edit mode.
     */
	private void createTopPanel(boolean flag) {
		this.topPanel = new RoundedPanel(25); // 25 is the radius for rounded corners
        if(!flag) {
        	this.topPanel.setBackground(background);
        }
        else {
        	this.topPanel.setBackground(new Color(255,255,255,155));
        }
		this.topPanel.setBounds(5,5,325,70);
        this.topPanel.setLayout(null);
        this.topPanel.setBorder(null);
        
        String filePath = "Images/user.svg";
		userButton = new SvgButton(filePath);
		userButton.setBorder(new RoundedCornerBorder(15, null));
		if (!flag) {
			userButton.setBackground(background);
			userButton.setEnabled(true);
		}
		else {
			userButton.setBackground(new Color(0,0,0,0));
			userButton.setEnabled(false);
		}
		userButton.setBounds(2,8,45,45);
		userButton.setBorder(null);
		userButton.addActionListener(this);
        topPanel.add(userButton);
        
        ShadowLabel Username = new ShadowLabel(user.getUsername());
        Username.setFont(new Font("Bold", Font.BOLD, 10));
        Username.setForeground(new Color(68,126,202));
        Username.setBounds(7,47,50,20);
        topPanel.add(Username);
        
        RoundedPanel panel = new RoundedPanel(25);
        if (!flag) {
        	panel.setBackground(background);
        }
        else {
        	panel.setBackground(new Color(0,0,0,0));
        }
        panel.setBorder(new RoundedCornerBorder(25, steelBlue));
        panel.setBounds(65, 30, 170, 34);
        panel.setLayout(null);
        topPanel.add(panel);
        
        
        if (!flag) {
        	searchField = RoundedTextField.createRoundedTextField("Search...", null);
        	searchField.setBackground(new Color (245,245,245));
        	searchField.setEditable(true);
        	searchField.setBounds(25,2,135,30);
            searchField.setBorder(new MatteBorder(0,0,0,0,new Color(0,0,0,0)));
            searchField.addActionListener(this);
            panel.add(searchField);
        }    
        
        filePath = "Images/search1.svg";
		searchButton = new SvgButton(filePath);
		searchButton.setBorder(new RoundedCornerBorder(15, null));
		if(!flag) {
			searchButton.setBackground(background);
			searchButton.setEnabled(true);
		}
		else {
			searchButton.setBackground(new Color(0,0,0,0));
			searchButton.setEnabled(false);
		}
		searchButton.setBounds(5, 9,15,15);
		searchButton.setBorder(null);
		searchButton.addActionListener(this);
        panel.add(searchButton);
        
        filePath = "Images/deleteUser1.svg";
		deleteButton = new SvgButton(filePath);
		deleteButton.setBorder(new RoundedCornerBorder(15, null));
		if(!flag) {
			deleteButton.setBackground(background);
			deleteButton.setEnabled(true);
		}
		else {
			deleteButton.setBackground(new Color(0,0,0,0));
			deleteButton.setEnabled(false);
		}
		deleteButton.setBounds(280,30,35,35);
		deleteButton.setBorder(null);
		deleteButton.addActionListener(this);
        topPanel.add(deleteButton);
        
        filePath = "Images/addUser.svg";
		addButton = new SvgButton(filePath);
		addButton.setBorder(new RoundedCornerBorder(15, null));
		if (!flag) {
			addButton.setBackground(background);
			addButton.setEnabled(true);
		}
		else {
			addButton.setBackground(new Color(0,0,0,0));
			addButton.setEnabled(false);
		}
		addButton.setBounds(240,30,35,35);
		addButton.setBorder(null);
		addButton.addActionListener(this);
        topPanel.add(addButton);
        
        filePath = "Images/icon2.svg";
		SvgIconPanel icon = new SvgIconPanel(filePath);
		if(!flag) {
			icon.setBackground(background);
		}
		else {
			icon.setBackground(new Color(0,0,0,0));
		}
		icon.setBounds(193,2,24,24);
		icon.setBorder(null);
        topPanel.add(icon);
        
        ShadowLabel contactsLabel = new ShadowLabel("CONTACTS");
        contactsLabel.setFont(new Font("ITALIC", Font.BOLD, 14));
        contactsLabel.setForeground(new Color  (50, 110, 140) );
        contactsLabel.setBounds(105, 2, 150, 20);
        topPanel.add(contactsLabel);
	}
	
	/**
     * Creates a scrollable panel for displaying contacts. This panel hosts individual contact panels, allowing the user to scroll through their contacts.
     * Contacts can be displayed in a default or deletion mode, controlled by the flag parameter.
     *
     * @param contacts The list of contacts to display within the panel.
     * @param flag Indicates if the panel is in deletion mode, which affects how contacts are displayed and interacted with.
     */
	private void createScrollableContactPanel(ArrayList<Person> contacts, boolean flag) {
	    this.personsPanel = new ArrayList<>();
	    
	    this.contactsPanel = new JPanel(null); // Set to null layout
	    if (!flag) {
	    	this.contactsPanel.setBounds(15, 85, 300, 315);
		    this.contactsPanel.setBackground(new Color(245, 245, 245));
		    this.contactsPanel.setBorder(null);
	    }
	    else {
	    	this.contactsPanel.setBounds(15, 85, 300, 285);
		    this.contactsPanel.setBackground(new Color(245, 245, 245));
		    this.contactsPanel.setBorder(null);
	    }

	    JPanel labelsPanel = new JPanel();
	    labelsPanel.setBackground(new Color(245, 245, 245));
	    // Even with a null layout in the parent, labelsPanel can use BoxLayout for its children
	    labelsPanel.setLayout(null);

	    // Sorting and adding person panels
	    Collections.sort(contacts, Comparator.comparing(person -> person.getName().toLowerCase()));
	    for (Person p : contacts) {
	        PersonPanel personPanel = flag ? this.createContactDeletePanel(contacts.indexOf(p), contacts) :
	                                         this.createContactPanel(contacts.indexOf(p), contacts);
	        labelsPanel.add(personPanel);
	    }

	    // Manually set the preferred size of labelsPanel based on content
	    int totalHeight = contacts.size() * 45; // Assuming each PersonPanel has a height of 45
	    labelsPanel.setPreferredSize(new Dimension(this.contactsPanel.getWidth(), totalHeight));

	    JScrollPane scrollPane = new JScrollPane(labelsPanel);
	    scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
	    scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    scrollPane.setBounds(0, 0, this.contactsPanel.getWidth(), this.contactsPanel.getHeight()); // Manually set bounds as contactPanel uses null layout

	    this.contactsPanel.add(scrollPane); // Add scrollPane directly to contactPanel
	}

	/**
     * Creates a panel for a single contact in the contacts list. This panel includes the contact's name, surname, and phone number.
     * It also prepares an SVG button for further interaction, such as entering edit mode or selecting the contact for deletion.
     *
     * @param i The index of the contact in the contacts list, used for positioning the panel within its parent container.
     * @param contacts The full list of contacts, from which the specific contact's details are extracted.
     * @return A {@code PersonPanel} object, which is a customized panel displaying the contact's information.
     */
	private PersonPanel createContactPanel(int i, ArrayList <Person> contacts) {
		PersonPanel panel = new PersonPanel(contacts.get(i));
		this.personsPanel.add(panel);
		panel.setBackground(background);
		panel.setMinimumSize(new Dimension(280, 45));
		panel.setMaximumSize(new Dimension(280, 45));
		panel.setBorder(new MatteBorder(0,0,2,0, new Color (50, 110, 140)));
		panel.setBounds(0, 45 * i, 280, 45);
		panel.setLayout(null);
		
        String filePath = "Images/contact2.svg";
		SvgButton icon = new SvgButton(filePath);
		icon.setBorder(new RoundedCornerBorder(15, null));
		icon.setBackground(background);
		icon.setMinimumSize(new Dimension(35,35));
		icon.setBounds(5,5,35,35);
		icon.setBorder(new MatteBorder(0,0,0,0,new Color(240,240,240)));
        panel.add(icon);
        this.personsPanel.get(i).setContactSvgButton(icon);
        this.personsPanel.get(i).getContactButton().addActionListener(this);
		
		JLabel label = new JLabel(contacts.get(i).getName() + " " + contacts.get(i).getSurname());
		label.setPreferredSize(new Dimension(90,30));
		label.setFont(new Font("Arial", Font.BOLD, 13));
		label.setBounds(65,3,150,20);
		panel.add(label);
		
		JLabel label1 = new JLabel(contacts.get(i).getPhone());
		label1.setForeground(new Color (50, 110, 140));
		label1.setPreferredSize(new Dimension(100,30));
		label1.setFont(new Font("Arial", Font.BOLD, 13));
		label1.setBounds(65, 22, 150, 20);
		panel.add(label1);
		
        return panel;
	}
	
	/**
     * Similar to {@link #createContactPanel(int, ArrayList)}, but specifically for deletion mode.
     * This version of the contact panel includes a button for selecting the contact for deletion.
     *
     * @param i The index of the contact in the contacts list.
     * @param contacts The full list of contacts.
     * @return A {@code PersonPanel} designed for contact selection and deletion.
     */
	private PersonPanel createContactDeletePanel(int i, ArrayList <Person> contacts) {
		PersonPanel panel = new PersonPanel(contacts.get(i));
		this.personsPanel.add(panel);
		panel.setBackground(background);
		panel.setMinimumSize(new Dimension(330, 45));
		panel.setMaximumSize(new Dimension(330, 45));
		panel.setBorder(new MatteBorder(0,0,2,0, new Color (50, 110, 140)));
		panel.setBounds(0, 45 * i, 330, 45);
		panel.setLayout(null);
		
        String filePath = "Images/contact2.svg";
		SvgButton icon = new SvgButton(filePath);
		icon.setBorder(new RoundedCornerBorder(15, null));
		icon.setBackground(background);
		icon.setMinimumSize(new Dimension(35,35));
		icon.setBounds(5,5,35,35);
		icon.setBorder(new MatteBorder(0,0,0,0,new Color(240,240,240)));
        panel.add(icon);
        this.personsPanel.get(i).setContactSvgButton(icon);
        this.personsPanel.get(i).getContactButton().addActionListener(this);
        
		JLabel label = new JLabel(contacts.get(i).getName() + " " + contacts.get(i).getSurname());
		label.setPreferredSize(new Dimension(90,30));
		label.setFont(new Font("Arial", Font.BOLD, 13));
		label.setBounds(65,3,150,20);
		panel.add(label);
		
		JLabel label1 = new JLabel(contacts.get(i).getPhone());
		label1.setForeground(new Color (50, 110, 140));
		label1.setPreferredSize(new Dimension(100,30));
		label1.setFont(new Font("Arial", Font.BOLD, 13));
		label1.setBounds(65, 22, 150, 20);
		panel.add(label1);
		
		filePath = "Images/empty.svg";
		SvgButton icon1 = new SvgButton(filePath);
		icon1.setBorder(new RoundedCornerBorder(15, null));
		icon1.setBackground(background);
		icon1.setMinimumSize(new Dimension(35,35));
		icon1.setBounds(240,10,25,25);
		icon1.setBorder(new MatteBorder(0,0,0,0,new Color(240,240,240)));
		icon1.setFilePath(filePath);
        panel.add(icon1);
        this.personsPanel.get(i).setDeleteSvgButton(icon1);
        this.personsPanel.get(i).getDeleteButton().addActionListener(this);;
		return panel;
	}
	
	/**
	 * Initializes the panel used for confirming or canceling the deletion of selected contacts.
	 * This method creates a new panel with buttons for executing the delete action and undoing the selection.
	 * The delete confirmation button ('doneButton') allows the user to confirm the deletion of all selected contacts.
	 * The undo button ('undoButton') cancels the deletion process and resets any selections made by the user.
	 */
	private void createDeletePanel() {
		this.deletePanel = new JPanel(null);
		deletePanel.setBackground(background);
		deletePanel.setBounds(this.contactsPanel.getX(), this.contactsPanel.getY() + contactsPanel.getHeight(), contactsPanel.getWidth(), 30);
		deletePanel.setLayout(null);
		
		
		String filePath = "Images/trash.svg";
		doneButton = new SvgButton(filePath);
		doneButton.setBounds(175,0,25,25);
		doneButton.setBorder(new MatteBorder(0,0,0,0,background));
		doneButton.setFocusable(false);
		doneButton.setBackground(background);
		doneButton.addActionListener(this);
		deletePanel.add(doneButton);
		
		filePath = "Images/undo2.svg";
		undoButton = new SvgButton(filePath);
		undoButton.setBorder(new MatteBorder(0,0,0,0,background));
		undoButton.setBackground(background);
		undoButton.setBounds(95,0,25,25);
		undoButton.setFocusable(false);
		undoButton.addActionListener(this);
		deletePanel.add(undoButton);
		add(deletePanel);
	}
	
	/**
	 * Sets up and displays the user profile panel. This panel shows the user's current information
	 * and provides options for editing or deleting the user account based on the specified mode.
	 * In edit mode, input fields become editable, allowing the user to update their information.
	 * Buttons for saving changes or canceling the edit process are also displayed.
	 *
	 * @param flag If true, the panel is initialized in edit mode with editable fields and appropriate action buttons.
	 *             If false, the panel displays the user's information in a read-only format.
	 */
	private void createUserPanel(boolean flag) {
		this.userPanel = new RoundedPanel(25);
		userPanel.setBackground(Color.white);
		userPanel.setBounds(15,10,300,390);
		userPanel.setLayout(null);
		
		ShadowLabel title = new ShadowLabel("User Card");
		title.setForeground(steelBlue);
		title.setBackground(Color.white);
		title.setBorder(null);
		title.setFont(new Font("Arial", Font.BOLD, 22));
		title.setBounds(95,5,200,30);
		userPanel.add(title);
		
		String filePath = "Images/arrow.svg";
		this.uBackButton = new SvgButton(filePath);
		uBackButton.setBackground(Color.white);
		uBackButton.setBorder(null);
		uBackButton.setBounds(8,3,35,35);
		uBackButton.addActionListener(this);
		userPanel.add(uBackButton);
		
		if (!flag) {
			RoundedPanel p = new RoundedPanel(25);
	        p.setBackground(new Color(0,0,0,0));
	        p.setBorder(new RoundedCornerBorder(25, steelBlue));
	        p.setBounds(15, 140, 120, 34);
	        p.setLayout(null);
	        p.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (SwingUtilities.isLeftMouseButton(e)) {
	                    uEditButton.doClick();
	                }
	            }
	        });
	        userPanel.add(p);
	        
			RoundedPanel panel = new RoundedPanel(25);
	        panel.setBackground(Color.white);
	        panel.setBorder(new RoundedCornerBorder(25, steelBlue));
	        panel.setBounds(15, 140, 120, 34);
	        panel.setLayout(null);
	        
	        JTextField t = RoundedTextField.createRoundedTextField("Edit credentials", null);
	        t.setBackground(Color.white);
	        t.setForeground(steelBlue);
	        t.setOpaque(false);
	        t.setEditable(false);
	        t.setFocusable(false);
	        t.setBounds(25,1,85,30);
	        t.setBorder(null);
	        panel.add(t);
	        
	        filePath = "Images/edit1.svg";
	        uEditButton = new SvgButton(filePath);
	        uEditButton.setBorder(new RoundedCornerBorder(15, null));
	        uEditButton.setBackground(Color.white);
	        uEditButton.setBounds(5, 9,15,15);
	        uEditButton.setBorder(new MatteBorder(0,0,0,0,background));
	        uEditButton.addActionListener(this);
	        panel.add(uEditButton);
	        
	        userPanel.add(panel);
	        
	        RoundedPanel p1 = new RoundedPanel(25);
	        p1.setBackground(new Color(0,0,0,0));
	        p1.setBorder(new RoundedCornerBorder(25, steelBlue));
	        p1.setBounds(15, 180, 120, 34);
	        p1.setLayout(null);
	        p1.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (SwingUtilities.isLeftMouseButton(e)) {
	                    uEditPasswordButton.doClick();
	                }
	            }
	        });
	        userPanel.add(p1);
	        
	        RoundedPanel panel1 = new RoundedPanel(25);
	        panel1.setBackground(Color.white);
	        panel1.setBorder(new RoundedCornerBorder(25, steelBlue));
	        panel1.setBounds(15, 180, 120, 34);
	        panel1.setLayout(null);
	        
	        JTextField t1 = RoundedTextField.createRoundedTextField("Edit password", null);
	        t1.setBackground(Color.white);
	        t1.setEditable(false);
	        t1.setForeground(steelBlue);
	        t1.setBounds(25,1,85,30);
	        t1.setFocusable(false);
	        t1.setBorder(null);
	        panel1.add(t1);
	        
	        filePath = "Images/password.svg";
	        uEditPasswordButton = new SvgButton(filePath);
	        uEditPasswordButton.setBorder(new RoundedCornerBorder(15, null));
	        uEditPasswordButton.setBackground(Color.white);
	        uEditPasswordButton.setBounds(5, 9,15,15);
	        uEditPasswordButton.setBorder(new MatteBorder(0,0,0,0,background));
	        uEditPasswordButton.addActionListener(this);
	        panel1.add(uEditPasswordButton);
	        
	        userPanel.add(panel1);
	        
	        RoundedPanel p2 = new RoundedPanel(25);
	        p2.setBackground(new Color(0,0,0,0));
	        p2.setBorder(new RoundedCornerBorder(25, new Color(227,49,49)));
	        p2.setBounds(150, 140, 120, 34);
	        p2.setLayout(null);
	        p2.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (SwingUtilities.isLeftMouseButton(e)) {
	                	uEliminate.doClick();
	                }
	            }
	        });
	        userPanel.add(p2);
	        
	        RoundedPanel panel2 = new RoundedPanel(25);
	        panel2.setBackground(Color.white);
	        panel2.setBorder(new RoundedCornerBorder(25, new Color(227,49,49)));
	        panel2.setBounds(150, 140, 120, 34);
	        panel2.setLayout(null);

	        JTextField t2 = RoundedTextField.createRoundedTextField("Delete account", null);
	        t2.setBackground(Color.white);
	        t2.setEditable(false);
	        t2.setForeground(new Color(227,49,49));
	        t2.setBounds(25,1,85,30);
	        t2.setFocusable(false);
	        t2.setBorder(null);
	        panel2.add(t2);
	        
	        filePath = "Images/ElUser.svg";
	        uEliminate = new SvgButton(filePath);
	        uEliminate.setBorder(new RoundedCornerBorder(15, null));
	        uEliminate.setBackground(Color.white);
	        uEliminate.setBounds(5, 9,15,15);
	        uEliminate.setBorder(new MatteBorder(0,0,0,0,background));
	        uEliminate.addActionListener(this);
	        panel2.add(uEliminate);
	        
	        userPanel.add(panel2);
	        
	        RoundedPanel p3 = new RoundedPanel(25);
	        p3.setBackground(new Color(0,0,0,0));
	        p3.setBorder(new RoundedCornerBorder(25, steelBlue));
	        p3.setBounds(150, 180, 120, 34);
	        p3.setLayout(null);
	        p3.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (SwingUtilities.isLeftMouseButton(e)) {
	                	logout.doClick();
	                }
	            }
	        });
	        userPanel.add(p3);
	        
	        RoundedPanel panel3 = new RoundedPanel(25);
	        panel3.setBackground(Color.white);
	        panel3.setBorder(new RoundedCornerBorder(25, steelBlue));
	        panel3.setBounds(150, 180, 120, 34);
	        panel3.setLayout(null);

	        JTextField t3 = RoundedTextField.createRoundedTextField("Log Out", null);
	        t3.setBackground(Color.white);
	        t3.setEditable(false);
	        t3.setForeground(steelBlue);
	        t3.setBounds(45,1,65,30);
	        t3.setFocusable(false);
	        t3.setBorder(null);
	        panel3.add(t3);
	        
	        filePath = "Images/logout.svg";
	        logout = new SvgButton(filePath);
	        logout.setBorder(new RoundedCornerBorder(15, null));
	        logout.setBackground(Color.white);
	        logout.setBounds(5, 9,15,15);
	        logout.setBorder(new MatteBorder(0,0,0,0,background));
	        logout.addActionListener(this);
	        panel3.add(logout);
	        
	        userPanel.add(panel3);
		}
		
		filePath = "Images/user.svg";
		SvgIconPanel card = new SvgIconPanel(filePath);
		card.setBackground(Color.white);
		card.setBounds(5,30,110,110);
		userPanel.add(card);
		
		ShadowLabel titleName = new ShadowLabel(user.getUsername());
		titleName.setBackground(Color.white);
		titleName.setForeground(steelBlue);
		titleName.setFont(new Font("Arial", Font.BOLD, 22));
		titleName.setBounds(130,70,150,30);
		userPanel.add(titleName);
		
		RoundedPanel whitePanel = new RoundedPanel(15);
		if (!flag) {
			whitePanel.setBounds(25,230,250,115);	
		}
		else {
			whitePanel.setBounds(25,170,250,115);	
		}
		whitePanel.setBackground(Color.white);
		whitePanel.setBorder(new RoundedCornerBorder(15, steelBlue));
		whitePanel.setLayout(null);
		
		uName = new JTextField(user.getName());
		uName.setEditable(flag);
		uName.setFont(pFont);
		uName.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		uName.setBackground(Color.white);
		uName.setBounds(25,10,200,25);
		whitePanel.add(uName);
		
		uSurname = new JTextField(user.getSurname());
		uSurname.setEditable(flag);
		uSurname.setFont(pFont);
		uSurname.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		uSurname.setBackground(Color.white);
		uSurname.setBounds(25,45,200,25);
		whitePanel.add(uSurname);
		
		uUsername = new JTextField(user.getUsername());
		uUsername.setEditable(flag);
		uUsername.setFont(pFont);
		uUsername.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		uUsername.setBackground(Color.white);
		uUsername.setBounds(25,80,200,25);
		whitePanel.add(uUsername);
		
		if (flag) {
			uName.setForeground(Color.LIGHT_GRAY.darker());
			uSurname.setForeground(Color.LIGHT_GRAY.darker());
			uUsername.setForeground(Color.LIGHT_GRAY.darker());
			
			filePath = "Images/confirm.svg";
			this.uConfirmEdit = new SvgButton(filePath);
			uConfirmEdit.setBackground(Color.white);
			uConfirmEdit.setBorder(null);
			uConfirmEdit.setBounds(180,305,30,30);
			uConfirmEdit.addActionListener(this);
			userPanel.add(uConfirmEdit);
			
			filePath = "Images/undo2.svg";
			this.uCancelEdit = new SvgButton(filePath);
			uCancelEdit.setBackground(Color.white);
			uCancelEdit.setBounds(90,305,30,30);
			uCancelEdit.setBorder(null);
			uCancelEdit.addActionListener(this);
			userPanel.add(uCancelEdit);
		}
		userPanel.add(whitePanel);
		add(userPanel);
	}
	
	/**
	 * Creates and displays a detailed view of a selected contact's information in a dedicated panel.
	 * This panel includes the contact's name, surname, address, phone number, and age. If the 'flag'
	 * parameter is true, the panel is set to edit mode, allowing the user to modify the contact's information.
	 * Edit and delete buttons are provided to save changes or remove the contact.
	 *
	 * @param i The index of the selected contact in the contacts list to display detailed information for.
	 * @param flag When true, the panel allows editing of the contact's information. Otherwise, it displays the information in a read-only format.
	 */
	private void createContactInfoPanel(int i, boolean flag) {
		this.personPanel = new RoundedPanel(25);
		personPanel.setBackground(Color.white);
		personPanel.setBounds(15,10,300,390);
		personPanel.setLayout(null);
		
		ShadowLabel title = new ShadowLabel("Contact Card");
		title.setForeground(steelBlue);
		title.setBackground(Color.white);
		title.setBorder(new MatteBorder(0,0,0,0,steelBlue));
		title.setFont(new Font("Arial", Font.BOLD, 22));
		title.setBounds(77,5,200,30);
		personPanel.add(title);
		
		String filePath = "Images/arrow.svg";
		this.pBackButton = new SvgButton(filePath);
		pBackButton.setBackground(Color.white);
		pBackButton.setBorder(null);
		pBackButton.setBounds(8,3,35,35);
		pBackButton.addActionListener(this);
		personPanel.add(pBackButton);
		
		if (!flag) {
			RoundedPanel p = new RoundedPanel(25);
	        p.setBackground(new Color(0,0,0,0));
	        p.setBorder(new RoundedCornerBorder(25, steelBlue));
	        p.setBounds(15, 130, 120, 34);
	        p.setLayout(null);
	        p.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (SwingUtilities.isLeftMouseButton(e)) {
	                    pEditButton.doClick();
	                }
	            }
	        });
	        personPanel.add(p);
	        
			RoundedPanel panel = new RoundedPanel(25);
	        panel.setBackground(Color.white);
	        panel.setBorder(new RoundedCornerBorder(25, steelBlue));
	        panel.setBounds(15, 130, 120, 34);
	        panel.setLayout(null);
	        
	        JTextField t = RoundedTextField.createRoundedTextField("Edit credentials", null);
	        t.setBackground(Color.white);
	        t.setForeground(steelBlue);
	        t.setOpaque(false);
	        t.setEditable(false);
	        t.setFocusable(false);
	        t.setBounds(25,1,85,30);
	        t.setBorder(null);
	        panel.add(t);
	        
	        filePath = "Images/edit1.svg";
	        pEditButton = new SvgButton(filePath);
	        pEditButton.setBorder(new RoundedCornerBorder(15, null));
	        pEditButton.setBackground(Color.white);
	        pEditButton.setBounds(5, 9,15,15);
	        pEditButton.setBorder(new MatteBorder(0,0,0,0,background));
	        pEditButton.addActionListener(this);
	        panel.add(pEditButton);
	        
	        personPanel.add(panel);
	        
	        RoundedPanel p1 = new RoundedPanel(25);
	        p1.setBackground(new Color(0,0,0,0));
	        p1.setBorder(new RoundedCornerBorder(25, new Color(227,49,49)));
	        p1.setBounds(160, 130, 120, 34);
	        p1.setLayout(null);
	        p1.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                if (SwingUtilities.isLeftMouseButton(e)) {
	                    deletePerson.doClick();
	                }
	            }
	        });
	        personPanel.add(p1);
	        
	        RoundedPanel panel1 = new RoundedPanel(25);
	        panel1.setBackground(Color.white);
	        panel1.setBorder(null);
	        panel1.setBounds(160, 130, 120, 34);
	        panel1.setLayout(null);
	        
	        JTextField t1 = RoundedTextField.createRoundedTextField("Delete contact", null);
	        t1.setBackground(Color.white);
	        t1.setEditable(false);
	        t1.setForeground(new Color(227,49,49));
	        t1.setBounds(25,3,85,26);
	        t1.setFocusable(false);
	        t1.setBorder(null);
	        panel1.add(t1);
	        
	        filePath = "Images/ElUser.svg";
	        deletePerson = new SvgButton(filePath);
	        deletePerson.setBorder(new RoundedCornerBorder(15, null));
	        deletePerson.setBackground(Color.white);
	        deletePerson.setBounds(5, 9,15,15);
	        deletePerson.setBorder(new MatteBorder(0,0,0,0,background));
	        deletePerson.addActionListener(this);
	        panel1.add(deletePerson);
	        
	        personPanel.add(panel1);
		}
		
		filePath = "Images/contactCard.svg";
		SvgIconPanel card = new SvgIconPanel(filePath);
		card.setBackground(Color.white);
		card.setBounds(10,20,120,120);
		personPanel.add(card);
		
		ShadowLabel titleName = new ShadowLabel(this.personsPanel.get(i).getPerson().getName());
		titleName.setBackground(Color.white);
		titleName.setForeground(steelBlue);
		titleName.setFont(new Font("Arial", Font.BOLD, 22));
		titleName.setBounds(140,55,150,30);
		personPanel.add(titleName);
		
		ShadowLabel titleSurname = new ShadowLabel(this.personsPanel.get(i).getPerson().getSurname());
		titleSurname.setBackground(Color.white);
		titleSurname.setForeground(steelBlue);
		titleSurname.setFont(new Font("Arial", Font.BOLD, 22));
		titleSurname.setBounds(140,95,150,30);
		personPanel.add(titleSurname);
		
		RoundedPanel whitePanel = new RoundedPanel(15);
		if (!flag) {
			whitePanel.setBounds(25,170,250,185);	
		}
		else {
			whitePanel.setBounds(25,150,250,185);	
		}
		whitePanel.setBackground(Color.white);
		whitePanel.setBorder(new RoundedCornerBorder(15, steelBlue));
		whitePanel.setLayout(null);
		
		pName = new JTextField(this.personsPanel.get(i).getPerson().getName());
		pName.setEditable(flag);
		pName.setFont(pFont);
		pName.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		pName.setBackground(Color.white);
		pName.setBounds(25,10,200,25);
		whitePanel.add(pName);
		
		pSurname = new JTextField(this.personsPanel.get(i).getPerson().getSurname());
		pSurname.setEditable(flag);
		pSurname.setFont(pFont);
		pSurname.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		pSurname.setBackground(Color.white);
		pSurname.setBounds(25,45,200,25);
		whitePanel.add(pSurname);
		
		pAddress = new JTextField(this.personsPanel.get(i).getPerson().getAddress());
		pAddress.setEditable(flag);
		pAddress.setFont(pFont);
		pAddress.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		pAddress.setBackground(Color.white);
		pAddress.setBounds(25,80,200,25);
		whitePanel.add(pAddress);
		
		pPhone = new JTextField(this.personsPanel.get(i).getPerson().getPhone());
		pPhone.setEditable(flag);
		pPhone.setFont(pFont);
		pPhone.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		pPhone.setBackground(Color.white);
		pPhone.setBounds(25,115,200,25);
		whitePanel.add(pPhone);
		
		pAge = new JTextField(Integer.toString(this.personsPanel.get(i).getPerson().getAge()));
		pAge.setEditable(flag);
		pAge.setFont(pFont);
		pAge.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		pAge.setBackground(Color.white);
		pAge.setBounds(25,150,200,25);
		whitePanel.add(pAge);
		
		if (flag) {
			pName.setForeground(Color.LIGHT_GRAY.darker());
			pSurname.setForeground(Color.LIGHT_GRAY.darker());
			pAddress.setForeground(Color.LIGHT_GRAY.darker());
			pPhone.setForeground(Color.LIGHT_GRAY.darker());
			pAge.setForeground(Color.LIGHT_GRAY.darker());
			
			filePath = "Images/confirm.svg";
			this.pConfirmEdit = new SvgButton(filePath);
			pConfirmEdit.setBackground(Color.white);
			pConfirmEdit.setBorder(null);
			pConfirmEdit.setBounds(180,345,30,30);
			pConfirmEdit.addActionListener(this);
			personPanel.add(pConfirmEdit);
			
			filePath = "Images/undo2.svg";
			this.pCancelEdit = new SvgButton(filePath);
			pCancelEdit.setBackground(Color.white);
			pCancelEdit.setBounds(90,345,30,30);
			pCancelEdit.setBorder(null);
			pCancelEdit.addActionListener(this);
			personPanel.add(pCancelEdit);
		}
		personPanel.add(whitePanel);
		add(personPanel);
	}
	
	/**
	 * Initializes a panel for adding a new contact to the phonebook. This panel includes text fields
	 * for inputting the new contact's name, surname, address, phone number, and age. Additionally, it provides
	 * 'Confirm' and 'Cancel' buttons for submitting the new contact information or aborting the add operation.
	 */
	private void createAddPanel() {
		this.createTopPanel(true);
		add(topPanel);
		
		this.addPanel = new RoundedPanel(25);
		addPanel.setBounds(20,90,295,280);
		addPanel.setBackground(Color.white);
		addPanel.setBackground(Color.white);
		addPanel.setBorder(new RoundedCornerBorder(15, steelBlue));
		addPanel.setLayout(null);
		
		pName = RoundedTextField.createRoundedTextField("Name", null);
		pName.setFont(pFont);
		pName.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		pName.setBackground(Color.white);
		pName.setBounds(15,10,260,25);
		addPanel.add(pName);
		
		pSurname = RoundedTextField.createRoundedTextField("Surname", null);
		pSurname.setFont(pFont);
		pSurname.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		pSurname.setBackground(Color.white);
		pSurname.setBounds(15,45,260,25);
		addPanel.add(pSurname);
		
		pAddress = RoundedTextField.createRoundedTextField("Address", null);
		pAddress.setFont(pFont);
		pAddress.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		pAddress.setBackground(Color.white);
		pAddress.setBounds(15,80,260,25);
		addPanel.add(pAddress);
		
		pPhone = RoundedTextField.createRoundedTextField("Phone", null);
		pPhone.setFont(pFont);
		pPhone.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		pPhone.setBackground(Color.white);
		pPhone.setBounds(15,115,260,25);
		addPanel.add(pPhone);
		
		pAge = RoundedTextField.createRoundedTextField("Age", null);
		pAge.setFont(pFont);
		pAge.setBorder(new MatteBorder(0,0,1,0,steelBlue));
		pAge.setBackground(Color.white);
		pAge.setBounds(17,150,260,25);
		addPanel.add(pAge);
		
		RoundedPanel p = new RoundedPanel(25);
        p.setBackground(new Color(0,0,0,0));
        p.setBorder(new RoundedCornerBorder(25, steelBlue));
        p.setBounds(15, 210, 100, 34);
        p.setLayout(null);
        p.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    addCancelButton.doClick();
                }
            }
        });
        addPanel.add(p);
        
		RoundedPanel panel = new RoundedPanel(25);
        panel.setBackground(Color.white);
        panel.setBorder(new RoundedCornerBorder(25, steelBlue));
        panel.setBounds(15, 210, 100, 34);
        panel.setLayout(null);
        
        JLabel t = new JLabel("Cancel");
        t.setBackground(Color.black);
        t.setForeground(steelBlue);
        t.setFocusable(false);
        t.setBounds(35,1,85,30);
        t.setBorder(null);
        panel.add(t);
        
        String filePath = "Images/undo2.svg";
        addCancelButton = new SvgButton(filePath);
        addCancelButton.setBorder(new RoundedCornerBorder(15, null));
        addCancelButton.setBackground(Color.white);
        addCancelButton.setBounds(5,3,25,25);
        addCancelButton.setBorder(null);
        addCancelButton.addActionListener(this);
        panel.add(addCancelButton);
        
        addPanel.add(panel);
        
        RoundedPanel p1 = new RoundedPanel(25);
        p1.setBackground(new Color(0,0,0,0));
        p1.setBorder(new RoundedCornerBorder(25, steelBlue));
        p1.setBounds(180, 210, 100, 34);
        p1.setLayout(null);
        p1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    addConfirmButton.doClick();
                }
            }
        });
        addPanel.add(p1);
        
        RoundedPanel panel1 = new RoundedPanel(25);
        panel1.setBackground(Color.white);
        panel1.setBorder(null);
        panel1.setBounds(180, 210, 100, 34);
        panel1.setLayout(null);
        
        JLabel t1 = new JLabel("Add contact");
        t1.setBackground(Color.white);
        t1.setForeground(steelBlue);
        t1.setBounds(25,3,85,26);
        t1.setFocusable(false);
        t1.setBorder(null);
        panel1.add(t1);
        
        filePath = "Images/addUser.svg";
        addConfirmButton = new SvgButton(filePath);
        addConfirmButton.setBorder(new RoundedCornerBorder(15, null));
        addConfirmButton.setBackground(Color.white);
        addConfirmButton.setBounds(3,6,20,20);
        addConfirmButton.setBorder(null);
        addConfirmButton.addActionListener(this);
        panel1.add(addConfirmButton);
        
        addPanel.add(panel1);
        add(addPanel);
	}
	
	/**
	 * Handles actions performed on the GUI components, such as button clicks for adding, deleting,
	 * and editing contacts, as well as navigating between different UI panels. This method routes
	 * each action to its appropriate handler based on the source of the event.
	 *
	 * @param e The action event generated by interacting with GUI components.
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == this.deleteButton) {
			removeMainComponent();
			addMainComponent(false, true, user.getContacts());
			repaint();
			createDeletePanel();
			repaint();
		}
		
		for (int i = 0; i < this.personsPanel.size(); i++) {
			if (e.getSource() == this.personsPanel.get(i).getDeleteButton()) {
				if (this.personsPanel.get(i).getDeleteButton().getFilePath().equals("Images/empty.svg")) {
					this.personsPanel.get(i).setSelected(true);
					String filePath = "Images/check.svg";
					File file = new File(filePath);
					URI svgUri = file.toURI();
					this.personsPanel.get(i).getDeleteButton().setSvgURI(svgUri);
					this.personsPanel.get(i).getDeleteButton().setFilePath(filePath);
				}
				else {
					this.personsPanel.get(i).setSelected(false);
					String filePath = "Images/empty.svg";
					File file = new File(filePath);
					URI svgUri = file.toURI();
					this.personsPanel.get(i).getDeleteButton().setSvgURI(svgUri);
					this.personsPanel.get(i).getDeleteButton().setFilePath(filePath);
				}
			}
			
			if (e.getSource() == this.personsPanel.get(i).getContactButton()) {
				removeMainComponent();
				this.createContactInfoPanel(i, false);
				selected = i;
			}
			
		}
		
		if (e.getSource() == this.undoButton) {
			this.deletePanel.setVisible(false);
			this.remove(deletePanel);
			removeMainComponent();
			addMainComponent(false, false, user.getContacts());
			repaint();
		}
		
		if (e.getSource() == this.doneButton) {
			int response = JOptionPane.showConfirmDialog(this, "Eliminate contacts?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				for (int i = 0; i < this.personsPanel.size(); i++) {
					if (this.personsPanel.get(i).getSelected() == true) {
						PhoneAction.deletePerson(this.personsPanel.get(i).getPerson(), user);			
					}
				}
				this.deletePanel.setVisible(false);
				remove(deletePanel);
				removeMainComponent();
				repaint();
				addMainComponent(false, false, user.getContacts());
				repaint();
			}
			else {
				this.deletePanel.setVisible(false);
				remove(deletePanel);
				removeMainComponent();
				repaint();
				addMainComponent(false, false, user.getContacts());
				repaint();
			}
		}
		
		if (e.getSource() == this.deletePerson) {
			int response = JOptionPane.showConfirmDialog(this, "Eliminate contact?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				PhoneAction.deletePerson(this.personsPanel.get(selected).getPerson(), user);
				selected = -1;
				personPanel.setVisible(false);
				remove(personPanel);
				removeMainComponent();
				addMainComponent(false, false, user.getContacts());
				repaint();
			}
		}
		
		if (e.getSource() == this.pEditButton) {
				this.personPanel.setVisible(false);
				remove(personPanel);
				this.createContactInfoPanel(selected, true);
				repaint();
		}
		
		if (e.getSource() == this.uEditButton) {
			this.userPanel.setVisible(false);
			remove(userPanel);
			this.createUserPanel(true);
			repaint();
		}
		
		if (e.getSource() == this.pConfirmEdit) {	
			int response = JOptionPane.showConfirmDialog(this, "Edit contact?", "Confirm edit", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				try {
					this.personsPanel.get(selected).getPerson().setName(this.pName.getText());
					this.personsPanel.get(selected).getPerson().setSurname(this.pSurname.getText());
					this.personsPanel.get(selected).getPerson().setAddress(this.pAddress.getText());
					this.personsPanel.get(selected).getPerson().setPhone(this.pPhone.getText());
					this.personsPanel.get(selected).getPerson().setAge(Integer.parseInt(this.pAge.getText()));
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
					personPanel.setVisible(false);
					remove(personPanel);
					this.createContactInfoPanel(selected, true);
					repaint();
				}
				PhoneAction.editPerson(user, this.personsPanel.get(selected).getPerson());
				personPanel.setVisible(false);
				remove(personPanel);
				this.createContactInfoPanel(selected, false);
				repaint();
			}
			else {
				personPanel.setVisible(false);
				remove(personPanel);
				repaint();
				this.createContactInfoPanel(selected, false);
				repaint();
			}	
		}
		
		if (e.getSource() == this.uConfirmEdit) {
			int response = JOptionPane.showConfirmDialog(this, "Edit user credentials?", "Confirm edit", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				try {
					user.setName(uName.getText());
					user.setSurname(uSurname.getText());
					if (!uUsername.getText().equals(user.getUsername())) {
						user.setUsername(uUsername.getText());
						PhoneAction.checkUsername(uUsername.getText());
						PhoneAction.editUser(user);
					}
					userPanel.setVisible(false);
					remove(userPanel);
					repaint();
					this.createUserPanel(false);
					repaint();
				}
				catch(Exception e1) {
					JOptionPane.showMessageDialog(this, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
				}
			}
			else {
				userPanel.setVisible(false);
				remove(userPanel);
				repaint();
				this.createUserPanel(false);
				repaint();
			}
		}
		
		if(e.getSource() == this.pCancelEdit) {
			this.personPanel.setVisible(false);
			remove(personPanel);
			repaint();
			this.createContactInfoPanel(selected, false);
			repaint();
		}	
		
		if (e.getSource() == this.uCancelEdit) {
				this.userPanel.setVisible(false);
				remove(userPanel);
				repaint();
				this.createUserPanel(false);
				repaint();
		}
		
		
		if (e.getSource() == this.pBackButton) {
			selected = -1;
			this.personPanel.setVisible(false);
			remove(personPanel);
			repaint();
			addMainComponent(false, false, user.getContacts());
			repaint();
		}
		
		if (e.getSource() == this.uBackButton) {
			this.userPanel.setVisible(false);
			remove(userPanel);
			repaint();
			addMainComponent(false, false, user.getContacts());
			repaint();
		}
		
		if (e.getSource() == this.userButton) {
			removeMainComponent();
			this.createUserPanel(false);
			repaint();
		}
		
		if (e.getSource() == this.uEditPasswordButton) {
			String OldPassword = JOptionPane.showInputDialog(this, "Enter your old password:");
			
	        if (OldPassword != null && user.checkPassword(OldPassword)) {
	            // If the old password is correct, prompt for a new password
	            String newPassword = JOptionPane.showInputDialog(this, "Enter your new password:");
	            if (newPassword != null && !newPassword.isEmpty()) {
	            	try {
	            		user.setPassword(newPassword);
		                JOptionPane.showMessageDialog(this, "Password changed successfully!");
	            	}
	                catch(Exception e1) {
	                	JOptionPane.showMessageDialog(this, e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
	                }
	            } 
	            else {
	            	JOptionPane.showMessageDialog(this, "Password must not be empty", "Error", JOptionPane.ERROR_MESSAGE);
	            }
	        } else {
	            JOptionPane.showMessageDialog(this, "Operation canceled or incorrect old password.", "Error", JOptionPane.ERROR_MESSAGE);
	        }
		}
		
		if (e.getSource() == this.uEliminate) {
			int response = JOptionPane.showConfirmDialog(this, "Eliminate account?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				PhoneAction.deleteUser(user);
				WindowsManager.switchToLoginFrame();
			}
			else {
				userPanel.setVisible(false);
				remove(userPanel);
				repaint();
				this.createUserPanel(false);
				repaint();
			}
		}
		
		if (e.getSource() == this.searchButton) {
			String s = this.searchField.getText();
			if (s.equals("Search...") || s.trim().isEmpty()) {
				removeMainComponent();
				addMainComponent(false, false, user.getContacts());
				repaint();
			}
			else {
				ArrayList<Person > contacts = PhoneAction.Search(user, s);
				if (contacts.size() == 0) {
					JOptionPane.showMessageDialog(this, "Contact not found.", "Info", JOptionPane.INFORMATION_MESSAGE);
					removeMainComponent();
					addMainComponent(false, false, user.getContacts());
					repaint();
				}
				else {
					removeMainComponent();
					addMainComponent(false, false, contacts);
					repaint();
				}

			}
		}
		if (e.getSource() == this.addButton) {
			removeMainComponent();
			createAddPanel();
            repaint();
		}
		if (e.getSource() == this.addConfirmButton) {
			try {
				String[] info = {pName.getText(), pSurname.getText(), pAddress.getText(),pPhone.getText(), pAge.getText()};
				if (info[0].equals("Name")|| info[1].equals("Surname") || info[2].equals("Address") || info[3].equals("Phone") || info[4].equals("Age")) {
					JOptionPane.showMessageDialog(this, "All fields must not be empty", "Warning", JOptionPane.WARNING_MESSAGE);
				}
				
				for (int i = 0; i < info.length; i++) {
					if (info[i].trim().isEmpty()) {
						JOptionPane.showMessageDialog(this, "All fields must not be empty", "Warning", JOptionPane.WARNING_MESSAGE);
					}
				}
				Person p = new Person(info, user);
				PhoneAction.addPerson(p, user);
				addPanel.setVisible(false);
				topPanel.setVisible(false);
				remove(addPanel);
				remove(topPanel);
				repaint();
				addMainComponent(false, false, user.getContacts());
				repaint();
			}
			catch(Exception e1) {
				JOptionPane.showMessageDialog(this, e1.getMessage(), "Warning", JOptionPane.WARNING_MESSAGE);
			}
		}
		
		if (e.getSource() == this.addCancelButton) {
			addPanel.setVisible(false);
			topPanel.setVisible(false);
			remove(addPanel);
			remove(topPanel);
			repaint();
			addMainComponent(false, false, user.getContacts());
			repaint();
		}
		
		if(e.getSource() == this.logout) {
			int response = JOptionPane.showConfirmDialog(this, "Log Out?", "Confirm deletion", JOptionPane.YES_NO_OPTION);
			if (response == JOptionPane.YES_OPTION) {
				WindowsManager.switchToLoginFrame();
			}
		}
	}
	
	public static void main(String[] args) {
		user = PhoneAction.searchUser("UserTest11");
        SwingUtilities.invokeLater(() -> {
            PhonebookFrame frame = new PhonebookFrame();
            frame.setVisible(true);
            
        });
    }
}
