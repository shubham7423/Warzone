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

	/*@Test
	public void testLoadMap() {
		fail("Not yet implemented");
	}

	@Test
	public void testEditContinent() {
		fail("Not yet implemented");
	}*/

	@Test
	public void testEditCountry() {
		String[] l_addCountry = new String[]{"editcountry", "-add", "india","Asia"}; 
		String l_result = d_commands.executeCommand(l_addCountry);
		assertEquals("Country \"india\" successfully removed to map", l_result);
	}

	/*@Test
	public void testEditNeighbour() {
		fail("Not yet implemented");
	}*/

}
