package entities;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * Tests Country class
 *
 */
public class CountryTest {

	Continent d_Continent = new Continent(1, 2);
	Country d_country = new Country(1, d_Continent);

	/**
	 * Checks if country object is null. 
	 */
	@Test
	public void nullTest() {
		assertNotNull(d_country);
	}
	
	/**
	 * Checks id of country
	 */
	@Test
	public void nameTest() {
		assertEquals(1, d_country.getId());
	}
	
	@Test
	public void ContinentTest() {
		assertEquals(d_Continent, d_country.getContinent());
	}
	
	/**
	 * Checks if correct number of armies are placed
	 */
	@Test
	public void placeArmiesTest() {
		d_country.placeArmies(10);
		assertEquals(10, d_country.getNumberOfArmiesPresent());
	}
	
	/**
	 * Checks if correct number of armies are removed
	 */
	@Test
	public void removeArmiesTest() {
		d_country.placeArmies(10);
		d_country.removeArmies(10);
		assertEquals(0, d_country.getNumberOfArmiesPresent());
	}
	
	/**
	 * Checks if neighbours are added
	 */
	@Test
	public void addNeighbourTest() {
		Country l_neighbourCountry_1 = new Country(1, new Continent(1, 2));
		d_country.addNeighbour(l_neighbourCountry_1);
		assertEquals(1, d_country.getNeighbourCountries().size());
		assertTrue(d_country.getNeighbourCountries().contains(l_neighbourCountry_1));
		
		Country l_neighbourCountry_2 = new Country(2, new Continent(1, 2));
		d_country.addNeighbour(l_neighbourCountry_2);
		assertEquals(2, d_country.getNeighbourCountries().size());
		assertTrue(d_country.getNeighbourCountries().contains(l_neighbourCountry_2));
	}
	
	/**
	 * Checks if neighbours are removed
	 */
	@Test
	public void removeNeighbourTest() {
		Country l_neighbourCountry = new Country(1, new Continent(1, 2));
		d_country.addNeighbour(l_neighbourCountry);
		d_country.removeNeighbour(l_neighbourCountry);
		assertEquals(0, d_country.getNeighbourCountries().size());
	}

}
