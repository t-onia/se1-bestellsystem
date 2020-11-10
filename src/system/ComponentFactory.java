package system;

import datamodel.RawDataFactory;

public final class ComponentFactory {

	private static ComponentFactory instance = null;
	private InventoryManager inventoryManager;
	private OrderProcessor orderProcessor;
	private OutputProcessor outputProcessor;
	private DataFactory dataFactory;
	private RawDataFactory rawDataFactory;

	/**
	 * Private constructor.
	 */

	private ComponentFactory() {
		this.inventoryManager = InventoryManager.getInstance();
		this.orderProcessor = OrderProcessor.getInstance(this.inventoryManager);
		this.outputProcessor = OutputProcessor.getInstance(this.inventoryManager, this.orderProcessor);
		RawDataFactory.RawDataFactoryIntf objectRawFactory = RawDataFactory.getInstance(this);
		this.dataFactory = new DataFactory(objectRawFactory, this.inventoryManager, this.outputProcessor);
	}

	/**
	 * Public access method to Factory singleton instance that is created when
	 * getInstance() is first invoked. Subsequent invocations return the reference
	 * to the same instance.
	 *
	 * @return Factory singleton instance
	 */
	public static ComponentFactory getInstance() {
		if (instance == null) {
			instance = new ComponentFactory();
		}
		return instance;
	}

	public Components.InventoryManager getInventoryManager() {
		return this.inventoryManager;
	}

	public Components.OrderProcessor getOrderProcessor() {
		return this.orderProcessor;
	}

	public Components.OutputProcessor getOutputProcessor() {
		return this.outputProcessor;
	}

	public Components.DataFactory getDataFactory() {
		return this.dataFactory;
	}

}
