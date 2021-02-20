package entities;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.*;

/**
 * This class performs Unit testing on every function of the MapValidation Class
 */
public class MapValidationTest {

	GameMap d_gameMap = new GameMap();
	GameMap d_gameMap1 = new GameMap();
	
	/**
	 * This will validate "isMapConnected" function with different maps provided in resource
	 */
	@Test
	public void testIsMapConnected() {
		
		MapValidation l_mapValidation;
		MapValidation l_mapValidation1;
		boolean l_testVar;
		boolean l_testVar1;
		
		d_gameMap.loadMap("risk.map");
		l_mapValidation = new MapValidation(d_gameMap);
		l_testVar = l_mapValidation.isMapConnected(d_gameMap.getCountries().values().iterator().next() , d_gameMap.getCountries().keySet());
		assertTrue(l_testVar);
		assertEquals(true, l_testVar);

		d_gameMap1.loadMap("WorldMap.txt");
		l_mapValidation1 = new MapValidation(d_gameMap1);
		l_testVar1 = l_mapValidation1.isMapConnected(d_gameMap1.getCountries().values().iterator().next() , d_gameMap1.getCountries().keySet());
		assertFalse(l_testVar1);
		assertEquals(false, l_testVar1);
	}
	
	/**
	 * This will validate "countryIterator" function with different maps provided in resource 
	 */
	@Test
	public void testCountryIterator() {

		MapValidation l_mapValidation;
		MapValidation l_mapValidation1;
		Set<Integer> l_countryIdsVisited;
		Set<Integer> l_countryIdsVisited1;
		Set<Integer> l_iterationOutputIntegers;
		Set<Integer> l_iterationOutputIntegers1;
		
		d_gameMap.loadMap("risk.map");		
		l_mapValidation = new MapValidation(d_gameMap);
		l_countryIdsVisited = new HashSet<Integer>();
		l_iterationOutputIntegers = l_mapValidation.countryIterator(d_gameMap.getCountries().values().iterator().next(), l_countryIdsVisited);
		assertEquals(d_gameMap.getCountries().keySet(), l_iterationOutputIntegers);
		assertTrue(l_iterationOutputIntegers.equals(d_gameMap.getCountries().keySet()));		
		
		d_gameMap1.loadMap("WorldMap.txt");		
		l_mapValidation1 = new MapValidation(d_gameMap1);
		l_countryIdsVisited1 = new HashSet<Integer>();
		l_iterationOutputIntegers1 = l_mapValidation1.countryIterator(d_gameMap1.getCountries().values().iterator().next(), l_countryIdsVisited1);
		assertNotEquals(d_gameMap1.getCountries().keySet(), l_iterationOutputIntegers1);
		assertFalse(l_iterationOutputIntegers1.equals(d_gameMap.getCountries().keySet()));
	}
}