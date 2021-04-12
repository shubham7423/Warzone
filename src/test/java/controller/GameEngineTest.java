package controller;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.Test;

import controller.state.edit.PostEdit;
import controller.state.edit.PreEdit;
import controller.state.gamephase.gameplay.ExecuteOrders;
import controller.state.gamephase.gameplay.IssueOrders;
import controller.state.gamephase.gamesetup.GameSetup;
import controller.state.gamephase.gamesetup.PostLoad;
import controller.state.gamephase.gamesetup.PreLoad;
import entities.orders.Advance;
import entities.orders.Deploy;

/**
 * GameEngine Test
 */
public class GameEngineTest {
	private GameEngine d_gameEngine = new GameEngine();

	/**
	 * main executeCommand test Checks whether any command is executed or not
	 */
	@Test
	public void testExecuteCommand() {
		d_gameEngine.setPhase(new PreLoad(d_gameEngine));
		String[] l_newStrings = new String[] { "loadmap", "risk.map" };
		String l_result = d_gameEngine.executeCommand(l_newStrings);
		assertEquals("Domination Map \"risk.map\" loaded successfully", l_result);
	}

	/**
	 * Test to check if specific orders can be executed in specific phase.
	 */
	@Test
	public void testPhase() {
		d_gameEngine.setPhase(new PreLoad(d_gameEngine));
		String[] l_newStrings = new String[] { "deploy", "2", "3" };
		String l_result = d_gameEngine.executeCommand(l_newStrings);
		assertEquals("Invalid command in phase PreLoad", l_result);

		d_gameEngine.setPhase(new PostLoad(d_gameEngine));
		l_newStrings = new String[] { "loadmap", "risk.map" };
		l_result = d_gameEngine.executeCommand(l_newStrings);
		assertEquals("Map already loaded", l_result);

		d_gameEngine.setPhase(new IssueOrders(d_gameEngine));
		l_newStrings = new String[] { "loadmap", "risk.map" };
		l_result = d_gameEngine.executeCommand(l_newStrings);
		assertEquals("Invalid command in phase IssueOrders", l_result);
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
		assertEquals("Domination Map \"risk.map\" loaded successfully", l_loadResultString1);

		d_gameEngine.setPhase(new PreLoad(d_gameEngine));
		String[] l_loadCommand = new String[] { "loadmap", "Canada.map" };
		String l_loadResultString = d_gameEngine.executeCommand(l_loadCommand);
		assertEquals("Conquest Map \"Canada.map\" loaded successfully", l_loadResultString);

		d_gameEngine.setPhase(new PreLoad(d_gameEngine));
		String[] l_loadCommand2 = new String[] { "loadmap", "xyzabc.map" };
		String l_loadResultString2 = d_gameEngine.executeCommand(l_loadCommand2);
		assertEquals("Map \"xyzabc.map\" cannot be loaded", l_loadResultString2);

		d_gameEngine.setPhase(new PreLoad(d_gameEngine));
		String[] l_loadCommand3 = new String[] { "loadmap", "xyzabc" };
		String l_loadResultString3 = d_gameEngine.executeCommand(l_loadCommand3);
		assertEquals("File extension should be .map", l_loadResultString3);
	}

	/**
	 * Test to check addition and deletion of continents Checked by adding a correct
	 * continent id and, continent with id already present in map Checked by
	 * removing a continent and also checked that correct results are obtained by
	 * removing continent id which is not present in map.
	 */
	@Test
	public void testEditContinent() {
		d_gameEngine.setPhase(new PreEdit(d_gameEngine));
		String[] l_loadCommand1 = new String[] { "editmap", "WorldMap.map" };
		String l_loadResultString1 = d_gameEngine.executeCommand(l_loadCommand1);

		String[] l_addContinent1 = new String[] { "editcontinent", "-add", "1", "2" };
		String l_addResult1 = d_gameEngine.executeCommand(l_addContinent1);
		assertEquals("Continent \"1\" added to map", l_addResult1);

		String[] l_addContinent2 = new String[] { "editcontinent", "-add", "1", "2" };
		String l_addResult2 = d_gameEngine.executeCommand(l_addContinent2);
		assertEquals("Continent \"1\" already present in map", l_addResult2);

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
		assertEquals("Invalid command in phase PostEdit", l_editResultString3);
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
		assertEquals("Domination Map file \"abc.map\" saved successfully", l_saveResultString1);

		String[] l_saveCommand3 = new String[] { "savemap", "conquestabc.map" };
		String l_saveResultString3 = d_gameEngine.executeCommand(l_saveCommand3);
		assertEquals("Conquest Map file \"conquestabc.map\" saved successfully", l_saveResultString3);

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
	 * if the map is null, without countries, not traversable and other different
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

	/**
	 * Check to see if correct player is declared as winner.
	 */
	@Test
	public void winningTest() {
		GameEngine l_gameEngine = new GameEngine();

		l_gameEngine.setPhase(new PostLoad(l_gameEngine));
		String[] l_newString1 = new String[] { "gameplayer", "-add", "Shubham", "-add", "Meet" };
		String l_result1 = l_gameEngine.executeCommand(l_newString1);
		l_gameEngine.getGameMap().addContinent(1, 5);
		l_gameEngine.getGameMap().addCountry(1, 1);
		l_gameEngine.getGameMap().addCountry(2, 1);
		l_gameEngine.getGameMap().addNeighbor(1, 2);
		l_gameEngine.getGameMap().getCountries().get(1).setPlayer(l_gameEngine.d_players.get("Shubham"));
		l_gameEngine.getGameMap().getCountries().get(2).setPlayer(l_gameEngine.d_players.get("Meet"));
		l_gameEngine.d_players.get("Shubham").addCountry(l_gameEngine.getGameMap().getCountries().get(1));
		l_gameEngine.d_players.get("Shubham").setNumberOfArmies();
		l_gameEngine.d_players.get("Meet").addCountry(l_gameEngine.getGameMap().getCountries().get(2));
		l_gameEngine.d_players.get("Meet").setNumberOfArmies();

		l_gameEngine.setPhase(new IssueOrders(l_gameEngine));
		Deploy l_deploy = new Deploy(l_gameEngine.d_players.get("Shubham"), 1, 3);
		l_gameEngine.d_players.get("Shubham").d_orders.add(l_deploy);
		l_gameEngine.addPlayerOrder(l_gameEngine.d_players.get("Shubham"));

		Advance l_advanceCmd = new Advance(l_gameEngine.d_players.get("Shubham"), 1, 2, 2);
		HashMap<String, Integer> l_ordersBefore = new HashMap<String, Integer>();
		l_ordersBefore.putAll(l_gameEngine.d_players.get("Shubham").d_cardsOwned);

		l_gameEngine.d_players.get("Shubham").d_orders.add(l_advanceCmd);
		l_gameEngine.addPlayerOrder(l_gameEngine.d_players.get("Shubham"));

		l_gameEngine.setPhase(new ExecuteOrders(l_gameEngine));
		l_gameEngine.getPhase().executeOrders();
		assertEquals(1, l_gameEngine.d_playerName.size());

		HashMap<String, Integer> l_ordersAfter = l_gameEngine.d_players.get("Shubham").d_cardsOwned;
		assertFalse(l_ordersBefore.equals(l_ordersAfter));
	}

	/**
	 * Test to check the tournament mode works perfectly, tournament is held on two
	 * maps with two games on each map and two players with random and cheater
	 * behavior.
	 */
	@Test
	public void tournamentTest() {
		GameEngine l_gameEngine = new GameEngine();
		l_gameEngine.setPhase(new PreLoad(l_gameEngine));
		String[] l_newString1 = new String[] { "tournament", "-M", "uk.map", "risk.map", "-P", "Random", "Cheater",
				"-G", "2", "-D", "50" };
		assertEquals("{uk.map=[Cheater, Cheater], risk.map=[Cheater, Cheater]}",
				l_gameEngine.executeCommand(l_newString1));
	}
}
