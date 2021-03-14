package entities.orders;

import controller.GameEngine;
import controller.GameStarter;

/**
 * Class for implementing Orders and is used to show map in game phase
 *
 */
public class ShowMap implements Orders {

	/**
	 * method to execute showMap order
	 * 
	 * @param p_game object calling this function
	 * @return map in string format
	 */
	@Override
	public String executeOrder(GameStarter p_game) {
		return p_game.getPhase().showMap();
	}

}
