package controller;

import static org.junit.Assert.*;

import org.junit.Test;

import controller.state.edit.PostEdit;
import controller.state.edit.PreEdit;
import controller.state.gamephase.gamesetup.PostLoad;
import controller.state.gamephase.gamesetup.PreLoad;

/**
 * 
 * Test Commands
 *
 */
public class CommandsTest {
	private GameEngine d_gameEngine = new GameEngine();

	/**
	 * main executeCommand test Checks whether any command is executed or not
	 */
	@Test
	public void testExecuteCommand() {
		d_gameEngine.setPhase(new PreLoad(d_gameEngine));
		String[] l_newStrings = new String[] { "loadmap", "risk.map" };
		String l_result = d_gameEngine.executeCommand(l_newStrings);
		assertEquals("Map \"risk.map\" loaded successfully", l_result);
	}

	/**
	 * Test to check loaded map Checks if a map is properly loaded Checked with a
	 * file with correct name, incorrect name and file without .map as extension
	 */
	@Test
	public void testLoadMap() {
		d_gameEngine.setPhase(new PreLoad(d_gameEngine));
		String[] l_loadCommand1 = new String[] { "loadmap", "risk.map" };
		String l_loadResultString1 = d_gameEngine.executeCommand(l_loadCommand1);
		assertEquals("Map \"risk.map\" loaded successfully", l_loadResultString1);

		d_gameEngine.setPhase(new PreLoad(d_gameEngine));
		String[] l_loadCommand2 = new String[] { "loadmap", "xyzabc.map" };
		String l_loadResultString2 = d_gameEngine.executeCommand(l_loadCommand2);
		assertEquals("Map \"xyzabc.map\" cannot be loaded", l_loadResultString2);

		d_gameEngine.setPhase(new PreLoad(d_gameEngine));
		String[] l_loadCommand3 = new String[] { "loadmap", "xyzabc" };
		String l_loadResultString3 = d_gameEngine.executeCommand(l_loadCommand3);
		assertEquals("File extension should be .map", l_loadResultString3);
	}
//
//	/**
//	 * Test to check addition and deletion of continents Checked by adding a correct
//	 * continent id and, continent with id already present in map Checked by
//	 * removing a continent and also checked that correct results are obtained by
//	 * removing continent id which is not present in map.
//	 */
	@Test
	public void testEditContinent() {
		d_gameEngine.setPhase(new PreEdit(d_gameEngine));
		String[] l_loadCommand1 = new String[] { "editmap", "WorldMap.map" };
		String l_loadResultString1 = d_gameEngine.executeCommand(l_loadCommand1);

		//d_gameEngine.setPhase(new PreEdit(d_gameEngine));
		String[] l_addContinent1 = new String[] { "editcontinent", "-add", "1", "2" };
		String l_addResult1 = d_gameEngine.executeCommand(l_addContinent1);
		assertEquals("Continent \"1\" added to map", l_addResult1);
		
		//d_gameEngine.setPhase(new PreEdit(d_gameEngine));
		String[] l_addContinent2 = new String[] { "editcontinent", "-add", "1", "2" };
		String l_addResult2 = d_gameEngine.executeCommand(l_addContinent2);
		assertEquals("Continent \"1\" already present in map", l_addResult2);

		//d_gameEngine.setPhase(new PreEdit(d_gameEngine));
		String[] l_removeContinent1 = new String[] { "editcontinent", "-remove", "1" };
		String l_removeResult1 = d_gameEngine.executeCommand(l_removeContinent1);
		assertEquals("Continent \"1\" successfully removed from map", l_removeResult1);

		String[] l_removeContinent2 = new String[] { "editcontinent", "-remove", "1" };
		String l_removeResult2 = d_gameEngine.executeCommand(l_removeContinent2);
		assertEquals("Continent \"1\" not present in map", l_removeResult2);
	}

	/**
	 * Test to check addition and deletion of countries Checked by adding a correct
	 * country id and, country with id already present in map Checked by removing a
	 * country and also checked that correct results are obtained by removing
	 * country id which is not present in map.
	 */
	@Test
	public void testEditCountry() {
		
		d_gameEngine.setPhase(new PreEdit(d_gameEngine));
		String[] l_loadCommand1 = new String[] { "editmap", "WorldMap.map" };
		String l_loadResultString1 = d_gameEngine.executeCommand(l_loadCommand1);

		String[] l_addContinent = new String[] { "editcontinent", "-add", "1", "2" };
		d_gameEngine.executeCommand(l_addContinent);
		String[] l_addCountry1 = new String[] { "editcountry", "-add", "1", "1" };
		String l_addResult1 = d_gameEngine.executeCommand(l_addCountry1);
		assertEquals("Country \"1\" successfully added to map", l_addResult1);

		String[] l_addCountry2 = new String[] { "editcountry", "-add", "1", "1" };
		String l_addResult2 = d_gameEngine.executeCommand(l_addCountry2);
		assertEquals("Country \"1\" already present in map", l_addResult2);

		String[] l_removeCountry1 = new String[] { "editcountry", "-remove", "1" };
		String l_removeResult1 = d_gameEngine.executeCommand(l_removeCountry1);
		assertEquals("Country \"1\" successfully removed from map", l_removeResult1);

		String[] l_removeCountry2 = new String[] { "editcountry", "-remove", "1" };
		String l_removeResult2 = d_gameEngine.executeCommand(l_removeCountry2);
		assertEquals("Country \"1\" not present in map", l_removeResult2);
	}

/**
 * Test to check addition and deletion of neighbors Checked by adding a country
	 * to neighbor list of another country, and also checked that correct results
	 * were obtained by adding already added country to neighbor Checked that
	 * countries are correctly removed from source country neighbor list and correct
	 * results are obtained when a country is not a neighbor of source and is tried
	 * to remove
	 */
	@Test
	public void testEditNeighbor() {
		d_gameEngine.setPhase(new PreEdit(d_gameEngine));
		String[] l_loadCommand1 = new String[] { "editmap", "WorldMap.map" };
		String l_loadResultString1 = d_gameEngine.executeCommand(l_loadCommand1);

		String[] l_addContinent = new String[] { "editcontinent", "-add", "1", "2" };
		d_gameEngine.executeCommand(l_addContinent);
		String[] l_addCountry = new String[] { "editcountry", "-add", "1", "1", "-add", "2", "1" };
		d_gameEngine.executeCommand(l_addCountry);

		String[] l_addNeighbor1 = new String[] { "editneighbor", "-add", "1", "2" };
		String l_addResult1 = d_gameEngine.executeCommand(l_addNeighbor1);
		assertEquals("Country \"2\" is now a neighbor of country \"1\"", l_addResult1);

		String[] l_addNeighbor2 = new String[] { "editneighbor", "-add", "1", "2" };
		String l_addResult2 = d_gameEngine.executeCommand(l_addNeighbor2);
		assertEquals("Country \"2\" already a neighbor of \"1\"", l_addResult2);

		String[] l_removeNeighbor1 = new String[] { "editneighbor", "-remove", "1", "2" };
		String l_removeResult1 = d_gameEngine.executeCommand(l_removeNeighbor1);
		assertEquals("Country \"2\" removed from neighbors of \"1\"", l_removeResult1);

		String[] l_removeNeighbor2 = new String[] { "editneighbor", "-remove", "1", "2" };
		String l_removeResult2 = d_gameEngine.executeCommand(l_removeNeighbor2);
		assertEquals("Country \"2\" is not a neighbor of \"1\"", l_removeResult2);
	}

	/**
	 * Test to check map editing is allowed and executed correctly or not Checks
	 * that a map is properly loaded in edit mode Checked with a file with correct
	 * name, incorrect name and file without .map as extension
	 */
	@Test
	public void testEditMap() {
		
		d_gameEngine.setPhase(new PreEdit(d_gameEngine));
		String[] l_loadCommand1 = new String[] { "editmap", "WorldMap.map" };
		String l_editResultString1 = d_gameEngine.executeCommand(l_loadCommand1);
		assertEquals("Map \"WorldMap.map\" ready for edit", l_editResultString1);

		d_gameEngine.setPhase(new PreEdit(d_gameEngine));
		String[] l_loadCommand2 = new String[] { "editmap", "WorldMap" };
		String l_editResultString2 = d_gameEngine.executeCommand(l_loadCommand2);
		assertEquals("File extension should be .map", l_editResultString2);

		d_gameEngine.setPhase(new PostEdit(d_gameEngine));
		String[] l_loadCommand3 = new String[] { "editmap", "WorldMap.map" };
		String l_editResultString3 = d_gameEngine.executeCommand(l_loadCommand3);
		assertEquals("Invalid command in state PostEdit", l_editResultString3);

	}

	/**
	 * Test to check map saving is allowed and executed correctly or not Checks that
	 * a map is properly loaded in edit mode Checked with a file with correct name,
	 * incorrect name and file without .map as extension
	 */
	@Test
	public void testSaveMap() {
		
		d_gameEngine.setPhase(new PreEdit(d_gameEngine));
		String[] l_loadCommand1 = new String[] { "editmap", "risk.map" };
		String l_loadResultString1 = d_gameEngine.executeCommand(l_loadCommand1);

		String[] l_saveCommand1 = new String[] { "savemap", "abc.map" };
		String l_saveResultString1 = d_gameEngine.executeCommand(l_saveCommand1);
		assertEquals("Map file \"abc.map\" saved successfully", l_saveResultString1);

		String[] l_saveCommand2 = new String[] { "savemap", "abc" };
		String l_saveResultString2 = d_gameEngine.executeCommand(l_saveCommand2);
		assertEquals("File extension should be .map", l_saveResultString2);
	}

	/**
	 * Test to check whether players are added/removed correctly or not Checked by
	 * adding a correct player name and, player with name already present in game
	 * Checked by removing a player and also checked that correct results are
	 * obtained by removing player name which is not present in game.
	 */
	@Test
	public void testGamePlayer() {
		
		d_gameEngine.setPhase(new PreLoad(d_gameEngine));
		String[] l_newStrings = new String[] { "loadmap", "risk.map" };
		String l_result = d_gameEngine.executeCommand(l_newStrings);

		String[] l_addPlayer1 = new String[] { "gameplayer", "-add", "Jay" };
		String l_addResult1 = d_gameEngine.executeCommand(l_addPlayer1);
		assertEquals("Player \"Jay\" added to game", l_addResult1);

		String[] l_addPlayer2 = new String[] { "gameplayer", "-add", "Jay" };
		String l_addResult2 = d_gameEngine.executeCommand(l_addPlayer2);
		assertEquals("Player \"Jay\" already present in game", l_addResult2);

		String[] l_removePlayer1 = new String[] { "gameplayer", "-remove", "Jay" };
		String l_removeResult1 = d_gameEngine.executeCommand(l_removePlayer1);
		assertEquals("Player \"Jay\" removed from game", l_removeResult1);

		String[] l_removePlayer2 = new String[] { "gameplayer", "-remove", "Jay" };
		String l_removeResult2 = d_gameEngine.executeCommand(l_removePlayer2);
		assertEquals("Player \"Jay\" not present in game", l_removeResult2);
	}
	
	/**
	 * This function tests the validateMap function with different conditions like
	 * if the map is null, without countries, not traverseble and other different
	 * conditions.
	 */
	@Test
	public void testValidateMap() {
		GameEngine l_gameEngine1 = new GameEngine();
		l_gameEngine1.setPhase(new PreLoad(l_gameEngine1));
		String[] l_newString1 = new String[] { "loadmap", "uk.map" };
		String l_result1 = l_gameEngine1.executeCommand(l_newString1);
		String[] l_valString = new String[] { "validatemap" };
		String l_resValString = l_gameEngine1.executeCommand(l_valString);
		assertEquals(" The graph is connected. Countries are traverseble.", l_resValString);

		GameEngine l_gameEngine2 = new GameEngine();
		l_gameEngine2.setPhase(new PreLoad(l_gameEngine2));
		String[] l_valString2 = new String[] { "validatemap" };
		String l_resValString2 = l_gameEngine2.executeCommand(l_valString2);
		assertEquals("The Map does not contain any countries.", l_resValString2);

		GameEngine l_gameEngine3 = new GameEngine();
		l_gameEngine3.setPhase(new PreLoad(l_gameEngine3));
		l_gameEngine3.setGameMap(null);
		String[] l_valString3 = new String[] { "validatemap" };
		String l_resValString3 = l_gameEngine3.executeCommand(l_valString3);
		assertEquals("Cannot validate map", l_resValString3);
	}

}
