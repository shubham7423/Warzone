package entities.orders;

import controller.GameEngine;
import entities.Country;
import entities.Player;

/**
 * This class represents the bomb card.
 */
public class Bomb implements Orders {

	private Player d_player;
	private Country d_country;

	/**
	 * @param p_player gets the object of Player class
	 */
	
	public Bomb (Player p_player)
	{
		d_player = p_player;
		d_country = d_country;
	}

	/**
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		



		return null;
	}

}
