package entities.orders;

import controller.GameEngine;
import entities.Player;

/**
 * Whenever blockade card is used, the player can triple the armies present in
 * one of the country they control and in return looses control over that
 * country, neutral player gets the control of the country.
 */
public class Blockade implements Orders {

	private Player d_player;
	private int d_country;

	/**
	 * Constructor to assign initial values
	 * 
	 * @param p_player  gets the object of Player class
	 * @param p_country gets the id of country
	 */
	public Blockade(Player p_player, int p_country) {
		d_player = p_player;
		d_country = p_country;
	}

	/**
	 * method to execute blockade command
	 * 
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		if (d_player.d_cardsOwned.get("blockade") >= 1) {
			if (d_player.getCountries().containsKey(d_country)) {
				int l_armiesPresent = d_player.getCountries().get(d_country).getNumberOfArmiesPresent();
				if (l_armiesPresent >= 0) {
					d_player.getCountries().get(d_country).setNumberOfArmiesPresent(l_armiesPresent * 3);
					//
					d_player.getCountries().get(d_country).setPlayer(p_game.d_neutralPlayer);
					p_game.d_neutralPlayer.addCountry(p_game.getGameMap().getCountries().get(d_country));
					d_player.removeCountry(d_country);
					//
					int l_blockadeCardCount = d_player.d_cardsOwned.get("blockade");
					d_player.d_cardsOwned.replace("blockade", l_blockadeCardCount - 1);
					return String.format("Blockade Card utilized successfully by player  \"%s\" on country  \"%d\".",
							d_player.getName(), d_country);
				} else {
					return "";
				}
			} else {
				return String.format("Player \"%s\" doesn't control country \"%d\".", d_player.getName(), d_country);
			}
		} else {
			return String.format("Player \"%s\" doesn't have blockade card.", d_player.getName());
		}
	}
}
