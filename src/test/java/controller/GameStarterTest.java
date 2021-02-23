package controller;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.GameMap;
import entities.Player;

public class GameStarterTest {

	GameStarter d_gameStarter;
	String d_mapName1 = "risk.map";
	
	/**
	 * This is the before method for testing, which will run before any 
	 * other test function is being executed. This is used to instantiate the Class which this tests.
	 */
	@Before
	public void setUp() {
		d_gameStarter = new GameStarter();
	}
	
	/**
	 * This is the before method for testing, which will run after any 
	 * other test function is being executed. This is used to deallocate the member variables.
	 */
	@After
	public void tearDown() {
		d_gameStarter = null;
	}
	
	/**
	 * This test function tests the loadMap function with multiple criteria.
	 */
	@Test
	public void testLoadMap() {
		String l_resultString;
		l_resultString =  d_gameStarter.loadMap(d_mapName1);
		assertEquals("Map \""+ d_mapName1 +"\" loaded successfully", l_resultString);
		assertNotNull(l_resultString);
	}
	
	/**
	 * This test function tests the addPlayer function with 
	 * multiple players being added to the game.
	 */
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
	
	/**
	 * This test function tests the removePlayer function with 
	 * multiple players being added first and then remove them from the game.
	 */
	@Test
	public void testRemovePlayer() {
		String l_playerNameString;
		d_gameStarter.addPlayer("John");
		d_gameStarter.addPlayer("Doe");
		d_gameStarter.addPlayer("Smith");
		d_gameStarter.addPlayer("Jack");
		d_gameStarter.addPlayer("Arnold");
		d_gameStarter.addPlayer("Paul");
		
		l_playerNameString = "John";
		String l_resultString1 = d_gameStarter.removePlayer(l_playerNameString);
		assertEquals("Player \""+ l_playerNameString +"\" removed from map", l_resultString1);
		
		l_playerNameString = "John";
		String l_resultString2 = d_gameStarter.removePlayer(l_playerNameString);
		assertEquals("Player \""+ l_playerNameString + "\" not present in game", l_resultString2);
		
		l_playerNameString = "Paul";
		String l_resultString3 = d_gameStarter.removePlayer(l_playerNameString);
		assertEquals("Player \""+ l_playerNameString +"\" removed from map", l_resultString3);
		
		l_playerNameString = "Arnold";
		String l_resultString4 = d_gameStarter.removePlayer(l_playerNameString);
		assertEquals("Player \""+ l_playerNameString +"\" removed from map", l_resultString4);
	}
	
	/**
	 * This function tests the object returned by the getGameMap function.
	 */
	@Test
	public void testGetGameMap() {

		d_gameStarter.loadMap(d_mapName1);
		GameMap l_resultGameMap =  d_gameStarter.getGameMap();
		GameStarter l_resultGameStarter = d_gameStarter;
		l_resultGameStarter.loadMap("risk.map");
		
		assertEquals(l_resultGameMap, l_resultGameStarter.getGameMap());
		
		GameStarter l_resultGameStarter1 = new GameStarter();
		l_resultGameStarter1.loadMap("risk.map");
		GameMap l_resultGameMap1 = l_resultGameStarter1.getGameMap();
		assertNotEquals(l_resultGameMap1, l_resultGameStarter.getGameMap());
	}
	
	@Test
	public void testGetPlayers() {
		Boolean l_result;
		
		d_gameStarter.addPlayer("John");
		d_gameStarter.addPlayer("Doe");
		d_gameStarter.addPlayer("Erick");
		HashMap<String, Player> l_resultPlayersExpected  = d_gameStarter.getPlayers();
	 	Set<String> l_resultPlayersExpectedKeySet = l_resultPlayersExpected.keySet();
		
		GameStarter l_GameStarter = new GameStarter();
		l_GameStarter.addPlayer("John");
		l_GameStarter.addPlayer("Doe");
		l_GameStarter.addPlayer("Erick");
		
		l_result = l_resultPlayersExpectedKeySet.equals(l_GameStarter.getPlayers().keySet());

		assertTrue(l_result);
		
		l_GameStarter.addPlayer("Arnold");
		l_result = l_resultPlayersExpectedKeySet.equals(l_GameStarter.getPlayers().keySet());
		assertFalse(l_result);
	}
}
