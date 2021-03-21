/**
 * 
 */
package entities.orders;

import controller.GameEngine;
import entities.Player;

/**
 * This class represents the airlift card.
 */
public class Airlift implements Orders {

	private Player d_player;
	private int d_countryNameFrom;
	private int d_countryNameTo;
	private int d_armies;
	/**
	 * 
	 * @param p_player player who is firing advance command
	 * @param p_countryNameFrom country from which the reinforcements are to be taken
	 * @param p_countryNameTo country to which reinforcements are to be placed
	 * @param p_armies number of reinforcements to be placed
	 */
	public Airlift (Player p_player, int p_countryNameFrom, int p_countryNameTo, int p_armies) {
		d_player = p_player;
		d_countryNameFrom = p_countryNameFrom;
		d_countryNameTo = p_countryNameTo;
		d_armies = p_armies;
	}
	/**
	 * Method to execute Airlift card
	 * 
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		// TODO Auto-generated method stub
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
			return String.format("Country \"%d\" should remain with atleast 1 armies after moving the armies during Airlift", d_countryNameFrom);
		}
		d_player.getCountries().get(d_countryNameFrom).removeArmies(d_armies);
		d_player.getCountries().get(d_countryNameTo).placeArmies(d_armies);
		return String.format("Armies successfully moved from country \"%d\" to country \"%d\"", d_countryNameFrom, d_countryNameTo);
//		return null;
	}

}
