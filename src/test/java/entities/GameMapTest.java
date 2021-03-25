package entities;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Test for GameMap
 */
public class GameMapTest {

	final GameMap d_map = new GameMap();

	/**
	 * Tests continent addition
	 */
	@Test
	public void testAddContinent() {
		d_map.addContinent(1, 2);
		assertTrue(d_map.getContinents().keySet().contains(1));
		assertEquals(2, d_map.getContinents().get(1).getControlValue());
	}

	/**
	 * Tests continent removal
	 */
	@Test
	public void testRemoveContinent() {
		d_map.addContinent(1, 2);
		d_map.addContinent(2, 3);
		d_map.addCountry(1, 2);
		d_map.removeContinent(2);
		assertEquals(1, d_map.getContinents().size());
		assertFalse(d_map.getContinents().containsKey(2));
	}

	/**
	 * Tests country addition
	 */
	@Test
	public void testAddCountry() {
		d_map.addContinent(1, 2);
		d_map.addCountry(1, 1);
		assertEquals(1, d_map.getCountries().size());
		assertTrue(d_map.getCountries().keySet().contains(1));
	}

	/**
	 * Tests country removal
	 */
	@Test
	public void testRemoveCountry() {
		d_map.addContinent(1, 2);
		d_map.addCountry(1, 1);
		d_map.addCountry(2, 1);
		d_map.removeCountry(2);
		assertEquals(1, d_map.getCountries().size());
		assertFalse(d_map.getCountries().keySet().contains(2));

		d_map.addCountry(2, 1);
		d_map.addCountry(3, 1);
		d_map.addNeighbor(2, 3);
		d_map.addNeighbor(3, 2);
		d_map.removeCountry(2);
		assertFalse(d_map.getCountries().get(3).getNeighborIds().contains(2));
	}

	/**
	 * Tests neighbor addition
	 */
	@Test
	public void testAddNeighbor() {
		d_map.addContinent(1, 2);
		d_map.addCountry(1, 1);
		d_map.addCountry(2, 1);
		String l_resultString = d_map.addNeighbor(1, 2);
		assertEquals(1, d_map.getCountries().get(1).getNeighborCountries().size());
		assertEquals("Country \"2\" is now a neighbor of country \"1\"", l_resultString);
	}

	/**
	 * Tests neighbor deletion
	 */
	@Test
	public void testRemoveNeighbor() {
		d_map.addContinent(1, 2);
		d_map.addCountry(1, 1);
		d_map.addCountry(2, 1);
		d_map.addNeighbor(1, 2);
		String l_resultString = d_map.removeNeighbor(1, 2);
		assertEquals(0, d_map.getCountries().get(1).getNeighborCountries().size());
		assertEquals("Country \"2\" removed from neighbors of \"1\"", l_resultString);
	}

	/**
	 * Tests load map
	 */
	@Test
	public void testLoadMap() {
		assertEquals(String.format("Map \"uk.map\" loaded successfully"), d_map.loadMap("uk.map"));
		assertEquals(String.format("Map \"uk123.map\" cannot be loaded"), d_map.loadMap("uk123.map"));
	}
}
