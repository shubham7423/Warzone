package entities;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for GameMap
 * 
 *
 */
public class GameMapTest {

	final GameMap d_map = new GameMap();
	
	/**
	 * Test continent add
	 */
	@Test
	public void testAddContinent() {
		d_map.addContinent("Asia", 2);
		assertTrue(d_map.getContinents().keySet().contains("Asia"));
		assertEquals(2, d_map.getContinents().get("Asia").getControlValue());
	}

	/**
	 * Test continent remove
	 */
	@Test
	public void testRemoveContinent() {
		d_map.addContinent("Asia", 2);
		d_map.addContinent("Africa", 3);
		d_map.addCountry("Egypt", "Africa");
		d_map.removeContinent("Africa");
		assertEquals(1, d_map.getContinents().size());
		assertFalse(d_map.getContinents().containsKey("Africa"));
	}

	/**
	 * Test country add
	 */
	@Test
	public void testAddCountry() {
		d_map.addContinent("Asia", 2);
		d_map.addCountry("India", "Asia");
		assertEquals(1, d_map.getCountries().size());
		assertTrue(d_map.getCountries().keySet().contains("India"));
	}

	/**
	 * Test country remove
	 */
	@Test
	public void testRemoveCountry() {
		d_map.addContinent("Asia", 2);
		d_map.addCountry("India", "Asia");
		d_map.addCountry("Pakistan", "Asia");
		d_map.removeCountry("Pakistan");
		assertEquals(1, d_map.getCountries().size());
		assertFalse(d_map.getCountries().keySet().contains("Pakistan"));
		
		d_map.addCountry("Pakistan", "Asia");
		d_map.addCountry("Nepal", "Asia");
		d_map.addNeighbour("Pakistan", "Nepal");
		d_map.addNeighbour("Nepal", "Pakistan");
		d_map.removeCountry("Pakistan");
		assertFalse(d_map.getCountries().get("Nepal").getNeighbourNames().contains("Pakistan"));
	}

	/**
	 * Test neighbour addition
	 */
	@Test
	public void testAddNeighbour() {
		d_map.addContinent("Asia", 2);
		d_map.addCountry("India", "Asia");
		d_map.addCountry("Pakistan", "Asia");
		String l_resultString = d_map.addNeighbour("India", "Pakistan");
		assertEquals(1, d_map.getCountries().get("India").getNeighbourCountries().size());
		assertEquals("Country \"Pakistan\" is now a neighbour of country \"India\"", l_resultString);
	}

	/**
	 * Test neighbour deletion
	 */
	@Test
	public void testRemoveNeighbour() {
		d_map.addContinent("Asia", 2);
		d_map.addCountry("India", "Asia");
		d_map.addCountry("Pakistan", "Asia");
		d_map.addNeighbour("India", "Pakistan");
		String l_resultString = d_map.removeNeighbour("India", "Pakistan");
		assertEquals(0, d_map.getCountries().get("India").getNeighbourCountries().size());
		assertEquals("Country \"Pakistan\" removed from neighbours of \"India\"", l_resultString);
	}
	
	@Test
	public void testLoadMap() {
		assertEquals(String.format("Map \"uk.map\" loaded successfully"), d_map.loadMap("uk.map"));
		assertEquals(String.format("Map \"uk123.map\" cannot be loaded"), d_map.loadMap("uk123.map"));
	}

}
