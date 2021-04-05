package strategy;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import entities.orders.Advance;
import entities.orders.Airlift;
import entities.orders.Bomb;
import entities.orders.Deploy;
import entities.orders.Orders;

public class Benevolent extends PlayerStrategy{

	public Benevolent(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
	}
	
	public Orders createOrder() {
		Orders l_order = null;
		int l_randomOrder = d_gameEngine.d_random.nextInt(5-1) + 1;
		List<Integer> l_randCountries = new ArrayList<>(d_gameEngine.getGameMap().getCountries().keySet());
		List<Integer> l_playerCountries = new ArrayList<>(d_player.getCountries().keySet());
		int l_randomArmies;
		int l_randomOwnCountry;
		Country l_weakCountry;
		switch (l_randomOrder) {
			case 1:
				l_weakCountry = d_player.getCountries().get(0);
				for(Country l_country: d_player.getCountries().values()) {
					if(l_weakCountry.getNumberOfArmiesPresent() < l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country)) {
						l_weakCountry = l_country;
					}
				}
				l_randomArmies = d_gameEngine.d_random.nextInt(d_player.getNumberOfArmies());
				l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
				l_order = new Deploy(d_player, l_weakCountry.getId() , l_randomArmies);
				System.out.println("Deploy " + l_weakCountry.getId() + ",armies:  " + l_randomArmies);
				d_gameEngine.d_logEntryBuffer.setString("Deploy " + l_weakCountry.getId() + ",armies:  " + l_randomArmies);
				break;
	
			case 2:
				Country l_strongNeighbour;
				l_weakCountry = d_player.getCountries().get(0);
				for(Country l_country: d_player.getCountries().values()) {
					if(l_weakCountry.getNumberOfArmiesPresent() < l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country)) {
						l_weakCountry = l_country;
					}
				}
				l_strongNeighbour = l_weakCountry.getNeighborCountries().iterator().next();
				for (Country l_country : l_weakCountry.getNeighborCountries()) {
					if(l_country.getNumberOfArmiesPresent() > l_strongNeighbour.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country)) {
					}
				}
				l_randomArmies = d_gameEngine.d_random.nextInt(d_player.getNumberOfArmies());
				l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
				//l_order = new Advance(d_player, l_weakCountry1.getId(), l_weakCountry1.getId() , l_randomArmies);
				//System.out.println("Deploy " + l_weakCountry.getId() + ",armies:  " + l_randomArmies);
				//d_gameEngine.d_logEntryBuffer.setString("Deploy " + l_weakCountry.getId() + ",armies:  " + l_randomArmies);
				break;
				
			case 3:
				
				//l_order = new Advance(d_player, l_playerCountries.get(l_randomOwnCountry), l_randomNeighborId, l_randomArmies);
				//System.out.println("Move from: " + l_playerCountries.get(l_randomOwnCountry) + ", to:  " + l_randomNeighborId + ", armies: " + l_randomArmies);
				//d_gameEngine.d_logEntryBuffer.setString("Move from: " + l_playerCountries.get(l_randomOwnCountry) + ", to:  " + l_randomNeighborId + ", armies: " + l_randomArmies);
				break;
			
			/*case 4:
				l_randomEnemyCountry = d_gameEngine.d_random.nextInt(l_neighborCountries.size());
//				l_randomEnemyCountry = l_randomEnemyCountry == 0 ? 1 : l_randomEnemyCountry;
				l_order = new Bomb(d_player, l_neighborCountries.get(l_randomEnemyCountry));
				System.out.println("Bomb " + l_neighborCountries.get(l_randomEnemyCountry));
				d_gameEngine.d_logEntryBuffer.setString("Bomb " + l_neighborCountries.get(l_randomEnemyCountry));
				break;
			
			case 5:
				l_randomOwnCountry = d_gameEngine.d_random.nextInt(l_playerCountries.size());
//				l_randomOwnCountry = l_randomOwnCountry == 0 ? 1 : l_randomOwnCountry;
				l_randomArmies = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries().get(l_playerCountries.get(l_randomOwnCountry)).getNumberOfArmiesPresent());
				int l_randomToCountryId = l_playerCountries.get(d_gameEngine.d_random.nextInt(l_playerCountries.size()));
				l_order = new Airlift(d_player, l_playerCountries.get(l_randomOwnCountry), l_randomToCountryId, l_randomArmies);
				System.out.println("Airlift from: " + l_playerCountries.get(l_randomOwnCountry) + ", to:  " + l_randomToCountryId + ", armies: " + l_randomArmies);
				d_gameEngine.d_logEntryBuffer.setString("Airlift from: " + l_playerCountries.get(l_randomOwnCountry) + ", to:  " + l_randomToCountryId + ", armies: " + l_randomArmies);
				break;*/
				
			default:
				System.out.println("EXIT::" + l_randomOrder);
				break;
		}
		return l_order;
	}
	
	public HashSet getNeighborCountries() {
		HashSet<Integer> l_neighbours = new HashSet<>();
		for(Country l_country: d_gameEngine.getGameMap().getCountries().values()) {
			l_neighbours.addAll(l_country.getNeighborIds());
		}
		return l_neighbours;
	}
}