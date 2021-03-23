package entities.orders;

import java.util.HashSet;

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
				
				HashSet <Integer> intCountry = new HashSet<>();
				for(Country tempCountry: d_player.getCountries().values()) {
					intCountry.addAll( tempCountry.getNeighborIds());
				}
				if(!intCountry.contains(d_country)) {
					int l_bombCardCount = d_player.d_cardsOwned.get("bomb");
					d_player.d_cardsOwned.replace("bomb", l_bombCardCount - 1);
					return String.format("The country \"%d\" is not a neighbour country of the countries owned by player \"%s\".", d_country, d_player.getName());
				}
				if(d_player.d_negotiatedPlayerNames.contains(p_game.getGameMap().getCountries().get(d_country).getPlayer().getName())) {
					int l_bombCardCount = d_player.d_cardsOwned.get("bomb");
					d_player.d_cardsOwned.replace("bomb", l_bombCardCount - 1);
					return String.format("Cannot bomb, as diplomacy is established between \"%s\" and \"%s\".", d_player.getName(), p_game.getGameMap().getCountries().get(d_country).getPlayer().getName());
				}

				int l_armiesPresence = p_game.getGameMap().getCountries().get(d_country).getNumberOfArmiesPresent();
				if(l_armiesPresence > 0){
					p_game.getGameMap().getCountries().get(d_country).setNumberOfArmiesPresent(l_armiesPresence/2);
					int l_bombCardCount = d_player.d_cardsOwned.get("bomb");
					d_player.d_cardsOwned.replace("bomb", l_bombCardCount - 1);
					return String.format("Player \"%s\" bombed country \"%d\" successfully.", d_player.getName(), d_country);
				} else {
					int l_bombCardCount = d_player.d_cardsOwned.get("bomb");
					d_player.d_cardsOwned.replace("bomb", l_bombCardCount - 1);
					return String.format("Blockade card cannot be used on country \"%d\", as number of armies are zero at country.", d_country);
				}
			} else {
				return String.format("Cannot bomb country \"%d\" as it is controlled by player \"%s\".", d_country, d_player.getName());
			}
		} else {
			return String.format("Player \"%s\" doesn't have bomb card.", d_player.getName());
		}
	}

}
