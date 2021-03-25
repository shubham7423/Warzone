package entities;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for Continent
 */
public class ContinentTest {
	Continent d_continent = new Continent(1, 3);

	/**
	 * Checks if continent object is not null
	 */
	@Test
	public void testCheckNull() {
		assertNotNull(d_continent);
	}

	/**
	 * Checks id of continent
	 */
	@Test
	public void testName() {
		assertEquals(1, d_continent.getId());
	}

	/**
	 * Checks control value of continent
	 */
	@Test
	public void testControlValue() {
		assertEquals(3, d_continent.getControlValue());
	}

	/**
	 * Checks if countries are added
	 */
	@Test
	public void testAddContries() {
		Country l_country = new Country(1, d_continent);
		d_continent.addCountry(l_country);
		assertEquals(1, d_continent.getCountriesSet().size());
		assertTrue(d_continent.getCountriesSet().contains(l_country));
	}

	/**
	 * Checks if countries are removed
	 */
	@Test
	public void testRemoveContries() {
		Country l_country = new Country(1, d_continent);
		d_continent.addCountry(l_country);
		d_continent.removeCountry(l_country);
		assertEquals(0, d_continent.getCountriesSet().size());
		assertFalse(d_continent.getCountriesSet().contains(l_country));
	}
}
