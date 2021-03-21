/**
 * 
 */
package entities.orders;

import controller.GameEngine;
import entities.Country;
import entities.Player;

/**
 * This class represents the blockade card.
 */
public class Blockade implements Orders {

	private Player d_player;
	private int d_country;

	/**
	 * @param p_player gets the object of Player class
	 * @param p_country gets the id of country
	 */
	public Blockade (Player p_player, int p_country){
		d_player = p_player;
		d_country = p_country;
	}

	/**
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		if(d_player.d_cardsOwned.get("blockade")>=1){
			if(d_player.getCountries().containsKey(d_country)){	
				int l_armiesPresence = d_player.getCountries().get(d_country).getNumberOfArmiesPresent();
				if(l_armiesPresence > 0){
					d_player.getCountries().get(d_country).setNumberOfArmiesPresent(l_armiesPresence*3);
					d_player.getCountries().get(d_country).setPlayer(p_game.d_neutralPlayer);
					int l_blockadeCardCount = d_player.d_cardsOwned.get("blockade");
					d_player.d_cardsOwned.replace("blockade", l_blockadeCardCount - 1);
					return "Blockade Card utilized successfully";
				} else {
					d_player.getCountries().get(d_country).setPlayer(p_game.d_neutralPlayer);
					int l_blockadeCardCount = d_player.d_cardsOwned.get("blockade");
					d_player.d_cardsOwned.replace("blockade", l_blockadeCardCount - 1);
					return "Army presence is Zero";
				}
			} else {
				return "Country referred is not your own country.";
			}
		} else {
			return "You don't have blockade card.";
		}
	}
}
