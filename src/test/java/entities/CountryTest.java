package entities;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests Country class
 */
public class CountryTest {

	Continent d_continent = new Continent(1, 2);
	Country d_country = new Country(1, d_continent);

	/**
	 * Checks if country object is null.
	 */
	@Test
	public void testNull() {
		assertNotNull(d_country);
	}

	/**
	 * Checks id of country
	 */
	@Test
	public void testName() {
		assertEquals(1, d_country.getId());
	}

	/**
	 * Compares continent provided and continent stored in the game map.
	 */
	@Test
	public void testContinent() {
		assertEquals(d_continent, d_country.getContinent());
	}

	/**
	 * Checks if correct number of armies are placed
	 */
	@Test
	public void testPlaceArmies() {
		d_country.placeArmies(10);
		assertEquals(10, d_country.getNumberOfArmiesPresent());
	}

	/**
	 * Checks if correct number of armies are removed
	 */
	@Test
	public void testRemoveArmies() {
		d_country.placeArmies(10);
		d_country.removeArmies(10);
		assertEquals(0, d_country.getNumberOfArmiesPresent());
	}

	/**
	 * Checks if neighbors are added
	 */
	@Test
	public void testAddNeighbor() {
		Country l_neighborCountry1 = new Country(1, new Continent(1, 2));
		d_country.addNeighbor(l_neighborCountry1);
		assertEquals(1, d_country.getNeighborCountries().size());
		assertTrue(d_country.getNeighborCountries().contains(l_neighborCountry1));

		Country l_neighborCountry2 = new Country(2, new Continent(1, 2));
		d_country.addNeighbor(l_neighborCountry2);
		assertEquals(2, d_country.getNeighborCountries().size());
		assertTrue(d_country.getNeighborCountries().contains(l_neighborCountry2));
	}

	/**
	 * Checks if neighbors are removed
	 */
	@Test
	public void testRemoveNeighbor() {
		Country l_neighborCountry = new Country(1, new Continent(1, 2));
		d_country.addNeighbor(l_neighborCountry);
		d_country.removeNeighbor(l_neighborCountry);
		assertEquals(0, d_country.getNeighborCountries().size());
	}
}
