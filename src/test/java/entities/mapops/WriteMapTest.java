package entities.mapops;

import static org.junit.Assert.*;

import org.junit.Test;
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
		WriteMap l_writeMap = new WriteMap(new GameMap());
		boolean l_testVar = l_writeMap.writeFullMap("WorldMapTest.map");
		assertTrue(l_testVar);
	}
}
