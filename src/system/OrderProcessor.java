package system;

import java.util.function.Consumer;

import datamodel.Order;
import datamodel.OrderItem;

final class OrderProcessor implements Components.OrderProcessor {

	/**
	 * DataFactory soll ein Singleton sein, die Instanz wird beim ersten Aufruf der
	 * getInstance() - Methode erzeugt und die Referenz zu der in Abhängigkeit
	 * stehenden Klasse wird abgespeichert
	 */
	private static OrderProcessor instance = null;
	private InventoryManager inventoryManager = null;

	private OrderProcessor(InventoryManager inventoryManager) {
		this.inventoryManager = inventoryManager; // Referenz abspeichern
	}

	public static OrderProcessor getInstance(InventoryManager inventoryManager) {
		if (instance == null) {
			instance = new OrderProcessor(inventoryManager); // Singleton erzeugen
		}
		return instance;
	}

	// Methode, die ohne Parameter die Referenz zur Instanz liefert
	public static OrderProcessor getInstance() {
		return instance;
	}

	@Override
	public boolean accept(Order order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean accept(Order order, Consumer<Order> acceptCode, Consumer<Order> rejectCode,
			Consumer<OrderItem> rejectedOrderItemCode) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public long orderValue(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	/**
	 * Calculates and returns the included VAT (by default: 19% of grossValue) in
	 * the given value.
	 * 
	 * @param value including VAT
	 * @return calculated VAT
	 */
	@Override
	public long vat(long grossValue) {
		float f = (float) grossValue; // vat probably contains decimal points
		float vat = (f / 119 * 19);
		long l = (long) Math.round(vat); // use method to round the vat
		return l; // return rounded long-value
	}

	/**
	 * Calculates and returns the included VAT (19% or 7%) in the given value.
	 * 
	 * @param value    including VAT
	 * @param vat-rate (1: 19%, 2: 7%)
	 * @return calculated VAT
	 */
	@Override
	public long vat(long grossValue, int rateIndex) {
		float f = (float) grossValue;
		float vat = 0;
		if (rateIndex == 1) {
			vat = (f / 119 * 19);
		} else if (rateIndex == 2) {
			vat = (f / 119 * 7);
		} else {
			System.out.println("There is a problem with the VAT rate.");
		}
		long l = (long) Math.round(vat);
		return l;
	}

}
