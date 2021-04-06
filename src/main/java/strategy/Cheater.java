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
	public Cheater(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
	}

	/**
	 * Logic for the Cheater Player, acquiring all the neighbor countries and doubling the armies. 
	 * return The dummy order is returned which which does not execute anything.
	 */
	@Override
	public Orders createOrder() {
		// get all the neighbors of the cheater player
		Collection<Country> l_playerOwnedCountriesBefore = d_player.getCountries().values();
		HashSet<Integer> l_playerOwnedCountriesAfter = new HashSet<>();
		for (Country l_currentCountry : l_playerOwnedCountriesBefore) {
			l_playerOwnedCountriesAfter.addAll(l_currentCountry.getNeighborIds());
		}

		return new Dummy();
	}
}
