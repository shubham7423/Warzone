package entities;

import controller.GameStarter;

/**
 * Class implementing Orders and is used to show map in game phase
 *
 */
public class ShowMap implements Orders {

	/**
	 * Execute showmap order
	 * @param p_game object calling this function
	 * @return map in string format
	 */
	@Override
	public String executeOrder(GameStarter p_game) {
		return p_game.showmap();
	}

}
