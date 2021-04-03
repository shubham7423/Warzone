package strategy;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import entities.orders.Advance;
import entities.orders.Airlift;
import entities.orders.Bomb;
import entities.orders.Deploy;
import entities.orders.Orders;

public class RandomPlayer extends PlayerStrategy{
	
	RandomPlayer d_random;
	
	public RandomPlayer(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
	}

	public Orders createOrder() {
		Orders l_order = null;
		int l_randomOrder = d_gameEngine.d_random.nextInt() % 5;
		List<Integer> l_randCountries = new ArrayList<>(d_gameEngine.getGameMap().getCountries().keySet());
		List<Integer> l_neighborCountries = new ArrayList<>(getNeighborCountries()); 
		int l_randomArmies;
		int l_randomOwnCountry;
		int l_randomEnemyCountry;
		switch (l_randomOrder) {
			case 1:
				
				l_randomOwnCountry = d_gameEngine.d_random.nextInt(l_randCountries.size());
				l_randomArmies = d_gameEngine.d_random.nextInt(d_player.getNumberOfArmies());
				l_order = new Deploy(d_player, l_randCountries.get(l_randomOwnCountry), l_randomArmies);
				break;
	
			case 2:
				l_randomOwnCountry = d_gameEngine.d_random.nextInt(l_randCountries.size());		
				l_randomEnemyCountry = d_gameEngine.d_random.nextInt(l_neighborCountries.size());
				l_randomArmies = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries().get(l_randCountries.get(l_randomOwnCountry)).getNumberOfArmiesPresent() - 1);
				l_order = new Advance(d_player, l_randCountries.get(l_randomOwnCountry), l_neighborCountries.get(l_randomEnemyCountry), l_randomArmies);
				break;
				
			case 3:
				l_randomOwnCountry = d_gameEngine.d_random.nextInt(l_randCountries.size());
				int l_randomNeighbor = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries().get(l_randomOwnCountry).getNeighborCountries().size());
				int l_randomNeighborId = new ArrayList<>(d_gameEngine.getGameMap().getCountries().get(l_randomOwnCountry).getNeighborIds()).get(l_randomNeighbor);
				l_randomArmies = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries().get(l_randCountries.get(l_randomOwnCountry)).getNumberOfArmiesPresent() - 1);
				l_order = new Advance(d_player, l_randCountries.get(l_randomOwnCountry), l_randomNeighborId, l_randomArmies);
				break;
			
			case 4:
				l_randomEnemyCountry = d_gameEngine.d_random.nextInt(l_neighborCountries.size());
				l_order = new Bomb(d_player, l_randomEnemyCountry);
				break;
			
			case 5:
				l_randomOwnCountry = d_gameEngine.d_random.nextInt(l_randCountries.size());
				l_randomArmies = d_gameEngine.d_random.nextInt(d_gameEngine.getGameMap().getCountries().get(l_randCountries.get(l_randomOwnCountry)).getNumberOfArmiesPresent() - 1);
				l_order = new Airlift(d_player, l_randCountries.get(l_randomOwnCountry), l_randCountries.get(d_gameEngine.d_random.nextInt(l_randCountries.size())), l_randomArmies);
				break;
				
			default:
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
