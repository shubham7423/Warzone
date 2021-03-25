/**
 * 
 */
package entities.orders;

import static org.junit.Assert.*;

import java.util.HashMap;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.GameEngine;
import controller.state.gamephase.gamesetup.PostLoad;

/**
 * Test to check that advance command works perfectly
 */
public class AdvanceTest {

	GameEngine d_game;

	/**
	 * Setup context for test to happen, object of game is created and players,
	 * countries and neighbors are setup and armies are assigned
	 */
	@Before
	public void setUp() {
		d_game = new GameEngine();
		d_game.setPhase(new PostLoad(d_game));
		String[] l_newStrings = new String[] { "gameplayer", "-add", "Shubham", "-add", "Meet" };
		d_game.executeCommand(l_newStrings);
		d_game.getGameMap().addContinent(1, 5);
		d_game.getGameMap().addCountry(1, 1);
		d_game.getGameMap().addCountry(2, 1);
		d_game.getGameMap().addCountry(3, 1);
		d_game.getGameMap().addNeighbor(1, 2);
		d_game.getGameMap().addNeighbor(1, 3);
		d_game.getGameMap().getCountries().get(1).setPlayer(d_game.d_players.get("Shubham"));
		d_game.getGameMap().getCountries().get(2).setPlayer(d_game.d_players.get("Meet"));
		d_game.getGameMap().getCountries().get(3).setPlayer(d_game.d_players.get("Shubham"));
		d_game.d_players.get("Shubham").addCountry(d_game.getGameMap().getCountries().get(1));
		d_game.d_players.get("Shubham").addCountry(d_game.getGameMap().getCountries().get(3));
		d_game.d_players.get("Shubham").setNumberOfArmies();
		d_game.d_players.get("Meet").addCountry(d_game.getGameMap().getCountries().get(2));
		d_game.d_players.get("Meet").setNumberOfArmies();
	}

	/**
	 * This function resets the variables to null.
	 */
	@After
	public void tearDown() {
		d_game = null;
	}

	/**
	 * test where attacking armies do not defeat all the defending armies
	 */
	@Test
	public void testExecuteOrder1() {
		Deploy l_deploy1 = new Deploy(d_game.d_players.get("Shubham"), 1, 2);
		Deploy l_deploy2 = new Deploy(d_game.d_players.get("Meet"), 2, 3);
		l_deploy1.executeOrder(d_game);
		l_deploy2.executeOrder(d_game);

		Advance l_advanceCmd = new Advance(d_game.d_players.get("Shubham"), 1, 2, 1);
		assertEquals(
				"Armies from country \"1\" were not able to advance to country \"2\" as the attacking armies could not defeat all the armies present in the defending country",
				l_advanceCmd.executeOrder(d_game));
	}

	/**
	 * test where no. are cards are checked in the player's list of cards after the
	 * attack and correct owner is set when country is conquered and correct armies
	 * are present in source and destination countries.
	 */
	@Test
	public void testExecuteOrder2() {
		Deploy l_deploy1 = new Deploy(d_game.d_players.get("Shubham"), 1, 3);
		Deploy l_deploy2 = new Deploy(d_game.d_players.get("Meet"), 2, 1);
		l_deploy1.executeOrder(d_game);
		l_deploy2.executeOrder(d_game);

		Advance l_advanceCmd = new Advance(d_game.d_players.get("Shubham"), 1, 2, 2);
		HashMap<String, Integer> l_ordersBefore = new HashMap<String, Integer>();
		l_ordersBefore.putAll(d_game.d_players.get("Shubham").d_cardsOwned);

		l_advanceCmd.executeOrder(d_game);
		HashMap<String, Integer> l_ordersAfter = d_game.d_players.get("Shubham").d_cardsOwned;

		assertEquals("Shubham", d_game.getGameMap().getCountries().get(2).getPlayer().getName());
		assertEquals(1, d_game.getGameMap().getCountries().get(1).getNumberOfArmiesPresent());
		assertEquals(1, d_game.getGameMap().getCountries().get(2).getNumberOfArmiesPresent());
		assertFalse(l_ordersBefore.equals(l_ordersAfter));
	}

	/**
	 * test where exact no. of defending armies were defeated by the attackers and
	 * correct armies remain in the countries
	 */
	@Test
	public void testExecuteOrder3() {
		Deploy l_deploy1 = new Deploy(d_game.d_players.get("Shubham"), 1, 3);
		Deploy l_deploy2 = new Deploy(d_game.d_players.get("Meet"), 2, 2);
		Deploy l_deploy3 = new Deploy(d_game.d_players.get("Shubham"), 3, 0);
		l_deploy1.executeOrder(d_game);
		l_deploy2.executeOrder(d_game);
		l_deploy3.executeOrder(d_game);

		Advance l_advanceCmd = new Advance(d_game.d_players.get("Shubham"), 1, 2, 2);
		String l_result = l_advanceCmd.executeOrder(d_game);
		assertEquals(1, d_game.getGameMap().getCountries().get(1).getNumberOfArmiesPresent());
		assertEquals(0, d_game.getGameMap().getCountries().get(2).getNumberOfArmiesPresent());
		assertEquals(
				"Armies from country \"1\" were not able to advance to country \"2\" as the attacking armies were only able to defeat the exact number of armies present in the defending country",
				l_result);
	}

	/**
	 * test where advanced is used on a country that does not exist
	 */
	@Test
	public void testExecuteOrder4() {
		Deploy l_deploy1 = new Deploy(d_game.d_players.get("Shubham"), 1, 3);
		Deploy l_deploy2 = new Deploy(d_game.d_players.get("Meet"), 2, 1);
		l_deploy1.executeOrder(d_game);
		l_deploy2.executeOrder(d_game);

		Advance l_advanceCmd = new Advance(d_game.d_players.get("Shubham"), 1, 5, 1);
		assertEquals("Country \"5\" does not exist", l_advanceCmd.executeOrder(d_game));
	}

	/**
	 * test where advance is executed successfully
	 */
	@Test
	public void testExecuteOrder5() {
		Deploy l_deploy1 = new Deploy(d_game.d_players.get("Shubham"), 1, 3);
		Deploy l_deploy2 = new Deploy(d_game.d_players.get("Meet"), 2, 1);
		l_deploy1.executeOrder(d_game);
		l_deploy2.executeOrder(d_game);

		Advance l_advanceCmd = new Advance(d_game.d_players.get("Shubham"), 1, 3, 1);
		assertEquals("Armies successfully moved from country \"1\" to country \"3\"",
				l_advanceCmd.executeOrder(d_game));
	}
}
