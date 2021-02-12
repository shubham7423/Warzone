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
	 */
	@Test
	public void testExecuteCommand() {
		String[] l_newStrings = new String[]{"loadmap", "uk.map"};
		String l_result = d_commands.executeCommand(l_newStrings);
		assertEquals("Map \"uk.map\" loaded successfully", l_result );
	}

	/**
	 * Test to check loaded map
	 */
	@Test
	public void testLoadMap() {
		String[] l_loadCommand_1 = new String[]{"loadmap", "uk.map"};
		String l_loadResultString_1 = d_commands.executeCommand(l_loadCommand_1);
		assertEquals("Map \"uk.map\" loaded successfully", l_loadResultString_1);
		
		String[] l_loadCommand_2 = new String[]{"loadmap", "xyzabc.map"};
		String l_loadResultString_2 = d_commands.executeCommand(l_loadCommand_2);
		assertEquals("Map \"xyzabc.map\" cannot be loaded", l_loadResultString_2);
	}

	/**
	 * Test to check addition deletion of continents
	 */
	@Test
	public void testEditContinent() {
		String[] l_loadCommand_1 = new String[]{"editmap", "uk.map"};
		String l_loadResultString_1 = d_commands.executeCommand(l_loadCommand_1);
		
		String[] l_addContinent = new String[]{"editcontinent", "-add", "1", "2"};
		String l_addResult = d_commands.executeCommand(l_addContinent);
		assertEquals("Continent \"1\" added to map", l_addResult);
		
		String[] l_removeContinent = new String[]{"editcontinent", "-remove", "1"};
		String l_removeResult = d_commands.executeCommand(l_removeContinent);
		assertEquals("Continent \"1\" successfully removed from map", l_removeResult);
	}

	/**
	 * Test to check addition deletion of countries
	 */
	@Test
	public void testEditCountry() {
		String[] l_loadCommand_1 = new String[]{"editmap", "uk.map"};
		String l_loadResultString_1 = d_commands.executeCommand(l_loadCommand_1);
		
    String[] l_addContinent = new String[]{"editcontinent", "-add", "1", "2"};
		d_commands.executeCommand(l_addContinent);
		String[] l_addCountry = new String[]{"editcountry", "-add", "1","1"};
		String l_addResult = d_commands.executeCommand(l_addCountry);
		assertEquals("Country \"1\" successfully removed to map", l_addResult);
		
		String[] l_removeCountry = new String[]{"editcountry", "-remove", "1"};
		String l_removeResult = d_commands.executeCommand(l_removeCountry);
		assertEquals("Country \"1\" successfully removed from map", l_removeResult);
	}

	/**
	 * Test to check addition deletion of neighbours
	 */
	@Test
	public void testEditNeighbour() {
		String[] l_loadCommand_1 = new String[]{"editmap", "uk.map"};
		String l_loadResultString_1 = d_commands.executeCommand(l_loadCommand_1);
		
		String[] l_addContinent = new String[]{"editcontinent", "-add", "1", "2"};
		d_commands.executeCommand(l_addContinent);
		String[] l_addCountry = new String[] {"editcountry", "-add", "1","1", "-add", "2", "1"};
		d_commands.executeCommand(l_addCountry);
		String[] l_addNeighbour = new String[]{"editneighbour", "-add", "1" ,"2"};
		String l_addResult = d_commands.executeCommand(l_addNeighbour);
		assertEquals("Country \"2\" is now a neighbour of country \"1\"", l_addResult);
		
	}
	
	/**
	 * Test to check map editing is allowed and executed correctly or not
	 */
	@Test
	public void testEditMap() {
		String[] l_loadCommand_1 = new String[]{"editmap", "uk.map"};
		String l_editResultString_1 = d_commands.executeCommand(l_loadCommand_1);
		assertEquals("Map \"uk.map\" ready for edit", l_editResultString_1);
		
	}
	
	/**
	 * Test to check map saving is allowed and executed correctly or not
	 */
	@Test
	public void testSaveMap() {
		String[] l_loadCommand_1 = new String[]{"editmap", "uk.map"};
		String l_loadResultString_1 = d_commands.executeCommand(l_loadCommand_1);
		
		String[] l_saveCommand_1 = new String[]{"savemap", "abc.map"};
		String l_saveResultString_1 = d_commands.executeCommand(l_saveCommand_1);
		assertEquals("Map file \"abc.map\" saved successfully", l_saveResultString_1);
		
	}

}
