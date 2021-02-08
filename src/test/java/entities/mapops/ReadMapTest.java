package entities.mapops;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;


import entities.GameMap;

/**
 * Test for ReadMap
 * @author shubhampatel
 *
 */
public class ReadMapTest {

	/**
	 * Checks if map is read properly
	 */
	@Test
	public void testReadFullMap() {
		ReadMap map = new ReadMap(new GameMap());
		boolean l_result= map.readFullMap("uk.map");
		assertTrue(l_result);
	}
}

