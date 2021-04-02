package strategy;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import entities.orders.Orders;

public class Random extends PlayerStrategy{
	
	
	
	public Random(Player p_player,GameEngine p_gameEngine) {
		super(p_player,p_gameEngine);
	}

	public Orders createOrder() {
		
	}
	public Country toAttack() {
		
	}
	public Country toAttackFrom() {
		
	}
	public Country toMoveFrom() {
		
	}
	public Country toDefend() {
		
	}
}
