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
	private int d_country;

	/**
	 * @param p_player gets the object of Player class
	 * @param p_country gets the id of country
	 */
	public Bomb (Player p_player, int p_country)
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

		int l_armiesPresence = p_game.getGameMap().getCountries().get(d_country).getNumberOfArmiesPresent();
		
		if(!d_player.getCountries().containsKey(p_game.getGameMap().getCountries().get(d_country).getId()))
		{			
			if(l_armiesPresence > 0)
			{
				l_armiesPresence = l_armiesPresence/2;
				p_game.getGameMap().getCountries().get(d_country).setNumberOfArmiesPresent(l_armiesPresence);
				return "Bomb Card utilized successfully";
			}
			else
				return "Army presence is Zero";
		}
		else
			return "Country referred is your own country.";
		
	}

}
