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
			l_randomOrder = d_gameEngine.d_random.nextInt(5-1) + 1;
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
					
					if(l_strongCountry.getNumberOfArmiesPresent()<2)continue;
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
					int l_randomNeighbor = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries()
							.get(l_playerCountries.get(l_strongCountry.getId())).getNeighborCountries().size());
					if (d_gameEngine.getGameMap().getCountries().get(l_playerCountries.get(l_randomNeighbor))
							.getNumberOfArmiesPresent() == 0) {
						l_randomArmies = d_gameEngine.d_random.nextInt(1);
					} else {
						l_randomArmies = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries()
								.get(l_playerCountries.get(l_randomNeighbor)).getNumberOfArmiesPresent());
					}
					
					l_order = new Advance(d_player, l_neighborCountries.get(l_randomNeighbor), l_strongCountry.getId() , l_randomArmies);
					System.out.println("Advance from: " + l_neighborCountries.get(l_randomNeighbor) + ", to: " + l_strongCountry.getId()  + ", armies:  " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Advance from: " + l_neighborCountries.get(l_randomNeighbor) + ", to: " + l_strongCountry.getId()  + ", armies:  " + l_randomArmies);
					l_isComplete = true;
					break;
					
				case 4:
					if(d_player.d_cardsOwned.get("bomb") < 1) {
					continue;
					}
					l_randomEnemyCountry = d_gameEngine.d_random.nextInt(l_neighborCountries.size());
					// l_randomEnemyCountry = l_randomEnemyCountry == 0 ? 1 : l_randomEnemyCountry;
					l_order = new Bomb(d_player, l_neighborCountries.get(l_randomEnemyCountry));
					System.out.println("Bomb " + l_neighborCountries.get(l_randomEnemyCountry));
					d_gameEngine.d_logEntryBuffer.setString("Bomb " + l_neighborCountries.get(l_randomEnemyCountry));
					break;
					
				case 5:
					if(d_player.d_cardsOwned.get("airlift") < 1) {
						continue;
					}
					l_strongCountry = d_player.getCountries().values().iterator().next();
					for(Country l_country: d_player.getCountries().values()) {
						if(l_strongCountry.getNumberOfArmiesPresent() < l_country.getNumberOfArmiesPresent() && d_player.getCountries().containsKey(l_country.getId())) {
							l_strongCountry = l_country;
						}
					}
					l_randomOwnCountry = d_gameEngine.d_random.nextInt(l_playerCountries.size());
					// l_randomOwnCountry = l_randomOwnCountry == 0 ? 1 : l_randomOwnCountry;
					l_randomArmies = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries()
							.get(l_playerCountries.get(l_randomOwnCountry)).getNumberOfArmiesPresent());
					l_order = new Airlift(d_player, l_playerCountries.get(l_randomOwnCountry), l_strongCountry.getId(),
							l_randomArmies);
					System.out.println("Airlift from: " + l_playerCountries.get(l_randomOwnCountry) + ", to:  "
							+ l_strongCountry.getId() + ", armies: " + l_randomArmies);
					d_gameEngine.d_logEntryBuffer.setString("Airlift from: " + l_playerCountries.get(l_randomOwnCountry)
							+ ", to:  " + l_strongCountry.getId() + ", armies: " + l_randomArmies);
					l_isComplete = true;
					break;
					
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
	
