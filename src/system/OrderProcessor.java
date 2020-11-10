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

	@Override
	public long vat(long grossValue) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public long vat(long grossValue, int rateIndex) {
		// TODO Auto-generated method stub
		return 0;
	}

}
