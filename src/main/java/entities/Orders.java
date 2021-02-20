package entities;

import controller.GameStarter;

/**
 * Interface implemented by all the order classes
 *
 */
public interface Orders {
	/**
	 * Executes the order  
	 * @param p_game GameEngine 
	 * @return Positive response if command was successful, otherwise negative reply 
	 */
	String executeOrder(GameStarter p_game);
}
