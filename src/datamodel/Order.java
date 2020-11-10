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
		this.date = date;
		this.customer = customer;
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
	
	public Iterable<OrderItem> getItems() { 	//TODO
		return this.items;
	}
	
	public int count() {	//TODO
		int number = 0;
		return number;
	}
	
	public Order addItem(OrderItem item) {
		this.items.add(item);
		return this;
	}
	
	public Order removeItem(OrderItem item) {	//TODO
		this.items.remove(item);
		return this;
	}
	
	public Order clearItems() {
		this.items.clear();
		return this;
	}

}
