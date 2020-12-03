package datamodel;

import java.util.ArrayList;
import java.util.Date;

public class Order {
	
	private final long id;
	private final Date date;
	private final Customer customer;
	private final ArrayList<OrderItem> items = new ArrayList<OrderItem>();	
	
	/*
	 *  Instanzen der Klasse sollen nur durch die DataFactory erzeugt werden können, 
	 *  also ist der Konstruktor protected
	 */
	protected Order (long id, Date date, Customer customer) {
		this.id = id;	
		if (date == null) {
			this.date = new Date();	// Date erzeugen, wenn keines übergeben wurde
		} else {
		this.date = date;
		}
		this.customer = customer;	// customer can be null
	}
	
	public long getId(){
		return this.id;
	}
	
	public Date getDate() {
		return this.date;
	}
	
	public Customer getCustomer() {
		return this.customer;
	}
	
	public Iterable<OrderItem> getItems() { 
		return this.items;
	}
	
	public int count() {
		int number = this.items.size();
		return number;
	}
	
	public Order addItem(OrderItem item) {
		if (item == null) {	// wenn null übergeben wird: ignorieren
			return this;
		}
		for ( OrderItem oi : this.items ) {	// falls das OrderItem schon in der Order enthalten ist: auch ignorieren
			if ( oi.equals(item)) {
				return this;
			}
		}
		this.items.add(item);
		return this;
	}
	
	public Order removeItem(OrderItem item) {
		this.items.remove(item);
		return this;
	}
	
	public Order clearItems() {
		this.items.clear();
		return this;
	}

}
