package entities.orders;

import java.util.Random;
import java.util.Set;

import controller.GameEngine;
import entities.Player;

/**
 * This class represents the advance command. The attack system is very simple.
 * Each army that attacks has a 60% chance at killing one defending army. If all
 * of the defending armies are killed, the territory is captured and all the
 * attacking armies move to occupy the destination territory.
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
	 * This method is used to get the order in String format.
	 * 
	 * @return command in String form.
	 */
	public String getOrder() {
		return "advance " + d_countryNameFrom + " " + d_countryNameTo + " " + d_armies;
	}

	/**
	 * Method to execute advance command
	 * 
	 * @param p_game gets the object of GameEngine class
	 * @return string according to the executed order
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

			Set<Integer> l_neighboringCountries = d_player.getCountries().get(d_countryNameFrom).getNeighborIds();
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

			if (d_player.d_negotiatedPlayerNames
					.contains(p_game.getGameMap().getCountries().get(d_countryNameTo).getPlayer().getName())) {

				return String.format(
						"Armies cannot be moved to country \"%d\" as there is diplomacy established between the calling and the called player",
						d_countryNameTo);
			}

			int l_sourceCountryArmies = p_game.getGameMap().getCountries().get(d_countryNameFrom)
					.getNumberOfArmiesPresent();
			int l_destinationCountryArmies = p_game.getGameMap().getCountries().get(d_countryNameTo)
					.getNumberOfArmiesPresent();

			int l_capabilitySourceCountryArmies = (int) Math.ceil(d_armies * 0.6);
			int l_capabilityDestinationCountryArmies = (int) Math.ceil(l_destinationCountryArmies * 0.7);

			if (l_capabilitySourceCountryArmies > l_destinationCountryArmies) {
				Player l_playerBeingAttacked = p_game.getGameMap().getCountries().get(d_countryNameTo).getPlayer();

				p_game.getGameMap().getCountries().get(d_countryNameTo).setPlayer(d_player);
				// adding the country to the players list of countries
				d_player.addCountry(p_game.getGameMap().getCountries().get(d_countryNameTo));

				l_playerBeingAttacked.getCountries().remove(d_countryNameTo);
				d_player.getCountries().get(d_countryNameFrom)
						.setNumberOfArmiesPresent(l_sourceCountryArmies - d_armies);
				d_player.getCountries().get(d_countryNameTo)
						.setNumberOfArmiesPresent(d_armies - l_capabilityDestinationCountryArmies);
				String[] l_cardNames = { "airlift", "bomb", "blockade", "diplomacy" };

				if (!d_player.d_isConquered) {
					int l_cardNumber = p_game.d_random.nextInt(l_cardNames.length);
					d_player.d_cardsOwned.replace(l_cardNames[l_cardNumber],
							d_player.d_cardsOwned.get(l_cardNames[l_cardNumber]) + 1);
					d_player.d_isConquered = true;
					return String.format(
							"Armies successfully moved from country \"%d\" to country \"%d\" and the ownership changed to \"%s\" player, Card \"%s\" rewarded to player \"%s\".",
							d_countryNameFrom, d_countryNameTo, d_player.getName(), l_cardNames[l_cardNumber],
							d_player.getName());
				}
				return String.format(
						"Armies successfully moved from country \"%d\" to country \"%d\" and the ownership changed to \"%s\" player.",
						d_countryNameFrom, d_countryNameTo, d_player.getName());

			} else if (l_capabilitySourceCountryArmies == l_destinationCountryArmies) {
				d_player.getCountries().get(d_countryNameFrom)
						.setNumberOfArmiesPresent(l_sourceCountryArmies - l_capabilityDestinationCountryArmies);
				p_game.getGameMap().getCountries().get(d_countryNameTo).setNumberOfArmiesPresent(0);
				return String.format(
						"Armies from country \"%d\" were not able to advance to country \"%d\" as the attacking armies were only able to defeat the exact number of armies present in the defending country",
						d_countryNameFrom, d_countryNameTo, d_player.getName());
			} else {
				d_player.getCountries().get(d_countryNameFrom)
						.setNumberOfArmiesPresent(l_sourceCountryArmies - d_armies);
				p_game.getGameMap().getCountries().get(d_countryNameTo).removeArmies(l_capabilitySourceCountryArmies);
				return String.format(
						"Armies from country \"%d\" were not able to advance to country \"%d\" as the attacking armies could not defeat all the armies present in the defending country",
						d_countryNameFrom, d_countryNameTo, d_player.getName());
			}
		}
	}
}
