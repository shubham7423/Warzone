package strategy;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import entities.orders.Dummy;
import entities.orders.Orders;
import java.util.*;

/**
 * This class has the behavior of the Cheater Player. All the neighbor countries
 * are acquired by this Cheater Player and armies are doubled in those neighbor
 * countries that are recently conquered.
 */
public class Cheater extends PlayerStrategy {
	
	/**
	 * Constructor to Instantiate the members of Cheater Class. 
	 * @param p_player The properties of Cheater player to be given to this class.
	 * @param p_gameEngine Object of Game Engine.
	 */
	public Cheater(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
	}

	/**
	 * Logic for the Cheater Player, acquiring all the neighbor countries and doubling the armies. 
	 * return The dummy order is returned which which does not execute anything.
	 */
	@Override
	public Orders createOrder() {
		Collection<Country> l_playerOwnedCountriesBefore = d_player.getCountries().values();
		Collection<Integer> l_playerOwnedCountriesBeforeIds = d_player.getCountries().keySet();
		HashSet<Integer> l_playerOwnedCountriesAfter = new HashSet<>();
		for (Country l_currentCountry : l_playerOwnedCountriesBefore) {
			l_playerOwnedCountriesAfter.addAll(l_currentCountry.getNeighborIds());
		}

		for(Integer l_currentCountryIdInteger : l_playerOwnedCountriesAfter) {
			Country l_country = d_gameEngine.getGameMap().getCountries().get(l_currentCountryIdInteger);
			if(!l_playerOwnedCountriesBeforeIds.contains(l_country.getId())) {
				l_country.placeArmies(l_country.getNumberOfArmiesPresent());
				d_player.addCountry(l_country);
				l_country.getPlayer().removeCountry(l_country.getId());
				l_country.setPlayer(d_player);
			}
		}
		return new Dummy();
	}
}
