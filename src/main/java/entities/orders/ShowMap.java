package entities.orders;

import controller.GameStarter;

/**
 * Class for implementing Orders and is used to show map in game phase
 *
 */
public class ShowMap implements Orders {

	/**
	 * method to execute showmap order
	 * @param p_game object calling this function
	 * @return map in string format
	 */
	@Override
	public String executeOrder(GameStarter p_game) {
		return p_game.showmap();
	}

}
