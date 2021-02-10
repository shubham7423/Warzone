package controller;

import static org.junit.Assert.*;

import org.junit.Test;

public class CommandsTest {
	Commands d_commands = new Commands();

	@Test
	public void testExecuteCommand() {
		String[] l_newStrings = new String[]{"loadmap", "uk.map"};
		String l_result = d_commands.executeCommand(l_newStrings);
		assertEquals("Map \"uk.map\" loaded successfully", l_result );
	}

	@Test
	public void testLoadMap() {
		String[] l_loadCommand_1 = new String[]{"loadmap", "uk.map"};
		String l_loadResultString_1 = d_commands.executeCommand(l_loadCommand_1);
		assertEquals("Map \"uk.map\" loaded successfully", l_loadResultString_1);
		
		String[] l_loadCommand_2 = new String[]{"loadmap", "u.map"};
		String l_loadResultString_2 = d_commands.executeCommand(l_loadCommand_2);
		assertEquals("Map \"u.map\" cannot be loaded", l_loadResultString_2);
	}

	@Test
	public void testEditContinent() {
		String[] l_addContinent = new String[]{"editcontinent", "-add", "Asia", "2"};
		String l_addResult = d_commands.executeCommand(l_addContinent);
		assertEquals("Continent \"Asia\" added to map", l_addResult);
		
		String[] l_removeContinent = new String[]{"editcontinent", "-remove", "Asia"};
		String l_removeResult = d_commands.executeCommand(l_removeContinent);
		assertEquals("Continent \"Asia\" successfully removed from map", l_removeResult);
	}

	@Test
	public void testEditCountry() {
		String[] l_addContinent = new String[]{"editcontinent", "-add", "Asia", "2"};
		d_commands.executeCommand(l_addContinent);
		String[] l_addCountry = new String[]{"editcountry", "-add", "india","Asia"};
		String l_addResult = d_commands.executeCommand(l_addCountry);
		assertEquals("Country \"india\" successfully removed to map", l_addResult);
		
		String[] l_removeCountry = new String[]{"editcountry", "-remove", "india"};
		String l_removeResult = d_commands.executeCommand(l_removeCountry);
		assertEquals("Country \"india\" successfully removed from map", l_removeResult);
	}

	@Test
	public void testEditNeighbour() {
		String[] l_addContinent = new String[]{"editcontinent", "-add", "Asia", "2"};
		d_commands.executeCommand(l_addContinent);
		String[] l_addCountry = new String[] {"editcountry", "-add", "india","Asia", "-add", "can", "Asia"};
		d_commands.executeCommand(l_addCountry);
		String[] l_addNeighbour = new String[]{"editneighbour", "-add", "india" ,"can"};
		String l_addResult = d_commands.executeCommand(l_addNeighbour);
		assertEquals("Country \"india\" successfully removed to map", l_addResult);
		
	}

}
