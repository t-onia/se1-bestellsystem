package datamodel;

public class Customer {

	private final String id;
	private String firstName = "";
	private String lastName = "";
	private String contact = "";

	/*
	 * Instanzen der Klasse sollen nur durch die DataFactory erzeugt werden können,
	 * also ist der Konstruktor protected
	 */
	protected Customer(String id, String name, String contact) {	
			this.id = id;	// id kann null sein
			setContact(contact);
			setLastName(name);
	}

	/*
	 * getName() und setName(String name) wurden entfernt, die Namenszuweisung und
	 * -teilung wird im OutputProcessor organisiert.
	 */

	public String getId() {
		return this.id;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public void setFirstName(String firstName) {
		if (firstName == null) {	// firstName darf nicht null sein
			this.firstName = "";
		} else {
			this.firstName = firstName;
		}
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		if (lastName == null) {	// lastName darf nicht null sein
			this.lastName = "";
		} else {
			this.lastName = lastName;
		}
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		if (contact == null) {	// contact darf nicht null sein
			this.contact = "";
		} else {
			this.contact = contact;
		}
	}

}
