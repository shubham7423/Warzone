package entities;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for Continent
 * @author shubhampatel
 *
 */
public class ContinentTest {

	Continent d_continent = new Continent(1, 3);
	
	/**
	 * Check if continent object is not null
	 */
	@Test
	public void checkNull() {
		assertNotNull(d_continent);
	}
	
	/**
	 * Check name of continent
	 */
	@Test
	public void nameTest() {
		assertEquals(1, d_continent.getId());
	}
	
	/**
	 * Check control value of continent
	 */
	@Test
	public void controlValueTest() {
		assertEquals(3, d_continent.getControlValue());
	}

	/**
	 * Check if countries are added
	 */
	@Test
	public void addContriesTest() {
		Country l_Country =  new Country(1, d_continent);
		d_continent.addCountry(l_Country);
		assertEquals(1, d_continent.getCountriesSet().size());
		assertTrue(d_continent.getCountriesSet().contains(l_Country));
	}
	
	/**
	 * Check if countries are removed
	 */
	@Test
	public void removeContriesTest() {
		Country l_Country =  new Country(1, d_continent);
		d_continent.addCountry(l_Country);
		d_continent.removeCountry(l_Country);
		assertEquals(0, d_continent.getCountriesSet().size());
		assertFalse(d_continent.getCountriesSet().contains(l_Country));
	}
}
