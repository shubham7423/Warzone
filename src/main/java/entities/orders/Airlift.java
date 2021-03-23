/**
 * 
 */
package entities.orders;

import controller.GameEngine;
import entities.Player;

/**
 * This class represents the airlift card. The Airlift Card allows you to
 * transfer your armies long distances. Each time you play one, you can do a
 * single transfer from any of your territories to any other territory of yours.
 * Similar to a normal transfer, those armies can't do any other action that
 * turn.
 */
public class Airlift implements Orders {

	private Player d_player;
	private int d_countryNameFrom;
	private int d_countryNameTo;
	private int d_armies;

	/**
	 * Constructor for assigning the initial values that will be required during the
	 * execution of the commands
	 * 
	 * @param p_player          player who is firing airlift command
	 * @param p_countryNameFrom country from which the reinforcements are to be
	 *                          taken
	 * @param p_countryNameTo   country to which the reinforcements are to be placed
	 * @param p_armies          number of reinforcements to be moved
	 */
	public Airlift(Player p_player, int p_countryNameFrom, int p_countryNameTo, int p_armies) {
		d_player = p_player;
		d_countryNameFrom = p_countryNameFrom;
		d_countryNameTo = p_countryNameTo;
		d_armies = p_armies;
	}

	/**
	 * Method to execute the Airlift command
	 * 
	 * @param p_game Gets the object of GameEngine class
	 * @return string Result as per the operations performed
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		int l_airliftCardCount = d_player.d_cardsOwned.get("airlift");
		if (l_airliftCardCount == 0) {
			return String.format("Player \"%s\" does not have a airlift card", d_player.getName());
		}
		if (!d_player.getCountries().containsKey(d_countryNameFrom)) {
			return String.format("Player \"%s\" does not own country \"%d\"", d_player.getName(), d_countryNameFrom);
		}
		if (!d_player.getCountries().containsKey(d_countryNameTo)) {
			return String.format("Player \"%s\" does not own country \"%d\"", d_player.getName(), d_countryNameTo);
		}
		if (d_player.getCountries().get(d_countryNameFrom).getNumberOfArmiesPresent() < d_armies) {
			return String.format("Country \"%d\" does not have enough armies", d_countryNameFrom);
		}
		if ((d_player.getCountries().get(d_countryNameFrom).getNumberOfArmiesPresent() - d_armies) < 1) {
			//d_player.d_cardsOwned.replace("airlift", l_airliftCardCount - 1);
			return String.format(
					"Country \"%d\" should remain with atleast 1 armies after moving the armies during Airlift",
					d_countryNameFrom);
		}
		d_player.getCountries().get(d_countryNameFrom).removeArmies(d_armies);
		d_player.getCountries().get(d_countryNameTo).placeArmies(d_armies);

		d_player.d_cardsOwned.replace("airlift", l_airliftCardCount - 1);
		return String.format("Armies successfully moved from country \"%d\" to country \"%d\"", d_countryNameFrom,
				d_countryNameTo);
	}
}
