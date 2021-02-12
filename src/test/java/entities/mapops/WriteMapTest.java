package entities.mapops;

import static org.junit.Assert.*;

import org.junit.Test;
import entities.*;
import entities.GameMap;

/**
 * Test for WriteMap
 *
 */
public class WriteMapTest {
	/**
	 * Checks if map is written properly
	 */
	@Test
	public void testWriteFullMap() {
		GameMap gMap = new GameMap();
		ReadMap rMap = new ReadMap(gMap);
		gMap.loadMap("uk.map");
		WriteMap l_writeMap = new WriteMap(gMap);
		boolean l_testVar = l_writeMap.writeFullMap("WorldMap.txt");
		assertTrue(l_testVar);
	}
}
	