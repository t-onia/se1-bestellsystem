package datamodel;

public class Article {

	private String id;
	private String description;
	private long unitPrice;
	private int unitsInStore;

	/*
	 * Instanzen der Klasse sollen nur durch die DataFactory erzeugt werden können,
	 * also ist der Konstruktor protected
	 */
	protected Article(String id, String descr, long price, int units) {
		this.id = id;
		setDescription(descr);
		setUnitPrice(price);
		setUnitsInStore(units);
	}

	public String getId() {
		return this.id;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String descr) {
		if (descr == null) { // Irregular Case: Beschreibung darf nicht null sein
			this.description = "";
		} else {
			this.description = descr;
		}
	}

	public long getUnitPrice() {
		return this.unitPrice;
	}

	public void setUnitPrice(long price) {
		if ((price < 0) || (price == Long.MAX_VALUE) || (price == Long.MIN_VALUE)) { // Preis darf nicht negativ oder Long-Grenzwert sein
			this.unitPrice = 0;
		} else {
			this.unitPrice = price;
		}
	}

	public int getUnitsInStore() {
		return this.unitsInStore;
	}

	public void setUnitsInStore(int number) {
		if ((number < 0) || (number == Integer.MAX_VALUE) || (number == Integer.MIN_VALUE)) { // Anzahl darf nicht negativ sein oder Integer-Grenzwert (MIN/MAX) sein
			this.unitsInStore = 0;
		} else {
			this.unitsInStore = number;
		}
	}

}
