package controller;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.Player;

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
	
	@Test
	public void testAddPlayer() {
		String l_playerNameString = "John";
		String l_resultString1 = d_gameStarter.addPlayer(l_playerNameString);
		assertEquals("Player \""+ l_playerNameString +"\" added to map", l_resultString1);
		
		l_playerNameString = "John";
		String l_resultString2 = d_gameStarter.addPlayer(l_playerNameString);
		assertEquals("Player \""+ l_playerNameString +"\" already present in game", l_resultString2);
		
		l_playerNameString = "Doe";
		String l_resultString3 = d_gameStarter.addPlayer(l_playerNameString);
		assertEquals("Player \""+ l_playerNameString +"\" added to map", l_resultString3);
	}
}
