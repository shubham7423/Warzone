package strategy;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import entities.orders.Orders;

/**
 * abstract class that is implemented by concrete strategy classes
 * This is the class that declares the method to be implemented by the various strategies.
 */
public abstract class PlayerStrategy {
	
	Player d_player;
	GameEngine d_gameEngine;
	
	/**
	 * constructor method for PlaerStrategy taking the name of he player and game engine object
	 * @param p_player the player's name whose strategy is to be set
	 * @param p_gameEngine object of GameEngine class
	 */
	public PlayerStrategy(Player p_player, GameEngine p_gameEngine) {
		d_player = p_player;
		d_gameEngine = p_gameEngine;
	}
	
	/**
	 * method that is implemented by concrete strategy classes
	 * @return order of a strategy type to be executed
	 */
	public abstract Orders createOrder();
}
