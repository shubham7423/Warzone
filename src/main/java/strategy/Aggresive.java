package strategy;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import entities.orders.Orders;

public class Aggresive extends PlayerStrategy{

	
	
	public Aggresive(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
	}

	@Override
	public Orders createOrder() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
