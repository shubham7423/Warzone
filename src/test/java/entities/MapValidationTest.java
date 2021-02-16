package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.mapops.ReadMap;

/**
 * This class performs Unit testing on every function of the MapValidation Class
 */
public class MapValidationTest {

	GameMap d_gameMap = new GameMap();
	
	/**
	 * this will validate "isMapConnected" function with different maps provided in resource
	 */
	@Test
	public void testIsMapConnected() {
		d_gameMap.loadMap("WorldMap.txt");
		
		MapValidation l_mapValidation = new MapValidation(d_gameMap);
		boolean l_testVar = l_mapValidation.isMapConnected(d_gameMap.getCountries().values().iterator().next() , d_gameMap.getCountries().keySet());

		assertFalse(l_testVar);
		assertEquals(false, l_testVar);
		
		d_gameMap.loadMap("risk.map");
		l_mapValidation = new MapValidation(d_gameMap);
		l_testVar = l_mapValidation.isMapConnected(d_gameMap.getCountries().values().iterator().next() , d_gameMap.getCountries().keySet());
		
		assertTrue(l_testVar);
		assertEquals(true, l_testVar);
		
	}

}