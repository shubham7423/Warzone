package entities.mapops;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.GameMap;

import java.util.*;

/**
 * This class performs Unit testing on every function of the MapValidation Class
 */
public class MapValidationTest {

	GameMap d_gameMap = new GameMap();
	GameMap d_gameMap1 = new GameMap();
	
	/**
	 * This test will validate "getMapValidationStatus" function which will return boolean value
	 */
	@Test
	public void testGetMapValidationStatus() {
		GameMap l_gameMap = new GameMap();
		l_gameMap.loadMap("risk.map");
		MapValidation l_mapValidation = new MapValidation(l_gameMap);
		l_mapValidation.validate();
		assertTrue(l_mapValidation.getMapValidationStatus());
		
		GameMap l_gameMap1 = new GameMap();
		l_gameMap1.loadMap("WorldMapFail.map");
		MapValidation l_mapValidation1 = new MapValidation(l_gameMap1);
		l_mapValidation1.validate();
		assertFalse(l_mapValidation1.getMapValidationStatus());
	
		GameMap l_gameMap2 = new GameMap();
		l_gameMap1.loadMap("EmptyMap.map");
		MapValidation l_mapValidation2 = new MapValidation(l_gameMap2);
		l_mapValidation2.validate();
		assertFalse(l_mapValidation2.getMapValidationStatus());
	}
	
	/**
	 * This test will validate "validate" function with different maps provided 
	 * which outputs the string value based on multiple conditions of map being loaded.
	 */
	@Test
	public void  testValidate() {
		GameMap l_gameMap = new GameMap();
		l_gameMap.loadMap("risk.map");
		MapValidation l_mapValidation = new MapValidation(l_gameMap);
		assertEquals(" The graph is connected. Countries are traverseble.", l_mapValidation.validate());
		
		GameMap l_gameMap1 = new GameMap();
		l_gameMap1.loadMap("WorldMapFail.map");
		MapValidation l_mapValidation1 = new MapValidation(l_gameMap1);
		assertEquals(" The graph is not connected. Countries are not traverseble.", l_mapValidation1.validate());
	}
	
	/**
	 * This test will validate "isConnected" function with different maps provided in resource
	 */
	@Test
	public void testisConnected() {
		
		MapValidation l_mapValidation;
		MapValidation l_mapValidation1;
		boolean l_testVar;
		boolean l_testVar1;
		
		d_gameMap.loadMap("risk.map");
		l_mapValidation = new MapValidation(d_gameMap);
		l_testVar = l_mapValidation.isConnected(d_gameMap.getCountries().values().iterator().next() , d_gameMap.getCountries().keySet());
		assertTrue(l_testVar);
		assertEquals(true, l_testVar);

		d_gameMap1.loadMap("WorldMapFail.map");
		l_mapValidation1 = new MapValidation(d_gameMap1);
		l_testVar1 = l_mapValidation1.isConnected(d_gameMap1.getCountries().values().iterator().next() , d_gameMap1.getCountries().keySet());
		assertFalse(l_testVar1);
		assertEquals(false, l_testVar1);
	}
	
	/**
	 * This test will validate "countryIterator" function with different maps provided in resource 
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
		
		d_gameMap1.loadMap("WorldMapFail.map");		
		l_mapValidation1 = new MapValidation(d_gameMap1);
		l_countryIdsVisited1 = new HashSet<Integer>();
		l_iterationOutputIntegers1 = l_mapValidation1.countryIterator(d_gameMap1.getCountries().values().iterator().next(), l_countryIdsVisited1);
		assertNotEquals(d_gameMap1.getCountries().keySet(), l_iterationOutputIntegers1);
		assertFalse(l_iterationOutputIntegers1.equals(d_gameMap.getCountries().keySet()));
	}
}