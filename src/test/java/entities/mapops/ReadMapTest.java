package entities.mapops;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import entities.Country;
import entities.GameMap;

/**
 * Test for ReadMap
 *
 */
public class ReadMapTest {

	ReadMap d_map;
	
	/**
	 * Initialize map before each test
	 */
	@Before
	public void initialize() {
		d_map = new ReadMap(new GameMap());
	}
	
	/**
	 * Checks if map is read properly.
	 */
	@Test
	public void testReadFullMap1() {
		boolean l_result= d_map.readFullMap("India.map");
		assertTrue(l_result);
		
		Set<Integer> l_countriesRead = d_map.getCountriesIds();
		Set<Integer> l_continentsRead = d_map.getContinentIds();
		Set<Integer> l_countries = new HashSet<>();
		Set<Integer> l_continents = new HashSet<>();
		
		l_countries.add(1);
		l_countries.add(2);
		l_countries.add(3);
		l_countries.add(4);
		l_countries.add(5);
		assertTrue(l_countries.equals(l_countriesRead));
		
		l_continents.add(1);
		l_continents.add(2);
		l_continents.add(3);
		assertTrue(l_continents.equals(l_continentsRead));
		
		HashMap<Integer, Set<Integer>> l_neighbours = new HashMap<>();
		l_neighbours.put(1, new HashSet<>(Arrays.asList(2, 3)));
		l_neighbours.put(2, new HashSet<>(Arrays.asList(1, 3, 4)));
		l_neighbours.put(3, new HashSet<>(Arrays.asList(1, 2, 4, 5)));
		l_neighbours.put(4, new HashSet<>(Arrays.asList(2, 3, 5)));
		l_neighbours.put(5, new HashSet<>(Arrays.asList(3, 4)));

		GameMap l_gameMap = d_map.getGameMap();
		for(Integer l_country: l_gameMap.getCountries().keySet()) {
			assertTrue(l_neighbours.get(l_country).equals(l_gameMap.getCountries().get(l_country).getNeighbourIds()));
		}
		
		
	}
	
	/**
	 * Check if missing map file results in false
	 */
	@Test
	public void testReadFullMap2() {
		boolean l_result= d_map.readFullMap("ukk.map");
		assertFalse(l_result);
	}
}

