package entities.orders;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.GameEngine;
import controller.state.gamephase.gamesetup.PostLoad;

/**
 * Test to check that bomb command works perfectly
 *
 */
public class BombTest {

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
		d_game.getGameMap().addNeighbor(1, 2);
		d_game.getGameMap().getCountries().get(1).setPlayer(d_game.d_players.get("Shubham"));
		d_game.getGameMap().getCountries().get(2).setPlayer(d_game.d_players.get("Meet"));
		d_game.d_players.get("Shubham").addCountry(d_game.getGameMap().getCountries().get(1));
		d_game.d_players.get("Shubham").setNumberOfArmies();
		d_game.d_players.get("Meet").addCountry(d_game.getGameMap().getCountries().get(2));
		d_game.d_players.get("Meet").setNumberOfArmies();
		
		Deploy l_deploy1 = new Deploy(d_game.d_players.get("Shubham"), 1, 2);
		Deploy l_deploy2 = new Deploy(d_game.d_players.get("Meet"), 2, 3);
		l_deploy1.executeOrder(d_game);
		l_deploy2.executeOrder(d_game);
	}
	
	/**
	 * This function resets the variables to null.
	 */
	@After
	public void tearDown() {
		d_game = null;
	}

	/**
	 * test where bomb card is used on one's own country
	 */
	@Test
	public void testExecuteOrder1() {
		d_game.d_players.get("Shubham").d_cardsOwned.put("bomb", 1);
		Bomb l_bombCmd = new Bomb(d_game.d_players.get("Shubham"), 1);
		assertEquals("Cannot bomb country \"1\" as it is controlled by player \"Shubham\".", l_bombCmd.executeOrder(d_game));
	}
	
	/**
	 * test where bomb card is used successfully
	 */
	@Test
	public void testExecuteOrder2() {
		d_game.d_players.get("Shubham").d_cardsOwned.put("bomb", 1);
		Bomb l_bombCmd = new Bomb(d_game.d_players.get("Shubham"), 2);
		assertEquals("Player \"Shubham\" bombed country \"2\" successfully.", l_bombCmd.executeOrder(d_game));
	}
	
	/**
	 * test where bomb card is used even when a player does not own one 
	 */
	@Test
	public void testExecuteOrder3() {
		Bomb l_bombCmd = new Bomb(d_game.d_players.get("Shubham"), 1);
		assertEquals("Player \"Shubham\" doesn't have bomb card.", l_bombCmd.executeOrder(d_game));
	}
	
	/**
	 * test where bomb card is used on a non neighboring country of the player
	 */
	@Test
	public void testExecuteOrder4() {
		d_game.d_players.get("Meet").d_cardsOwned.put("bomb", 1);
		Bomb l_bombCmd = new Bomb(d_game.d_players.get("Meet"), 1);
		assertEquals("The country \"1\" is not a neighbour country of the countries owned by player \"Meet\".", l_bombCmd.executeOrder(d_game));
	}
	
	/**
	 * test where bomb card is used while there exist diplomacy between 2 players
	 */
	@Test
	public void testExecuteOrder5() {
		d_game.d_players.get("Shubham").d_cardsOwned.put("bomb", 1);
		Bomb l_bombCmd = new Bomb(d_game.d_players.get("Shubham"), 2);
		d_game.d_players.get("Shubham").d_negotiatedPlayerNames.add("Meet");
		assertEquals("Cannot bomb, as diplomacy is established between \"Shubham\" and \"Meet\".", l_bombCmd.executeOrder(d_game));
	}
}
