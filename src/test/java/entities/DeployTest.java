package entities;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import controller.GameStarter;
import entities.orders.Deploy;

/**
 * Test to check that deploy command works perfectly
 *
 */
public class DeployTest {

	GameStarter d_game;
	
	/**
	 * Setup context for test to happen, object of game is created and player and countries are setup
	 */
	@Before
	public void setUp() {
		d_game = new GameStarter();
		d_game.addPlayer("Shubham");
		d_game.getGameMap().addContinent(1, 5);
		d_game.getGameMap().addCountry(1, 1);
		d_game.getPlayers().get("Shubham").addCountry(d_game.getGameMap().getCountries().get(1));
		d_game.getPlayers().get("Shubham").setNumberOfArmies();
	}
	
	/**
	 * Test where player armies are placed on country occupied by player 
	 */
	@Test
	public void testExecuteOrder1() {
		int l_country = 1;
		int l_armies = 2;
		Deploy l_Deploy = new Deploy(d_game.getPlayers().get("Shubham"), l_country, l_armies);
		assertEquals("Player \"Shubham\" deployed \"2\" armies to country \"1\"", l_Deploy.executeOrder(d_game));
	}
	
	/**
	 * Test where armies greater than player have are tried to placed on a country, rejected command with errror message
	 */
	@Test
	public void testExecuteOrder2() {
		int l_country = 1;
		int l_armies = 5;
		Deploy l_Deploy = new Deploy(d_game.getPlayers().get("Shubham"), l_country, l_armies);
		assertEquals("Player \"Shubham\" does not enough armies", l_Deploy.executeOrder(d_game));
	}
	
	/**
	 * Test where player tries to deploy armies to country which player doesn't possess, rejected command with error message
	 */
	@Test
	public void testExecuteOrder3() {
		int l_country = 4;
		int l_armies = 1;
		Deploy l_Deploy = new Deploy(d_game.getPlayers().get("Shubham"), l_country, l_armies);
		assertEquals("Player \"Shubham\" does not control country \"4\"", l_Deploy.executeOrder(d_game));
	}

}
