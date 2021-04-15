package entities.orders;

import java.util.HashSet;

import controller.GameEngine;
import entities.Country;
import entities.Player;

/**
 * This class represents the bomb card. The Bomb Card allows you to target an
 * enemy or neutral territory and kill half of the armies on that territory. You
 * can target any territory that is adjacent to one of your own territories.
 * Since it kills half of the armies on a territory, it's most effective when
 * your opponent builds a single large stack of armies. Therefore, when your
 * opponent has a Bomb Card, you should try to keep your armies into separate
 * smaller stacks when possible to reduce its effectiveness. This can have a big
 * impact on a game's strategy!
 */
public class Bomb implements Orders {
	private Player d_player;
	private int d_country;

	/**
	 * Constructor to assign initial values
	 * 
	 * @param p_player  gets the object of Player class who uses the bomb card to
	 *                  attack on specified country.
	 * @param p_country gets the id of country on which bomb card will be used. Then
	 *                  after, it will have effect the no. of armies but not
	 *                  ownership.
	 */
	public Bomb(Player p_player, int p_country) {
		d_player = p_player;
		d_country = p_country;
	}

	/**
	 * This method is used to get the order in String format.
	 * 
	 * @return command in String form.
	 */
	public String getOrder() {
		return "bomb " + d_country;
	}

	/**
	 * method to execute bomb command
	 * 
	 * @param p_game gets the object of GameEngine class for accessing details
	 *               regarding country and their neighbors and player.
	 * @return This will provide us with the message after we execute Bomb Card.
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		if (d_player.d_cardsOwned.get("bomb") >= 1) {
			if (!d_player.getCountries().containsKey(d_country)) {
				HashSet<Integer> l_intCountry = new HashSet<>();
				for (Country l_tempCountry : d_player.getCountries().values()) {
					l_intCountry.addAll(l_tempCountry.getNeighborIds());
				}
				if (!l_intCountry.contains(d_country)) {
					return String.format(
							"The country \"%d\" is not a neighbour country of the countries owned by player \"%s\".",
							d_country, d_player.getName());
				}
				if (d_player.d_negotiatedPlayerNames
						.contains(p_game.getGameMap().getCountries().get(d_country).getPlayer().getName())) {
					return String.format("Cannot bomb, as diplomacy is established between \"%s\" and \"%s\".",
							d_player.getName(),
							p_game.getGameMap().getCountries().get(d_country).getPlayer().getName());
				}

				int l_armiesPresent = p_game.getGameMap().getCountries().get(d_country).getNumberOfArmiesPresent();
				if (l_armiesPresent >= 0) {
					p_game.getGameMap().getCountries().get(d_country).setNumberOfArmiesPresent(l_armiesPresent / 2);
					int l_bombCardCount = d_player.d_cardsOwned.get("bomb");
					d_player.d_cardsOwned.replace("bomb", l_bombCardCount - 1);
					return String.format("Player \"%s\" bombed country \"%d\" successfully.", d_player.getName(),
							d_country);
				} else {
					return "";
				}
			} else {
				return String.format("Cannot bomb country \"%d\" as it is controlled by player \"%s\".", d_country,
						d_player.getName());
			}
		} else {
			return String.format("Player \"%s\" doesn't have bomb card.", d_player.getName());
		}
	}
}
