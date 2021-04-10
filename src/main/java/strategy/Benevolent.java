package strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import entities.orders.Advance;
import entities.orders.Airlift;
import entities.orders.Deploy;
import entities.orders.Dummy;
import entities.orders.Orders;

/**
 * concrete strategy class of Benevolent player.
 * A benevolent computer player strategy that focuses on protecting its weak countries (deploys on its weakest country, never attacks,
 * then moves its armies in order to reinforce its weaker country).
 */
public class Benevolent extends PlayerStrategy{

	/**
	 * constructor method for the class
	 * @param p_player name of the aggressive player
	 * @param p_gameEngine object of GameEngine class
	 */
	public Benevolent(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
	}
	
	/**
	 * this function creates orders keeping in mind how the aggressive player plays.
	 * it uses a switch case to create any random order(move armies, deploy armies, advance armies, cards)
	 * deploys on its weakest country
	 * never attacks
	 * moves its armies in order to reinforce its weaker country
	 */
	public Orders createOrder() {
		Orders l_order = null;
		int l_randomOrder;
		List<Integer> l_randCountries = new ArrayList<>(d_gameEngine.getGameMap().getCountries().keySet());
		List<Integer> l_playerCountries = new ArrayList<>(d_player.getCountries().keySet());
		int l_randomArmies;
		int l_randomOwnCountry;
		Country l_weakCountry;
		boolean l_isComplete = false;
		
		if(l_playerCountries.size() == 0) {
			return new Dummy();
		}
		
		while(!l_isComplete) {
			l_randomOrder = d_gameEngine.d_random.nextInt(3-1) + 1;
			switch (l_randomOrder) {
				case 1:
					l_weakCountry = d_player.getCountries().values().iterator().next();
					for(Country l_country: d_player.getCountries().values()) {
						if(l_weakCountry.getNumberOfArmiesPresent() > l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
							l_weakCountry = l_country;
						}
					}
					l_randomArmies = d_gameEngine.d_random.nextInt(d_player.getNumberOfArmies());
					l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
					l_order = new Deploy(d_player, l_weakCountry.getId() , l_randomArmies);
					System.out.println("Deploy " + l_weakCountry.getId() + ",armies:  " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Deploy " + l_weakCountry.getId() + ",armies:  " + l_randomArmies);
					l_isComplete = true;
					break;
		
				case 2:
					Country l_strongNeighbour = null;
					l_weakCountry = d_player.getCountries().values().iterator().next();
					for(Country l_country: d_player.getCountries().values()) {
						
						if(l_weakCountry.getNumberOfArmiesPresent() > l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
							l_weakCountry = l_country;
						}
					}
					
					
					for(Country l_country: d_player.getCountries().values()) {
						if(!l_country.equals(l_weakCountry) && (l_country.getNeighborIds().contains(l_weakCountry.getId()))) {
							if(l_strongNeighbour == null) {
								l_strongNeighbour = l_country;
							}
							else {
								if(l_country.getNumberOfArmiesPresent() > l_strongNeighbour.getNumberOfArmiesPresent()) {
									l_strongNeighbour = l_country;
								}
							}
						}
					}
					
					if(l_strongNeighbour == null || l_strongNeighbour.getNumberOfArmiesPresent() < 2) {
						continue;
					}
					l_randomArmies = d_gameEngine.d_random.nextInt(d_player.getNumberOfArmies());
					l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
					
					l_order = new Advance(d_player, l_strongNeighbour.getId(), l_weakCountry.getId() , l_randomArmies);
					System.out.println("Advance from: " + l_strongNeighbour.getId() + ", to: " + l_weakCountry.getId() + ", armies:  " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Advance from: " + l_strongNeighbour.getId() + ", to: " + l_weakCountry.getId() + ", armies:  " + l_randomArmies);
					l_isComplete = true;
					break;
					
				case 3:
					Country l_strongCountry = null;
					if(d_player.d_cardsOwned.get("airlift") < 1) {
						continue;
					}
					l_weakCountry = d_player.getCountries().get(0);
					for(Country l_country: d_player.getCountries().values()) {
						if(l_weakCountry.getNumberOfArmiesPresent() > l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
							l_weakCountry = l_country;
						}
					}
					for(Country l_country: d_player.getCountries().values()) {
						if(!l_country.equals(l_weakCountry)) {
							if(l_strongCountry == null) {
								l_strongCountry = l_country;
							}
							else {
								if(l_country.getNumberOfArmiesPresent() > l_strongCountry.getNumberOfArmiesPresent()) {
									l_strongCountry = l_country;
								}
							}
						}
					}
					if(l_strongCountry == null || l_strongCountry.getNumberOfArmiesPresent() < 2) {
						continue;
					}
					l_randomArmies = d_gameEngine.d_random.nextInt(d_player.getNumberOfArmies());
					l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
					l_order = new Airlift(d_player, l_strongCountry.getId(), l_weakCountry.getId() , l_randomArmies);
					System.out.println("Move from: " + l_strongCountry.getId() + ", to: " + l_weakCountry.getId() + ", armies:  " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Move from: " + l_strongCountry.getId() + ", to: " + l_weakCountry.getId() + ", armies:  " + l_randomArmies);
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
		for(Country l_country: d_gameEngine.getGameMap().getCountries().values()) {
			l_neighbours.addAll(l_country.getNeighborIds());
		}
		return l_neighbours;
	}
}