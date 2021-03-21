package entities.orders;

import controller.GameEngine;
import entities.Country;
import entities.Player;

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
	public Bomb (Player p_player, int p_country){
		d_player = p_player;
		d_country = p_country;
	}

	/**
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		if(d_player.d_cardsOwned.get("bomb")>=1){
			if(!d_player.getCountries().containsKey(d_country)){	
				int l_armiesPresence = d_player.getCountries().get(d_country).getNumberOfArmiesPresent();
				if(l_armiesPresence > 0){
					d_player.getCountries().get(d_country).setNumberOfArmiesPresent(l_armiesPresence/2);
					int l_bombCardCount = d_player.d_cardsOwned.get("bomb");
					d_player.d_cardsOwned.replace("bomb", l_bombCardCount - 1);
					return "Bomb Card utilized successfully";
				} else {
					int l_bombCardCount = d_player.d_cardsOwned.get("bomb");
					d_player.d_cardsOwned.replace("bomb", l_bombCardCount - 1);
					return "Army presence is Zero";
				}
			} else {
				return "Country referred is your own country.";
			}
		} else {
			return "You don't have bomb card.";
		}
	}

}
