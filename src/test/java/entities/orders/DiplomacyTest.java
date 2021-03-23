package entities.orders;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.GameEngine;
import controller.state.gamephase.gamesetup.PostLoad;
public class DiplomacyTest {

GameEngine d_game;
	
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

		Deploy l_deploy1 = new Deploy(d_game.d_players.get("Shubham"), 1, 2);
		Deploy l_deploy2 = new Deploy(d_game.d_players.get("Meet"), 2, 3);
		l_deploy1.executeOrder(d_game);
		l_deploy2.executeOrder(d_game);
	}

	@After
	public void tearDown() {
		d_game = null;
	}

	@Test
	public void testExecuteOrder1() {
		d_game.d_players.get("Shubham").d_cardsOwned.put("diplomacy", 1);
		Diplomacy l_bombCmd = new Diplomacy(d_game.d_players.get("Shubham"), "Meet");
		assertEquals("Diplomacy between Players \"Shubham\" and \"Meet\" established successfully.", l_bombCmd.executeOrder(d_game));
	}
	
	@Test
	public void testExecuteOrder2() {
		d_game.d_players.get("Shubham").d_cardsOwned.put("diplomacy", 1);
		Diplomacy l_bombCmd = new Diplomacy(d_game.d_players.get("Shubham"), "Vandit");
		assertEquals("Player \"Vandit\" does not exist", l_bombCmd.executeOrder(d_game));
	}
	
	@Test
	public void testExecuteOrder3() {
		Diplomacy l_bombCmd = new Diplomacy(d_game.d_players.get("Shubham"), "Meet");
		assertEquals("Player \"Shubham\" does not have Diplomacy Card.", l_bombCmd.executeOrder(d_game));
	}
}
