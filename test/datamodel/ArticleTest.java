package datamodel;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;

/**
 * 
 * JUnit4 test code for Article class.
 * 
 * Use of assertions, see:
 * https://junit.org/junit4/javadoc/latest/org/junit/Assert.html
 * 
 * @author sgra64
 */
@FixMethodOrder(org.junit.runners.MethodSorters.NAME_ASCENDING)
public class ArticleTest {

	/*
	 * Test fixtures - objects needed to perform the tests
	 */
	private final String aToaster_id = "SKU-868682";
	private final String aToaster_description = "Toaster";
	private final long aToaster_unitPrice = 2499;
	private final int aToaster_unitsInStore = 1200;

	private final Article aToaster = new Article(aToaster_id, aToaster_description, aToaster_unitPrice,
			aToaster_unitsInStore);



	/**
	 * JUnit4 Test Code
	 * ------------------------------------------------------------------------
	 */

	/*
	 * Test constructor, regular case.
	 */
	@Test
	public void test001_RegularConstructor() {
		Article a = new Article(aToaster_id, aToaster_description, aToaster_unitPrice, aToaster_unitsInStore);
		assertEquals(a.getId(), aToaster_id); // assert that correct id is returned
		assertSame(a.getId(), aToaster_id); // "==" - equivalent
		/*
		 * insert tests to verify that: 
		 * - description is returned as provided in the constructor 
		 * - unit price is returned as provided in the constructor 
		 * - units-in-store are returned as provided in the constructor
		 */
		assertEquals(a.getDescription(), aToaster_description); // assert that correct description is returned
		assertEquals(a.getUnitPrice(), aToaster_unitPrice); // assert that correct unit price is returned
		assertEquals(a.getUnitsInStore(), aToaster_unitsInStore); // assert that correct number of units in store is returned

	}

	/*
	 * Test constructor, special case with empty String and 0 - arguments.
	 */
	@Test
	public void test002_EmptyArgumentConstructor() {
		/*
		 * insert tests for a constructor invocation: new Article( "", "", 0, 0 ); to verify that: 
		 * - id "" is returned 
		 * - description "" is returned 
		 * - unit price 0 is returned 
		 * - units-in-store 0 is returned
		 */
		Article a = new Article("", "", 0, 0);
		assertEquals(a.getId(), "");
		assertEquals(a.getDescription(), "");
		assertEquals(a.getUnitPrice(), 0);
		assertEquals(a.getUnitsInStore(), 0);
	}

	/*
	 * Test constructor, Test special case with null and < 0 - arguments.
	 */
	@Test
	public void test003_NullArgumentConstructor() {
		/*
		 * insert tests for a constructor invocation: new Article( null, null, -1, -1 ); to verify that: 
		 * - id null is returned 
		 * - description "" is returned (null for description is not allowed) 
		 * - unit price 0 is returned (negative unit prices are not allowed) 
		 * - units-in-store 0 is returned (negative inventory is not allowed)
		 */
		Article a = new Article(null, null, -1, -1);
		assertNull(a.getId());
		assertEquals(a.getDescription(), "");
		assertEquals(a.getUnitPrice(), 0);
		assertEquals(a.getUnitsInStore(), 0);
	}

	@Test
	public void test010_SetDescription() {
		/*
		 * test method: setDescription( String descr ); to verify that: 
		 * - String description is returned by getDescription() (regular case) 
		 * - "" is returned for setDescription( "" ) (corner case) 
		 * - "" is returned for setDescription(null ) (irregular case)
		 * 
		 * Use the fixture object 'aToaster' that is created above.
		 */
		final String description = "Super Toaster Model XRC-2484698";
		aToaster.setDescription(description); // test regular case
		assertEquals(aToaster.getDescription(), description);

		aToaster.setDescription(""); // test corner case
		assertEquals(aToaster.getDescription(), "");

		aToaster.setDescription(null); // test irregular case
		assertEquals(aToaster.getDescription(), "");
		assertNotNull(aToaster.getDescription());

	}

	@Test
	public void test011_SetUnitPrice() {
		/*
		 * test method: setUnitPrice( long price ); to verify that: 
		 * - price = 100L is returned by getUnitPrice() (regular case) 
		 * - 0 is returned for setUnitPrice( 0 ) (corner case) 
		 * - 0 is returned for setUnitPrice( Long.MAX_VALUE ) (corner case) 
		 * - 0 is returned for setUnitPrice( -1 ) (irregular case) 
		 * - 0 is returned for setUnitPrice( Long.MIN_VALUE ) (irregular case)
		 * 
		 * Use the fixture object 'aToaster' that is created above.
		 */
		final long price = 100L;
		aToaster.setUnitPrice( price ); // regular case
		assertEquals(aToaster.getUnitPrice(), price);
		assertSame(aToaster.getUnitPrice(), price);	// "==" equals
		
		aToaster.setUnitPrice(0); 	// corner case 1
		assertEquals(aToaster.getUnitPrice(), 0);
		
		aToaster.setUnitPrice(Long.MAX_VALUE); // corner case 2
		assertEquals(aToaster.getUnitPrice(), 0);
		
		aToaster.setUnitPrice(-1); // irregular case 1
		assertEquals(aToaster.getUnitPrice(), 0);
		
		aToaster.setUnitPrice(Long.MIN_VALUE); // irregular case 2
		assertEquals(aToaster.getUnitPrice(), 0);

	}

	@Test
	public void test012_SetUnitsInStore() {
		/*
		 * test method: setUnitsInStore( int number ); to verify that: 
		 * - units = 100 is returned by getUnitsInStore() (regular case) 
		 * - 0 is returned for setUnitsInStore( 0 ) (corner case) 
		 * - 0 is returned for setUnitsInStore( Integer.MAX_VALUE ) (corner case) 
		 * - 0 is returned for setUnitsInStore( -1 ) (irregular case) 
		 * - 0 is returned for setUnitsInStore( Integer.MIN_VALUE ) (irregular case)
		 * 
		 * Use the fixture object 'aToaster' that is created above.
		 */
		final int units = 100;
		aToaster.setUnitsInStore( units ); // regular case
		assertEquals(aToaster.getUnitsInStore(), units);
		assertSame(aToaster.getUnitsInStore(), units); // "==" equals
		
		aToaster.setUnitsInStore( 0 ); // corner case 1
		assertEquals(aToaster.getUnitsInStore(), 0);
		
		aToaster.setUnitsInStore( Integer.MAX_VALUE ); // corner case 2
		assertEquals(aToaster.getUnitsInStore(), 0);
		
		aToaster.setUnitsInStore( -1 ); // irregular case 1
		assertEquals(aToaster.getUnitsInStore(), 0);
		
		aToaster.setUnitsInStore( Integer.MIN_VALUE ); // irregular case 2
		assertEquals(aToaster.getUnitsInStore(), 0);

	}


}