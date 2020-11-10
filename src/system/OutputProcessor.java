package system;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

import datamodel.Customer;
import datamodel.Order;
import datamodel.OrderItem;

final class OutputProcessor implements Components.OutputProcessor {
	/**
	 * OutputProcessor soll ebenfalls ein Singleton sein, die Instanz wird beim
	 * ersten Aufruf der getInstance() - Methode erzeugt und die Referenzen zu in
	 * Abhängigkeit stehenden Klassen abgespeichert
	 */
	private static OutputProcessor instance = null;
	private InventoryManager inventoryManager = null;
	private OrderProcessor orderProcessor = null;

	private OutputProcessor(InventoryManager inventoryManager, OrderProcessor orderProcessor) {
		this.inventoryManager = inventoryManager; // Referenzen abspeichern
		this.orderProcessor = orderProcessor;
	}

	public static OutputProcessor getInstance(InventoryManager inventoryManager, OrderProcessor orderProcessor) {
		if (instance == null) {
			instance = new OutputProcessor(inventoryManager, orderProcessor); // einmaliger Konstruktoraufruf
		}
		return instance;
	}

	// Methode, die ohne Parameter die Referenz zur Instanz liefert
	public static OutputProcessor getInstance() {
		return instance;
	}

	/*
	 * Implementierung der Methoden aus Application_1:
	 */
	@Override
	public void printOrders(List<Order> orders, boolean printVAT) {

		final int printLineWidth = 95;

		StringBuffer sbAllOrders = new StringBuffer("-------------");
		StringBuffer sbLineItem = new StringBuffer();

		// long value for sum of all orders
		long sumOrders = 000;

		for (Order order : orders) {
			sbAllOrders.append("\n");
			/* 
			 * create left side String including number and Info etc, right side String
			 * with value in EUR
			 */
			String left = "#";
			String right = "";

			// get oNumber - left side String
			left += String.valueOf(order.getId());
			left += ", ";

			/* Print customer's name:
			 * Example left String Variante A: "#5234968294, Eric's Bestellung: "
			 * Example left String Variante B: "#5234968294, Schulz-Mueller, Eric's Bestellung: "
			 */
			Customer customer = order.getCustomer();
			
			String customerNameA = splitName(customer, customer.getFirstName() + " " + customer.getLastName());	// Variante A: "Vorname Nachname"
			String customerNameB = singleName(customer);	// Variante B: "Nachname, Vorname"
			
			
			// Ausgabevariante B:
			left += customerNameB;
			left += "'s Bestellung: "; 	

			// price as long
			long oPrice = 000;

			// identify OrderItems in Order
			for (OrderItem item : order.getItems()) {
				left += String.valueOf(item.getUnitsOrdered());
				left += "x ";
				left += item.getDescription();
				left += ", ";

				// calculate price of OrderItem and add it to the oprice
				oPrice += (item.getArticle().getUnitPrice() * item.getUnitsOrdered());
			}

			// delete last two chars ", " in left side String
			left = left.substring(0, left.length() - 2);

			// calculate total price of order, save in right side String
			right = fmtPrice(oPrice, "EUR");

			// add orderprice to sum of all orderprices
			sumOrders += oPrice;

			// format outputline
			sbLineItem = fmtLine(left, right, printLineWidth);
			sbAllOrders.append(sbLineItem);
		}

		String totalPrice = pad(fmtPrice(sumOrders, "", " EUR"), 14, true);

		// append final line with totals
		// TODO:LEERZEICHEN ODER --- ?
		sbAllOrders.append("\n").append(fmtLine("-------------", "              -------------", printLineWidth))
				.append("\n").append(fmtLine("Gesamtwert aller Bestellungen:", totalPrice, printLineWidth));

		// print sbAllOrders StringBuffer with all output to System.out
		System.out.println(sbAllOrders.toString());
	}

	@Override
	public void printInventory() {
		// TODO Auto-generated method stub

	}

	@Override
	public String fmtPrice(long price, String currency) {
		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	@Override
	public String fmtPrice(long price, String currency, int width) {
		String fmtPrice = pad(fmtPrice(price, "", " " + currency), 14, true);
		return fmtPrice;
	}

	private String fmtPrice(long price, String prefix, String postfix) {
		StringBuffer fmtPriceSB = new StringBuffer();
		if (prefix != null) {
			fmtPriceSB.append(prefix);
		}

		fmtPriceSB = fmtPrice(fmtPriceSB, price);

		if (postfix != null) {
			fmtPriceSB.append(postfix);
		}
		return fmtPriceSB.toString();
	}

	private StringBuffer fmtPrice(StringBuffer sb, long price) {
		if (sb == null) {
			sb = new StringBuffer();
		}
		double dblPrice = ((double) price) / 100.0; // convert cent to Euro
		DecimalFormat df = new DecimalFormat("#,##0.00", new DecimalFormatSymbols(new Locale("de"))); // rounds double
																										// to 2 digits

		String fmtPrice = df.format(dblPrice); // convert result to String in DecimalFormat
		sb.append(fmtPrice);
		return sb;
	}

	private String pad(String str, int width, boolean rightAligned) {
		String fmtter = (rightAligned ? "%" : "%-") + width + "s";
		String padded = String.format(fmtter, str);
		return padded;
	}

	@Override
	public StringBuffer fmtLine(String leftStr, String rightStr, int width) {
		StringBuffer sb = new StringBuffer(leftStr);
		int shiftable = 0; // leading spaces before first digit
		for (int i = 1; rightStr.charAt(i) == ' ' && i < rightStr.length(); i++) {
			shiftable++;
		}
		final int tab1 = width - rightStr.length() + 1; // - ( seperator? 3 : 0 );
		int sbLen = sb.length();
		int excess = sbLen - tab1 + 1;
		int shift2 = excess - Math.max(0, excess - shiftable);
		if (shift2 > 0) {
			rightStr = rightStr.substring(shift2, rightStr.length());
			excess -= shift2;
		}
		if (excess > 0) {
			switch (excess) {
			case 1:
				sb.delete(sbLen - excess, sbLen);
				break;
			case 2:
				sb.delete(sbLen - excess - 2, sbLen);
				sb.append("..");
				break;
			default:
				sb.delete(sbLen - excess - 3, sbLen);
				sb.append("...");
				break;
			}
		}
		String strLineItem = String.format("%-" + (tab1 - 1) + "s%s", sb.toString(), rightStr);
		sb.setLength(0);
		sb.append(strLineItem);
		return sb;
	}

	/**
	* Split single‐String name to first‐ and last name and set to the customer
	* object, e.g. single‐String "Eric Meyer" is split into "Eric" and "Meyer".
	*
	* @param customer object for which first‐ and lastName are set
	* @param name single‐String name that is split into first‐ and last name
	* @return returns single‐String name extracted from customer object
	*/
	@Override
	public String splitName(Customer customer, String name) {
		if (name.split("\\w+").length > 1) {
			// Variante A: Vorname Nachname
			customer.setLastName(name.substring(name.lastIndexOf(" ") + 1));
			customer.setFirstName(name.substring(0, name.lastIndexOf(" ")));

			// Variante B: Nachname, Vorname
			if (name.contains(",") == true) {
				customer.setFirstName(name.substring(name.lastIndexOf(" ") + 1));
				customer.setLastName(name.substring(0, name.lastIndexOf(",")));
			}
		}
		return customer.getFirstName() + " " + customer.getLastName();
	}

	/**
	* Returns single‐String name obtained from first‐ and lastName attributes of
	* Customer object, e.g. "Eric", "Meyer" returns single‐String "Meyer, Eric".
	*
	* @param customer object referred to
	* @return sanitized name derived from first‐ and lastName attributes
	*/
	@Override
	public String singleName(Customer customer) {
		if (customer.getFirstName().equals("") == true) {
			splitName(customer, customer.getLastName());
		}
		String name = customer.getLastName() + ", " + customer.getFirstName();
		return name;
	}

}
