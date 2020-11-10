package datamodel;

public class Article {
	
	private String id;
	private String description;
	private long unitPrice;
	private int unitsInStore;
	
	/*
	 *  Instanzen der Klasse sollen nur durch die DataFactory erzeugt werden können, 
	 *  also ist der Konstruktor protected
	 */
	protected Article(String id, String descr, long price, int units) {
		this.id = id;
		this.description = descr;
		this.unitPrice = price;
		this.unitsInStore = units;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String descr) {
		this.description = descr;
	}
	
	public long getUnitPrice() {
		return this.unitPrice;
	}
	
	public void setUnitPrice(long price) {
		this.unitPrice = price;
	}
	
	public int getUnitsInStore() {
		return this.unitsInStore;
	}
	
	public void setUnitsInStore(int number) {
		this.unitsInStore = number;
	}

}
