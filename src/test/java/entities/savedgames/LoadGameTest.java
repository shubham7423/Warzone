package entities.savedgames;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import entities.GameMap;

import controller.GameEngine;

public class LoadGameTest {
	
	LoadGame d_game;
	GameMap d_map = new GameMap();
	GameEngine d_engine = new GameEngine();
	
	@Before
	public void setUp() {
		d_game = new LoadGame(new GameEngine());
	}

	@After
	public void tearDown() {
		d_game = null;
		d_map = null;
		d_engine = null;
	}
	
	@Test
	public void testLoadGame1() {
		String l_result1 = d_game.loadGame("fileBot.game");
		String expected1 = "Game Loaded Successfully.";
		assertEquals(l_result1, expected1);
		
		Set<Integer> l_countriesRead = d_game.getCountriesIds();
		Set<Integer> l_countries = new HashSet<>();
		
		for(int l_index =1; l_index<=42; l_index++) {			
			l_countries.add(l_index);
		}
		assertTrue(l_countries.equals(l_countriesRead));
	}
	
	@Test
	public void testLoadGame2() {
		String l_result1 = d_game.loadGame("fileBot.game");
		String expected1 = "Game Loaded Successfully.";
		assertEquals(l_result1, expected1);
		
		Set<Integer> l_continentsRead  = d_game.getContinentIds();
		Set<Integer> l_continents  = new HashSet<>();
		
		for(int l_index =1; l_index<=6; l_index++) {			
			l_continents.add(l_index);
		}
		assertTrue(l_continents.equals(l_continentsRead));
	}
	
	@Test
	public void testLoadGame3() {
		String l_result2 = null;
		try {			
			l_result2 = d_game.loadGame("fileX.game");
		} catch (Exception p_e) {
			System.out.println("File not found");
		}
		String expected2 = "Game file : \"fileX.game\" does not exist.";
		assertEquals(l_result2, expected2);	
	}
}
