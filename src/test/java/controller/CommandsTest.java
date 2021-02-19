package controller;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * Test Commands
 *
 */
public class CommandsTest {
	Commands d_commands = new Commands();

	/**
	 * main executeCommand test
	 * Checks whether any command is executed
	 */
	@Test
	public void testExecuteCommand() {
		String[] l_newStrings = new String[] { "loadmap", "WorldMap.map" };
		String l_result = d_commands.executeCommand(l_newStrings);
		assertEquals("Map \"WorldMap.map\" loaded successfully", l_result);
	}

	/**
	 * Test to check loaded map
	 * Checks that a map is properly loaded
	 * Checked with a file with correct name, incorrect name and file without .map as extension
	 */
	@Test
	public void testLoadMap() {
		String[] l_loadCommand_1 = new String[] { "loadmap", "WorldMap.map" };
		String l_loadResultString_1 = d_commands.executeCommand(l_loadCommand_1);
		assertEquals("Map \"WorldMap.map\" loaded successfully", l_loadResultString_1);

		String[] l_loadCommand_2 = new String[] { "loadmap", "xyzabc.map" };
		String l_loadResultString_2 = d_commands.executeCommand(l_loadCommand_2);
		assertEquals("Map \"xyzabc.map\" cannot be loaded", l_loadResultString_2);
		
		String[] l_loadCommand_3 = new String[] { "loadmap", "xyzabc" };
		String l_loadResultString_3 = d_commands.executeCommand(l_loadCommand_3);
		assertEquals("File extension should be .map", l_loadResultString_3);
	}

	/**
	 * Test to check addition deletion of continents
	 * Checked by adding a correct continent id and, continent with id already present in map
	 * Checked by removing a continent and also checked that correct results are obtained by removing continent id which is not present in map.
	 */
	@Test
	public void testEditContinent() {
		String[] l_loadCommand_1 = new String[] { "editmap", "WorldMap.map" };
		String l_loadResultString_1 = d_commands.executeCommand(l_loadCommand_1);

		String[] l_addContinent1 = new String[] { "editcontinent", "-add", "1", "2" };
		String l_addResult1 = d_commands.executeCommand(l_addContinent1);
		assertEquals("Continent \"1\" added to map", l_addResult1);
		
		String[] l_addContinent2 = new String[] { "editcontinent", "-add", "1", "2" };
		String l_addResult2 = d_commands.executeCommand(l_addContinent2);
		assertEquals("Continent \"1\" already present in map", l_addResult2);

		String[] l_removeContinent1 = new String[] { "editcontinent", "-remove", "1" };
		String l_removeResult1 = d_commands.executeCommand(l_removeContinent1);
		assertEquals("Continent \"1\" successfully removed from map", l_removeResult1);
		
		String[] l_removeContinent2 = new String[] { "editcontinent", "-remove", "1" };
		String l_removeResult2 = d_commands.executeCommand(l_removeContinent2);
		assertEquals("Continent \"1\" not present in map", l_removeResult2);
	}

	/**
	 * Test to check addition deletion of countries
	 * Checked by adding a correct country id and, country with id already present in map
	 * Checked by removing a country and also checked that correct results are obtained by removing country id which is not present in map.
	 */
	@Test
	public void testEditCountry() {
		String[] l_loadCommand_1 = new String[] { "editmap", "WorldMap.map" };
		String l_loadResultString_1 = d_commands.executeCommand(l_loadCommand_1);

		String[] l_addContinent = new String[] { "editcontinent", "-add", "1", "2" };
		d_commands.executeCommand(l_addContinent);
		String[] l_addCountry1 = new String[] { "editcountry", "-add", "1", "1" };
		String l_addResult1 = d_commands.executeCommand(l_addCountry1);
		assertEquals("Country \"1\" successfully added to map", l_addResult1);
		
		String[] l_addCountry2 = new String[] { "editcountry", "-add", "1", "1" };
		String l_addResult2 = d_commands.executeCommand(l_addCountry2);
		assertEquals("Country \"1\" already present in map", l_addResult2);

		String[] l_removeCountry1 = new String[] { "editcountry", "-remove", "1" };
		String l_removeResult1 = d_commands.executeCommand(l_removeCountry1);
		assertEquals("Country \"1\" successfully removed from map", l_removeResult1);
		
		String[] l_removeCountry2 = new String[] { "editcountry", "-remove", "1" };
		String l_removeResult2 = d_commands.executeCommand(l_removeCountry2);
		assertEquals("Country \"1\" not present in map", l_removeResult2);
	}

	/**
	 * Test to check addition deletion of neighbours
	 * Checked by adding a country to neighbour list of another country, and also checked that correct results were obtained by adding already added country to neighbour
	 * Checked that countries are correctly removed from source country neighbour list and correct results are obtained when a country is not a neighbour of source and is tried to remove
	 */
	@Test
	public void testEditNeighbour() {
		String[] l_loadCommand_1 = new String[] { "editmap", "WorldMap.map" };
		String l_loadResultString_1 = d_commands.executeCommand(l_loadCommand_1);

		String[] l_addContinent = new String[] { "editcontinent", "-add", "1", "2" };
		d_commands.executeCommand(l_addContinent);
		String[] l_addCountry = new String[] { "editcountry", "-add", "1", "1", "-add", "2", "1" };
		d_commands.executeCommand(l_addCountry);
		
		String[] l_addNeighbour1 = new String[] { "editneighbour", "-add", "1", "2" };
		String l_addResult1 = d_commands.executeCommand(l_addNeighbour1);
		assertEquals("Country \"2\" is now a neighbour of country \"1\"", l_addResult1);
		
		String[] l_addNeighbour2 = new String[] { "editneighbour", "-add", "1", "2" };
		String l_addResult2 = d_commands.executeCommand(l_addNeighbour2);
		assertEquals("Country \"2\" already a neighbour of \"1\"", l_addResult2);
		
		String[] l_removeNeighbour1 = new String[] { "editneighbour", "-remove", "1", "2" };
		String l_removeResult1 = d_commands.executeCommand(l_removeNeighbour1);
		assertEquals("Country \"2\" removed from neighbours of \"1\"", l_removeResult1);
		
		String[] l_removeNeighbour2 = new String[] { "editneighbour", "-remove", "1", "2" };
		String l_removeResult2 = d_commands.executeCommand(l_removeNeighbour2);
		assertEquals("Country \"2\" is not a neighbour of \"1\"", l_removeResult2);
	}

	/**
	 * Test to check map editing is allowed and executed correctly or not
	 * Checks that a map is properly loaded in edit mode
	 * Checked with a file with correct name, incorrect name and file without .map as extension
	 */
	@Test
	public void testEditMap() {
		String[] l_loadCommand_1 = new String[] { "editmap", "WorldMap.map" };
		String l_editResultString_1 = d_commands.executeCommand(l_loadCommand_1);
		assertEquals("Map \"WorldMap.map\" ready for edit", l_editResultString_1);
		
		String[] l_loadCommand_2 = new String[] { "editmap", "WorldMap" };
		String l_editResultString_2 = d_commands.executeCommand(l_loadCommand_2);
		assertEquals("File extension should be .map", l_editResultString_2);
		
		String[] l_loadCommand_3 = new String[] { "editmap", "WorldMap.map" };
		String l_editResultString_3 = d_commands.executeCommand(l_loadCommand_3);
		assertEquals("Edit map not available", l_editResultString_3);

	}

	/**
	 * Test to check map saving is allowed and executed correctly or not
	 * Checks that a map is properly loaded in edit mode
	 * Checked with a file with correct name, incorrect name and file without .map as extension
	 */
	@Test
	public void testSaveMap() {
		String[] l_loadCommand_1 = new String[] { "editmap", "WorldMap.map" };
		String l_loadResultString_1 = d_commands.executeCommand(l_loadCommand_1);

		String[] l_saveCommand_1 = new String[] { "savemap", "abc.map" };
		String l_saveResultString_1 = d_commands.executeCommand(l_saveCommand_1);
		assertEquals("Map file \"abc.map\" saved successfully", l_saveResultString_1);

		String[] l_saveCommand_2 = new String[] { "savemap", "abc" };
		String l_saveResultString_2 = d_commands.executeCommand(l_saveCommand_2);
		assertEquals("File extension should be .map", l_saveResultString_2);
	}
	
	/**
	 * Test to check whether players are added/removed correctly or not
	 * Checked by adding a correct player name and, player with name already present in game
	 * Checked by removing a player and also checked that correct results are obtained by removing player name which is not present in game.
	 */
	@Test
	public void testGamePlayer() {
		String[] l_newStrings = new String[] { "loadmap", "WorldMap.map" };
		String l_result = d_commands.executeCommand(l_newStrings);
		
		String[] l_addPlayer1 = new String[] { "gameplayer", "-add", "Jay" };
		String l_addResult1 = d_commands.executeCommand(l_addPlayer1);
		assertEquals("Player \"Jay\" added to map", l_addResult1);
		
		String[] l_addPlayer2 = new String[] { "gameplayer", "-add", "Jay" };
		String l_addResult2 = d_commands.executeCommand(l_addPlayer2);
		assertEquals("Player \"Jay\" already present in game", l_addResult2);

		String[] l_removePlayer1 = new String[] { "gameplayer", "-remove", "Jay" };
		String l_removeResult1 = d_commands.executeCommand(l_removePlayer1);
		assertEquals("Player \"Jay\" removed from map", l_removeResult1);

		String[] l_removePlayer2 = new String[] { "gameplayer", "-remove", "Jay" };
		String l_removeResult2 = d_commands.executeCommand(l_removePlayer2);
		assertEquals("Player \"Jay\" not present in game", l_removeResult2);
	}

}
