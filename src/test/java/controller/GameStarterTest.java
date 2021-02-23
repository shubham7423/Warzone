package controller;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class GameStarterTest {

	GameStarter d_gameStarter;
	String d_mapName1 = "risk.map";
		
	@Test
	public void testLoadMap() {
		d_gameStarter = new GameStarter();
		String l_resultString;
		l_resultString =  d_gameStarter.loadMap(d_mapName1);
		assertEquals("Map \""+ d_mapName1 +"\" loaded successfully", l_resultString);
	}
}
