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
		// Spezialfall: NullArgumentConstructor, siehe CustomerTest
		if ((id == null) && (name == null) && (contact == null)) {
			this.id = null; // id ist null, die anderen Werte sind ""
			// Normalfall
		} else {
			this.id = id;
			this.contact = contact;
			this.lastName = name;
		}
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
		if (firstName == null) {
			this.firstName = "";
		} else {
			this.firstName = firstName;
		}
	}

	public String getLastName() {
		return this.lastName;
	}

	public void setLastName(String lastName) {
		if (lastName == null) {
			this.lastName = "";
		} else {
			this.lastName = lastName;
		}
	}

	public String getContact() {
		return this.contact;
	}

	public void setContact(String contact) {
		if (contact == null) {
			this.contact = "";
		} else {
			this.contact = contact;
		}
	}

}
