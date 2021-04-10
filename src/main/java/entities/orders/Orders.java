package entities.orders;

import controller.GameEngine;

/**
 * Interface implemented by all the order classes
 */
public interface Orders {
	/**
	 * Executes the order
	 * 
	 * @param p_game object of GameEngine class
	 * @return Positive response if command was successful, otherwise negative reply
	 */
	String executeOrder(GameEngine p_game);
	
	/**
	 * This function will give you the command format of Order.
	 * 
	 * @return Command of Order in String format.
	 */
	public String getOrder();
}
