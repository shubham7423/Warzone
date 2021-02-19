package entities.mapops;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;


import entities.GameMap;

/**
 * Test for ReadMap
 *
 */
public class ReadMapTest {

	ReadMap map;
	
	/**
	 * Initialize map before each test
	 */
	@Before
	public void initialize() {
		map = new ReadMap(new GameMap());
	}
	
	/**
	 * Checks if map is read properly
	 */
	@Test
	public void testReadFullMap1() {
		boolean l_result= map.readFullMap("uk.map");
		assertTrue(l_result);
	}
	
	/**
	 * Check if missing map file results in false
	 */
	@Test
	public void testReadFullMap2() {
		boolean l_result= map.readFullMap("ukk.map");
		assertFalse(l_result);
	}
}

