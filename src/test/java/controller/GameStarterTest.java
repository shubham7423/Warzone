package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameStarterTest {

	GameStarter d_gameStarter;
	String d_mapName1 = "risk.map";
		
	@Before
	public void setUp() {
		d_gameStarter = new GameStarter();		
	}
	
	@After
	public void tearDown() {
		d_gameStarter = null;
	}
	
	@Test
	public void testLoadMap() {
		String l_resultString;
		l_resultString =  d_gameStarter.loadMap(d_mapName1);
		assertEquals("Map \""+ d_mapName1 +"\" loaded successfully", l_resultString);
		assertNotNull(l_resultString);
	}
	
	//addPlayer method testing
	//removePlayer method testing
	//getGameMap method testing
	//get players
	//check continent ownership
	

	
	@Test
	public void testValidateMap() {
		
	}

	
	@Test
	public void testCheckContinentOwnership() {
		fail("Not yet implemented");
	}

}
