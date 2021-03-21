package entities.orders;

import java.util.Random;
import java.util.Set;

import controller.GameEngine;
import entities.Player;
import entities.Country;

/**
 * This class represents the advance command.
 */
public class Advance implements Orders {

	private Player d_player;
	private int d_countryNameFrom;
	private int d_countryNameTo;
	private int d_armies;

	/**
	 * Constructor to assign initial values
	 * 
	 * @param p_player          player who is firing advance command
	 * @param p_countryNameFrom country from which the reinforcements are to be
	 *                          taken
	 * @param p_countryNameTo   country to which reinforcements are to be placed
	 * @param p_armies          number of reinforcements to be placed
	 */
	public Advance(Player p_player, int p_countryNameFrom, int p_countryNameTo, int p_armies) {
		d_player = p_player;
		d_countryNameFrom = p_countryNameFrom;
		d_countryNameTo = p_countryNameTo;
		d_armies = p_armies;
	}

	/**
	 * Method to execute advance command
	 * 
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		if (p_game.getGameMap().getCountries().get(d_countryNameFrom) == null) {
			return String.format("Country \"%d\" does not exist", d_countryNameFrom);
		} else if (p_game.getGameMap().getCountries().get(d_countryNameTo) == null) {
			return String.format("Country \"%d\" does not exist", d_countryNameTo);
		} else {
			if (!d_player.getCountries().containsKey(d_countryNameFrom)) {
				return String.format("Player \"%s\" does not control country \"%d\" ,hence armies cannot be moved.",
						d_player.getName(), d_countryNameFrom);
			}
			if (d_player.getCountries().get(d_countryNameFrom).getNumberOfArmiesPresent() < d_armies) {
				return String.format("Country \"%d\" does not have enough armies", d_countryNameFrom);
			}

			if ((d_player.getCountries().get(d_countryNameFrom).getNumberOfArmiesPresent() - d_armies) < 1) {
				return String.format("Country \"%d\" should remain with atleast 1 armies after advancing command",
						d_countryNameFrom);
			}

			Set<Country> l_neighboringCountries = d_player.getCountries().get(d_countryNameFrom).getNeighborCountries();
			if (!(l_neighboringCountries.contains(d_countryNameTo))) {
				return String.format(
						"Armies cannot be moved from country \"%d\" to country \"%d\" because they are not neighbors",
						d_countryNameFrom, d_countryNameTo);

			}
			if (d_player.getCountries().containsKey(d_countryNameFrom)
					&& d_player.getCountries().containsKey(d_countryNameTo)) {
				d_player.getCountries().get(d_countryNameFrom).removeArmies(d_armies);
				d_player.getCountries().get(d_countryNameTo).placeArmies(d_armies);
				return String.format("Armies successfully moved from country \"%d\" to country \"%d\"",
						d_countryNameFrom, d_countryNameTo);
			}

			int l_sourceCountryArmies = p_game.getGameMap().getCountries().get(d_countryNameFrom)
					.getNumberOfArmiesPresent();
			int l_destinationCountryArmies = p_game.getGameMap().getCountries().get(d_countryNameTo)
					.getNumberOfArmiesPresent();

			int l_capabilitySourceCountryArmies = (int) Math.ceil(d_armies * 0.6);
			int l_capabilityDestinationCountryArmies = (int) Math.ceil(l_destinationCountryArmies * 0.7);

			if (l_capabilitySourceCountryArmies > l_destinationCountryArmies) {
				p_game.getGameMap().getCountries().get(d_countryNameTo).setPlayer(d_player);
				d_player.getCountries().get(d_countryNameFrom)
						.setNumberOfArmiesPresent(l_sourceCountryArmies - d_armies);
				d_player.getCountries().get(d_countryNameTo)
						.setNumberOfArmiesPresent(d_armies - l_capabilityDestinationCountryArmies);
				String[] l_cardNames = { "airlift", "bomb", "blockade", "diplomacy" };
				Random r = new Random();
				int l_cardNumber = r.nextInt(l_cardNames.length);
				d_player.d_cardsOwned.replace(l_cardNames[l_cardNumber],
						d_player.d_cardsOwned.get(l_cardNames[l_cardNumber]) + 1);
				return String.format(
						"Armies successfully moved from country \"%d\" to country \"%d\" and the ownership changed to \"%s\" player",
						d_countryNameFrom, d_countryNameTo, d_player.getName());
			} else if (l_capabilitySourceCountryArmies == l_destinationCountryArmies) {
				d_player.getCountries().get(d_countryNameFrom)
						.setNumberOfArmiesPresent(l_sourceCountryArmies - l_capabilityDestinationCountryArmies);
				d_player.getCountries().get(d_countryNameTo).setNumberOfArmiesPresent(0);
				return String.format(
						"Armies from country \"%d\" were not able to advance to country \"%d\" as the attacking armies were only able to defeat the exact number of armies present in the defending country",
						d_countryNameFrom, d_countryNameTo, d_player.getName());
			} else {
//			elif (l_capabilitySourceCountryArmies < l_destinationCountryArmies) {
				d_player.getCountries().get(d_countryNameFrom)
						.setNumberOfArmiesPresent(l_sourceCountryArmies - l_capabilityDestinationCountryArmies);
				d_player.getCountries().get(d_countryNameTo).removeArmies(l_capabilitySourceCountryArmies);
				return String.format(
						"Armies from country \"%d\" were not able to advance to country \"%d\" as the attacking armies could not defeat all the armies present in the defending country",
						d_countryNameFrom, d_countryNameTo, d_player.getName());
			}
		}
	}

}
