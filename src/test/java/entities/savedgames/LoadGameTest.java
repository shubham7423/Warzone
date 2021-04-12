package entities.savedgames;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

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

	@Test
	public void testLoadGame1() {
		String l_result1 = d_game.loadGame("file.game");
		String expected1 = "Game Loaded Successfully.";
		assertEquals(l_result1, expected1);
		
		Set<Integer> l_countriesRead = d_game.getCountriesIds();
		Set<Integer> l_continentsRead = d_game.getContinentIds();
		Set<Integer> l_countries = new HashSet<>();
		Set<Integer> l_continents = new HashSet<>();
		
		l_countries.add(1);
		l_countries.add(2);
		l_countries.add(3);
		l_countries.add(4);
		l_countries.add(5);
		l_countries.add(6);
		l_countries.add(7);
		l_countries.add(8);
		l_countries.add(9);
		l_countries.add(10);
		l_countries.add(11);
		l_countries.add(12);
		l_countries.add(13);
		l_countries.add(14);
		l_countries.add(15);
		l_countries.add(16);
		l_countries.add(17);
		l_countries.add(18);
		l_countries.add(19);
		l_countries.add(20);
		l_countries.add(21);
		l_countries.add(22);
		l_countries.add(23);
		l_countries.add(24);
		l_countries.add(25);
		l_countries.add(26);
		l_countries.add(27);
		l_countries.add(28);
		l_countries.add(29);
		l_countries.add(30);
		l_countries.add(31);
		l_countries.add(32);
		l_countries.add(33);
		l_countries.add(34);
		l_countries.add(35);
		l_countries.add(36);
		l_countries.add(37);
		l_countries.add(38);
		l_countries.add(39);
		l_countries.add(40);
		assertTrue(l_countries.equals(l_countriesRead));
		
		l_continents.add(1);
		l_continents.add(2);
		l_continents.add(3);
		l_continents.add(4);
		l_continents.add(5);
		l_continents.add(6);
		assertTrue(l_continents.equals(l_continentsRead));
	}
	
	@Test
	public void testLoadGame2() {
		String l_result2 = d_game.loadGame("fileX.game");
		String expected2 = "Game cannot be loaded.";
		assertEquals(l_result2, expected2);	
	}

}
