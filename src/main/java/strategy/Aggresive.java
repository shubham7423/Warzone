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
 * concrete strategy class of Aggressive player.
 * An aggressive computer player strategy that focuses on centralization of forces and then attack, i.e. it deploys on its strongest country,
 * then always attack with its strongest country, then moves its armies in order to maximize aggregation of forces in one country.
 */
public class Aggresive extends PlayerStrategy{

	/**
	 * constructor method for the class
	 * @param p_player name of the aggressive player
	 * @param p_gameEngine object of GameEngine class
	 */
	public Aggresive(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
	}
	
	/**
	 * this function creates orders keeping in mind how the aggressive player plays.
	 * it uses a switch case to create any random order(move armies, deploy armies, advance armies, cards)
	 * deploys on its strongest country
	 * attack with its strongest country
	 * moves its armies in order to maximize aggregation of forces in one country
	 */
	public Orders createOrder() {
		Orders l_order = null;
		int l_randomOrder;
		List<Integer> l_playerCountries = new ArrayList<>(d_player.getCountries().keySet());
		List<Integer> l_neighborCountries = new ArrayList<>(getNeighborCountries());
		int l_randomArmies;
		int l_randomOwnCountry;
		int l_randomEnemyCountry;
		Country l_strongCountryWEnyNei;
		boolean l_isComplete = false;
		
		if(l_playerCountries.size() == 0) {
			return new Dummy();
		}
		
		while(!l_isComplete) {
			l_randomOrder = d_gameEngine.d_random.nextInt(5-1) + 1;
			l_strongCountryWEnyNei = d_gameEngine.getGameMap().getCountries().get(l_playerCountries.get(0));
			if(getStrongNeighbors(l_strongCountryWEnyNei).size() == 0) {
				for(Country l_country: d_player.getCountries().values()) {
					if(getStrongNeighbors(l_country).size() != 0 ) {
						l_strongCountryWEnyNei = l_country;
						break;
					}
				}
			}
			
			for(Country l_country: d_player.getCountries().values()) {
				if(l_strongCountryWEnyNei.getNumberOfArmiesPresent() < l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
					if(getStrongNeighbors(l_country).size() != 0) {
						l_strongCountryWEnyNei = l_country;
					}
				}
			}
			System.out.println("Strongest: " + l_strongCountryWEnyNei.getId() +": "+ getStrongNeighbors(l_strongCountryWEnyNei));
			switch (l_randomOrder) {
				case 1:
					System.out.println("DEPLOY");
//					l_strongCountryWEnyNei = d_player.getCountries().values().iterator().next();
//					for(Country l_country: d_player.getCountries().values()) {
//						if(l_strongCountry.getNumberOfArmiesPresent() < l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
//							l_strongCountry = l_country;
//						}
//					}
//					l_randomArmies = d_gameEngine.d_random.nextInt(d_player.getNumberOfArmies());
//					l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
					l_randomArmies = d_player.getNumberOfArmies();
					l_order = new Deploy(d_player, l_strongCountryWEnyNei.getId() , l_randomArmies);
					System.out.println("Deploy " + l_strongCountryWEnyNei.getId() + ",armies:  " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Deploy " + l_strongCountryWEnyNei.getId() + ",armies:  " + l_randomArmies);
					l_isComplete = true;
					break;
		
				case 2:
					System.out.println("ADVANCE1");
//					l_strongCountry = d_player.getCountries().values().iterator().next();
//					for(Country l_country: d_player.getCountries().values()) {
//						if(l_strongCountry.getNumberOfArmiesPresent() < l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
//							if(getStrongNeighbors(l_country).size() != 0) {
//								l_strongCountry = l_country;
//							}
//						}
//					}
					
					if(l_strongCountryWEnyNei.getNumberOfArmiesPresent()<2) {
						continue;
					}
					
					List<Integer> l_strongNeighbors = getStrongNeighbors(l_strongCountryWEnyNei);	
					System.out.println(l_strongCountryWEnyNei.getId() + "; " + l_strongNeighbors);
					l_randomEnemyCountry = d_gameEngine.d_random.nextInt(l_strongNeighbors.size());
					
					l_randomArmies = d_gameEngine.d_random.nextInt(l_strongCountryWEnyNei.getNumberOfArmiesPresent());
					l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
					
					if(l_strongCountryWEnyNei.getNumberOfArmiesPresent() - l_randomArmies < 2) {
						continue;
					}
					if(d_gameEngine.getGameMap().getCountries().get(l_strongNeighbors.get(l_randomEnemyCountry)).getNumberOfArmiesPresent() > l_strongCountryWEnyNei.getNumberOfArmiesPresent()) {
						continue;
					}
					
					l_order = new Advance(d_player, l_strongCountryWEnyNei.getId(), l_strongNeighbors.get(l_randomEnemyCountry), l_randomArmies);
					System.out.println("Advance from: " + l_strongCountryWEnyNei.getId() + ", to: " + l_strongNeighbors.get(l_randomEnemyCountry) + ", armies:  " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Advance from: " + l_strongCountryWEnyNei.getId() + ", to: " + l_strongNeighbors.get(l_randomEnemyCountry) + ", armies:  " + l_randomArmies);
					l_isComplete = true;
					break;
					
				case 3:
					System.out.println("ADVANCE2");
					
					if(l_strongCountryWEnyNei.getNumberOfArmiesPresent()<2) {
						continue;
					}
					
					Country l_strongCountry;
					l_strongCountry = d_player.getCountries().values().iterator().next();
					for(Country l_country: d_player.getCountries().values()) {
						if(l_strongCountry.getNumberOfArmiesPresent() < l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
							l_strongCountry = l_country;
						}
					}
					
					if(l_strongCountry.equals(l_strongCountryWEnyNei) || l_strongCountry.getNumberOfArmiesPresent() < 2) {
						continue;
					}
					
//					int l_randomNeighbor = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries()
//							.get(l_playerCountries.get(l_strongCountry.getId())).getNeighborCountries().size());
//					if (d_gameEngine.getGameMap().getCountries().get(l_playerCountries.get(l_randomNeighbor))
//							.getNumberOfArmiesPresent() == 0) {
//						l_randomArmies = d_gameEngine.d_random.nextInt(1);
//					} else {
//						l_randomArmies = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries()
//								.get(l_playerCountries.get(l_randomNeighbor)).getNumberOfArmiesPresent());
//					}
					l_randomArmies = d_gameEngine.d_random.nextInt(l_strongCountry.getNumberOfArmiesPresent());
					l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
					
					l_order = new Advance(d_player, l_strongCountry.getId(), l_strongCountryWEnyNei.getId() , l_randomArmies);
					System.out.println("Advance from: " + l_strongCountry.getId() + ", to: " + l_strongCountryWEnyNei.getId()  + ", armies:  " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Advance from: " + l_strongCountry.getId() + ", to: " + l_strongCountryWEnyNei.getId()  + ", armies:  " + l_randomArmies);
					l_isComplete = true;
					break;
					
				case 4:
					System.out.println("BOMB");
					if(d_player.d_cardsOwned.get("bomb") < 1) {
						continue;
					}
					l_randomEnemyCountry = d_gameEngine.d_random.nextInt(l_neighborCountries.size());
					// l_randomEnemyCountry = l_randomEnemyCountry == 0 ? 1 : l_randomEnemyCountry;
					l_order = new Bomb(d_player, l_neighborCountries.get(l_randomEnemyCountry));
					System.out.println("Bomb " + l_neighborCountries.get(l_randomEnemyCountry));
					d_gameEngine.d_logEntryBuffer.setString("Bomb " + l_neighborCountries.get(l_randomEnemyCountry));
					l_isComplete = true;
					break;
					
				case 5:
					System.out.println("AIRLIFT");
					if(d_player.d_cardsOwned.get("airlift") < 1) {
						continue;
					}
//					l_strongCountry = d_player.getCountries().values().iterator().next();
//					for(Country l_country: d_player.getCountries().values()) {
//						if(l_strongCountry.getNumberOfArmiesPresent() < l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
//							l_strongCountry = l_country;
//						}
//					}
					l_randomOwnCountry = d_gameEngine.d_random.nextInt(l_playerCountries.size());
					// l_randomOwnCountry = l_randomOwnCountry == 0 ? 1 : l_randomOwnCountry;
					l_randomArmies = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries()
							.get(l_playerCountries.get(l_randomOwnCountry)).getNumberOfArmiesPresent());
					l_order = new Airlift(d_player, l_playerCountries.get(l_randomOwnCountry), l_strongCountryWEnyNei.getId(),
							l_randomArmies);
					System.out.println("Airlift from: " + l_playerCountries.get(l_randomOwnCountry) + ", to:  "
							+ l_strongCountryWEnyNei.getId() + ", armies: " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Airlift from: " + l_playerCountries.get(l_randomOwnCountry)
							+ ", to:  " + l_strongCountryWEnyNei.getId() + ", armies: " + l_randomArmies);
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
	
	/**
	 * function to get all the strong neighboring countries of the strongest country the player holds
	 * @param l_strongCountry the strongest country of the player
	 * @return all the strong neighboring countries
	 */
	public List<Integer> getStrongNeighbors(Country l_strongCountry) {
		List<Integer> l_strongNeighbors = new ArrayList<>();
		for(Country l_country: l_strongCountry.getNeighborCountries()) {
			if(!d_player.equals(l_country.getPlayer())) {
				l_strongNeighbors.add(l_country.getId());
			}
		}
		return l_strongNeighbors;
	}
}
	
