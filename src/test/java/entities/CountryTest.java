package entities;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

/**
 * 
 * Test Country class
 *
 */
public class CountryTest {

	Continent d_Continent = new Continent();
	Country d_country = new Country("India", d_Continent);

	/**
	 * Check is country object is null. 
	 */
	@Test
	public void nullTest() {
		assertNotNull(d_country);
	}
	
	/**
	 * Check name of country
	 */
	@Test
	public void nameTest() {
		assertEquals("India", d_country.getName());
	}
	
	@Test
	public void ContinentTest() {
		assertEquals(d_Continent, d_country.getContinent());
	}
	
	/**
	 * Check if correct number of armies are placed
	 */
	@Test
	public void placeArmiesTest() {
		d_country.placeArmies(10);
		assertEquals(10, d_country.getNumberOfArmiesPresent());
	}
	
	/**
	 * Check if correct number of armies are removed
	 */
	@Test
	public void removeArmiesTest() {
		d_country.placeArmies(10);
		d_country.removeArmies(10);
		assertEquals(0, d_country.getNumberOfArmiesPresent());
	}
	
	/**
	 * Check if neighbours are added
	 */
	@Test
	public void addNeighbourTest() {
		Country l_neighbourCountry_1 = new Country("Nepal", new Continent());
		d_country.addNeighbour(l_neighbourCountry_1);
		assertEquals(1, d_country.getNeighbourCountries().size());
		assertTrue(d_country.getNeighbourCountries().contains(l_neighbourCountry_1));
		
		Country l_neighbourCountry_2 = new Country("Pakistan", new Continent());
		d_country.addNeighbour(l_neighbourCountry_2);
		assertEquals(2, d_country.getNeighbourCountries().size());
		assertTrue(d_country.getNeighbourCountries().contains(l_neighbourCountry_2));
	}
	
	/**
	 * Check if neighbours are removed
	 */
	@Test
	public void removeNeighbourTest() {
		Country l_neighbourCountry = new Country("Nepal", new Continent());
		d_country.addNeighbour(l_neighbourCountry);
		d_country.removeNeighbour(l_neighbourCountry);
		assertEquals(0, d_country.getNeighbourCountries().size());
	}

}
