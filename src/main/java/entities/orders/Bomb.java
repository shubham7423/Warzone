package entities.orders;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import java.lang.Math;

/**
 * This class represents the bomb card.
 */
public class Bomb implements Orders {

	private Player d_player;
	private Country d_country;

	/**
	 * @param p_player gets the object of Player class
	 */
	
	public Bomb (Player p_player, Country p_country)
	{
		d_player = p_player;
		d_country = p_country;
	}

	/**
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		
		int d_armies_presence = d_country.getNumberOfArmiesPresent();

		if(d_armies_presence > 0)
		{
			d_armies_presence = d_armies_presence/2;
			d_country.setNumberOfArmiesPresent(d_armies_presence);
		}



		return null;
	}

}
