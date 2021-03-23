	package entities.orders;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import controller.GameEngine;
import controller.state.gamephase.gamesetup.PostLoad;

public class BlockadeTest {
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
	
	@After
	public void tearDown() {
		d_game = null;
	}
	
	@Test
	public void testExecuteOrder1() {
		Blockade l_blockadeCmd = new Blockade(d_game.d_players.get("Shubham"), 1);
		assertEquals("Player \"Shubham\" doesn.t hace blockade card.", l_blockadeCmd.executeOrder(d_game));
	}
	
	@Test
	public void testExecuteOrder2() {
		d_game.d_players.get("Shubham").d_cardsOwned.put("blockade", 1);
		Blockade l_blockadeCmd = new Blockade(d_game.d_players.get("Shubham"), 2);
		assertEquals("Player \"Shubham\" doesn't control country \"2\".", l_blockadeCmd.executeOrder(d_game));
	}
	
	@Test
	public void testExecuteOrder3() {
		d_game.d_players.get("Shubham").d_cardsOwned.put("blockade", 1);
		Blockade l_blockadeCmd = new Blockade(d_game.d_players.get("Shubham"), 1);
		assertEquals("Blockade Card utilized successfully by player  \"Shubham\" on country  \"1\".", l_blockadeCmd.executeOrder(d_game));
	}
	
	@Test
	public void testExecuteOrder4() {
		d_game.d_players.get("Shubham").d_cardsOwned.put("blockade", 1);
		Blockade l_blockadeCmd = new Blockade(d_game.d_players.get("Shubham"), 1);
		d_game.d_players.get("Shubham").d_cardsOwned.put("blockade", 1);
		l_blockadeCmd.executeOrder(d_game);
		assertEquals("neutralPlayer#1", d_game.getGameMap().getCountries().get(1).getPlayer().getName());
	}
	
	@Test
	public void testExecuteOrder5() {
		d_game.getGameMap().addCountry(3, 1);
		d_game.getGameMap().getCountries().get(1).setPlayer(d_game.d_players.get("Shubham"));
		d_game.d_players.get("Shubham").addCountry(d_game.getGameMap().getCountries().get(3));
		d_game.d_players.get("Shubham").d_cardsOwned.put("blockade", 3);
		Blockade l_blockadeCmd = new Blockade(d_game.d_players.get("Shubham"), 3);
		assertEquals("Blockade card cannot be used on country \"3\", as number of armies are zero at country.", l_blockadeCmd.executeOrder(d_game));
	}
}