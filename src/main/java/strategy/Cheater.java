package strategy;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import entities.orders.Dummy;
import entities.orders.Orders;

public class Cheater extends PlayerStrategy{
		
	public Cheater(Player p_player, GameEngine p_gameEngine) {
		super(p_player, p_gameEngine);
	}

	@Override
	public Orders createOrder() {
		// TODO Auto-generated method stub
		return new Dummy();
	}
	

}
