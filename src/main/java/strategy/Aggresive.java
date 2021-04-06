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
import entities.orders.Dummy;
import entities.orders.Orders;

public class Aggresive extends PlayerStrategy{

	public Aggresive(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
	}
	
	public Orders createOrder() {
		Orders l_order = null;
		int l_randomOrder;
		List<Integer> l_randCountries = new ArrayList<>(d_gameEngine.getGameMap().getCountries().keySet());
		List<Integer> l_playerCountries = new ArrayList<>(d_player.getCountries().keySet());
		List<Integer> l_neighborCountries = new ArrayList<>(getNeighborCountries());
		int l_randomArmies;
		int l_randomOwnCountry;
		int l_randomEnemyCountry;
		Country l_strongCountry;
		boolean l_isComplete = false;
		
		if(l_playerCountries.size() == 0) {
			return new Dummy();
		}
		
		while(!l_isComplete) {
			l_randomOrder = d_gameEngine.d_random.nextInt(3-1) + 1;
			switch (l_randomOrder) {
				case 1:
					l_strongCountry = d_player.getCountries().values().iterator().next();
					for(Country l_country: d_player.getCountries().values()) {
						if(l_strongCountry.getNumberOfArmiesPresent() < l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
							l_strongCountry = l_country;
						}
					}
					l_randomArmies = d_gameEngine.d_random.nextInt(d_player.getNumberOfArmies());
					l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
					l_order = new Deploy(d_player, l_strongCountry.getId() , l_randomArmies);
					System.out.println("Deploy " + l_strongCountry.getId() + ",armies:  " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Deploy " + l_strongCountry.getId() + ",armies:  " + l_randomArmies);
					l_isComplete = true;
					break;
		
				case 2:
					l_strongCountry = d_player.getCountries().values().iterator().next();
					for(Country l_country: d_player.getCountries().values()) {
						if(l_strongCountry.getNumberOfArmiesPresent() < l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
							l_strongCountry = l_country;
						}
					}
					l_randomEnemyCountry = d_gameEngine.d_random.nextInt(l_neighborCountries.size());
					l_randomArmies = d_gameEngine.d_random.nextInt(d_player.getNumberOfArmies());
					l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
					
					l_order = new Advance(d_player, l_strongCountry.getId(), l_neighborCountries.get(l_randomEnemyCountry) , l_randomArmies);
					System.out.println("Advance from: " + l_strongCountry.getId() + ", to: " + l_neighborCountries.get(l_randomEnemyCountry) + ", armies:  " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Advance from: " + l_strongCountry.getId() + ", to: " + l_neighborCountries.get(l_randomEnemyCountry) + ", armies:  " + l_randomArmies);
					l_isComplete = true;
					break;
					
				case 3:
					l_strongCountry = d_player.getCountries().values().iterator().next();
					for(Country l_country: d_player.getCountries().values()) {
						if(l_strongCountry.getNumberOfArmiesPresent() < l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
							l_strongCountry = l_country;
						}
					}
					l_randomEnemyCountry = d_gameEngine.d_random.nextInt(l_neighborCountries.size());
					l_randomArmies = d_gameEngine.d_random.nextInt(d_player.getNumberOfArmies());
					l_randomArmies = l_randomArmies == 0 ? 1 : l_randomArmies;
					
					l_order = new Advance(d_player, l_neighborCountries.get(l_randomEnemyCountry), l_strongCountry.getId() , l_randomArmies);
					System.out.println("Advance from: " + l_neighborCountries.get(l_randomEnemyCountry) + ", to: " + l_strongCountry.getId()  + ", armies:  " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Advance from: " + l_neighborCountries.get(l_randomEnemyCountry) + ", to: " + l_strongCountry.getId()  + ", armies:  " + l_randomArmies);
					l_isComplete = true;
					break;
					
				/*case 4:
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
					break;*/
					
				default:
					System.out.println("EXIT::" + l_randomOrder);
					break;
			}
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
	
