/*package strategy;

import java.util.Random;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import entities.orders.Deploy;
import entities.orders.Orders;

public class Benevolent extends PlayerStrategy{

	public Benevolent(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
	}
	
	public Orders createOrder() {
		Random rand = new Random();
	    if (rand.nextInt(5) != 0) {
	      return new Deploy(d_player, toDefend(), rand.nextInt(20));
	    }
	    return null;
	  }

	public Country toAttack() {
		return null;
	}
	public Country toAttackFrom() {
		return null;
	}
	
	public Country toMoveFrom(int p_countryId) {
		return null;
	}
	public Country toDefend() {
		Country maxCountry = d_player.getCountries().get(0);
		for(Country c: d_player.getCountries()) {
			
		}
	}
}*/
