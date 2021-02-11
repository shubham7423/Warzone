package entities;

import static org.junit.Assert.*;

import org.junit.Test;

import entities.mapops.ReadMap;

public class MapValidationTest {

	GameMap d_gameMap = new GameMap();
	@Test
	public void testIsMapConnected() {
		d_gameMap.loadMap("WorldMap.txt");
//		d_gameMap.loadMap("ameroki.map");
//		d_gameMap.loadMap("risk.map");
		MapValidation l_mapValidation = new MapValidation(d_gameMap);
		boolean l_testVar = l_mapValidation.isMapConnected(d_gameMap.getCountries().values().iterator().next() , d_gameMap.getCountries().keySet());
		assertTrue(l_testVar);
		
		//fail("Not yet implemented");
	}

}
