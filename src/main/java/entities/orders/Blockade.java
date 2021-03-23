/**
 * 
 */
package entities.orders;

import controller.GameEngine;
import entities.Country;
import entities.Player;

/**
 * This class represents the blockade card. The blockade card change one of your territories to a neutral and multiply the number of armies on that territory by a predefined factor, usually increasing it. They're useful for creating a defensive blockade, among other uses. Since the armies on the territory you select are changing into neutral armies, you are essentially permanently losing control of the armies on that territory. Blockade cards are played after deployments but before any attacks happen. This means you can deploy armies to a territory in the same turn in which you blockade it to help the card have maximum effect.
 */
public class Blockade implements Orders {

	private Player d_player;
	private int d_country;

	/**
	 * @param p_player gets the object of Player class who uses the blockade card to avoid enemy attack on specified country.
	 * @param p_country gets the id of country on which blockade card will be used. Then after, it will have owner as 'Neutral Player'.
	 */
	public Blockade (Player p_player, int p_country){
		d_player = p_player;
		d_country = p_country;
	}

	/**
	 * @param p_game gets the object of GameEngine class for accessing Neutral player reference and to get details regarding country and player.
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		if(d_player.d_cardsOwned.get("blockade")>=1){
			if(d_player.getCountries().containsKey(d_country)){	
				int l_armiesPresence = d_player.getCountries().get(d_country).getNumberOfArmiesPresent();
				if(l_armiesPresence > 0){
					d_player.getCountries().get(d_country).setNumberOfArmiesPresent(l_armiesPresence*3);
					//
					d_player.getCountries().get(d_country).setPlayer(p_game.d_neutralPlayer);
					p_game.d_neutralPlayer.addCountry(p_game.getGameMap().getCountries().get(d_country));
					d_player.removeCountry(d_country);
					//
					int l_blockadeCardCount = d_player.d_cardsOwned.get("blockade");
					d_player.d_cardsOwned.replace("blockade", l_blockadeCardCount - 1);
					return String.format("Blockade Card utilized successfully by player  \"%s\" on country  \"%d\".", d_player.getName(), d_country);
				} else {
					p_game.d_neutralPlayer.addCountry(p_game.getGameMap().getCountries().get(d_country));
					d_player.removeCountry(d_country);
					p_game.getGameMap().getCountries().get(d_country).setPlayer(p_game.d_neutralPlayer);
					int l_blockadeCardCount = d_player.d_cardsOwned.get("blockade");
					d_player.d_cardsOwned.replace("blockade", l_blockadeCardCount - 1);
					return String.format("Blockade card cannot be used on country \"%d\", as number of armies are zero at country.", d_country);
				}
			} else {
				return String.format("Player \"%s\" doesn't control country \"%d\".", d_player.getName(), d_country);
			}
		} else {
			return String.format("Player \"%s\" doesn't have blockade card.", d_player.getName());
		}
	}
}
