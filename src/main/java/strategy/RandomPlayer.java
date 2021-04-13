package strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import entities.orders.Advance;
import entities.orders.Airlift;
import entities.orders.Bomb;
import entities.orders.Deploy;
import entities.orders.Dummy;
import entities.orders.Orders;

/**
 * concrete strategy class of Random player.
 * A random computer player strategy that deploys on a random country, attacks random neighboring countries,
 * and moves armies randomly between its countries.
 */
public class RandomPlayer extends PlayerStrategy {

	RandomPlayer d_random;

	/**
	 * constructor method for the class
	 * @param p_player name of the aggressive player
	 * @param p_gameEngine object of GameEngine class
	 */
	public RandomPlayer(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
	}

	/**
	 * this function creates orders keeping in mind how the aggressive player plays.
	 * it uses a switch case to create any random order(move armies, deploy armies, advance armies, cards)
	 * deploys on a random country
	 * attacks random neighboring countries
	 * moves armies randomly between its countries
	 */
	@Override
	public Orders createOrder() {
		Orders l_order = null;
		int l_randomOrder;
		List<Integer> l_neighborCountries = new ArrayList<>(getNeighborCountries());
		List<Integer> l_playerCountries = new ArrayList<>(d_player.getCountries().keySet());
		int l_randomArmies;
		int l_randomOwnCountry;
		int l_randomEnemyCountry;
		boolean l_isComplete = false;
		
		if(l_playerCountries.size() == 0) {
			return new Dummy();
		}
		
		
		while(!l_isComplete) {
			l_randomOrder = d_gameEngine.d_random.nextInt(5 - 1) + 1;
			switch (l_randomOrder) {
			case 1:	
				l_randomOwnCountry = d_gameEngine.d_random.nextInt(l_playerCountries.size());
				
				l_randomArmies = d_gameEngine.d_random.nextInt(d_player.getNumberOfArmies());
				l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
				l_order = new Deploy(d_player, l_playerCountries.get(l_randomOwnCountry), l_randomArmies);
				System.out.println("Deploy " + l_playerCountries.get(l_randomOwnCountry) + ",armies:  " + l_randomArmies);
				d_gameEngine.d_logEntryBuffer
						.setString("Deploy " + l_playerCountries.get(l_randomOwnCountry) + ",armies:  " + l_randomArmies);
				l_isComplete = true;
				break;
	
			case 2:
				l_randomOwnCountry = d_gameEngine.d_random.nextInt(l_playerCountries.size());
				l_randomEnemyCountry = d_gameEngine.d_random.nextInt(l_neighborCountries.size());
		 		if (d_gameEngine.getGameMap().getCountries().get(l_playerCountries.get(l_randomOwnCountry))
						.getNumberOfArmiesPresent() <= 1) {
					l_randomArmies = d_gameEngine.d_random.nextInt(1);
				} else {
					l_randomArmies = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries()
							.get(l_playerCountries.get(l_randomOwnCountry)).getNumberOfArmiesPresent() - 1);
				}
				l_order = new Advance(d_player, l_playerCountries.get(l_randomOwnCountry),
						l_neighborCountries.get(l_randomEnemyCountry), l_randomArmies);
				System.out.println("Attack from: " + l_playerCountries.get(l_randomOwnCountry) + ", to:  "
						+ l_neighborCountries.get(l_randomEnemyCountry) + ", armies: " + l_randomArmies);
				d_gameEngine.d_logEntryBuffer.setString("Attack from: " + l_playerCountries.get(l_randomOwnCountry)
						+ ", to:  " + l_neighborCountries.get(l_randomEnemyCountry) + ", armies: " + l_randomArmies);
				l_isComplete = true;
				break;
	
			case 3:
				l_randomOwnCountry = d_gameEngine.d_random.nextInt(l_playerCountries.size());
				System.out.println(l_randomOwnCountry);
				System.out.println("MOVE: "
						+ d_gameEngine.getGameMap().getCountries().get(l_playerCountries.get(l_randomOwnCountry)).getId());
				int l_randomNeighbor = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries()
						.get(l_playerCountries.get(l_randomOwnCountry)).getNeighborCountries().size());
				int l_randomNeighborId = new ArrayList<>(d_gameEngine.getGameMap().getCountries()
						.get(l_playerCountries.get(l_randomOwnCountry)).getNeighborIds()).get(l_randomNeighbor);
				if (d_gameEngine.getGameMap().getCountries().get(l_playerCountries.get(l_randomOwnCountry))
						.getNumberOfArmiesPresent() == 0) {
					l_randomArmies = d_gameEngine.d_random.nextInt(1);
				} else {
					l_randomArmies = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries()
							.get(l_playerCountries.get(l_randomOwnCountry)).getNumberOfArmiesPresent());
				}
				l_order = new Advance(d_player, l_playerCountries.get(l_randomOwnCountry), l_randomNeighborId,
						l_randomArmies);
				System.out.println("Move from: " + l_playerCountries.get(l_randomOwnCountry) + ", to:  "
						+ l_randomNeighborId + ", armies: " + l_randomArmies);
				d_gameEngine.d_logEntryBuffer.setString("Move from: " + l_playerCountries.get(l_randomOwnCountry)
						+ ", to:  " + l_randomNeighborId + ", armies: " + l_randomArmies);
				l_isComplete = true;
				break;
	
			case 4:
				if(d_player.d_cardsOwned.get("bomb") < 1) {
					continue;
				}
				l_randomEnemyCountry = d_gameEngine.d_random.nextInt(l_neighborCountries.size());
				l_order = new Bomb(d_player, l_neighborCountries.get(l_randomEnemyCountry));
				System.out.println("Bomb " + l_neighborCountries.get(l_randomEnemyCountry));
				d_gameEngine.d_logEntryBuffer.setString("Bomb " + l_neighborCountries.get(l_randomEnemyCountry));
				break;
	
			case 5:
				if(d_player.d_cardsOwned.get("airlift") < 1) {
					continue;
				}
				l_randomOwnCountry = d_gameEngine.d_random.nextInt(l_playerCountries.size());
				l_randomArmies = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries()
						.get(l_playerCountries.get(l_randomOwnCountry)).getNumberOfArmiesPresent());
				int l_randomToCountryId = l_playerCountries.get(d_gameEngine.d_random.nextInt(l_playerCountries.size()));
				l_order = new Airlift(d_player, l_playerCountries.get(l_randomOwnCountry), l_randomToCountryId,
						l_randomArmies);
				System.out.println("Airlift from: " + l_playerCountries.get(l_randomOwnCountry) + ", to:  "
						+ l_randomToCountryId + ", armies: " + l_randomArmies);
				d_gameEngine.d_logEntryBuffer.setString("Airlift from: " + l_playerCountries.get(l_randomOwnCountry)
						+ ", to:  " + l_randomToCountryId + ", armies: " + l_randomArmies);
				l_isComplete = true;
				break;
	
			default:
				System.out.println("EXIT::" + l_randomOrder);
				break;
			}
		}
		return l_order;

	}

	/**
	 * function to get neighboring countries of any country
	 * @return the neighbors of the respective countries
	 */
	public HashSet getNeighborCountries() {
		HashSet<Integer> l_neighbours = new HashSet<>();
		for (Country l_country : d_gameEngine.getGameMap().getCountries().values()) {
			l_neighbours.addAll(l_country.getNeighborIds());
		}
		return l_neighbours;
	}
}
