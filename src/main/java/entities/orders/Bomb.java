package entities.orders;

import controller.GameEngine;

/**
 * This class represents the bomb card.
 */
public class Bomb implements Orders {

	private Player d_player;

	public Bomb (Player p_player)
	{
		d_player = p_player;
	}

	/**
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		// TODO Auto-generated method stub
		return null;
	}

}
