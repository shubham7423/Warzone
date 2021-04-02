package strategy;

import controller.GameEngine;
import entities.Country;
import entities.Player;
import entities.orders.Orders;

public abstract class PlayerStrategy {
	
	Player d_player;
	GameEngine d_gameEngine;
	
	public PlayerStrategy(Player p_player, GameEngine p_gameEngine) {
		d_player = p_player;
		d_gameEngine = p_gameEngine;
	}
	
	public abstract Orders createOrder();
	public abstract Country toAttack(); 
	public abstract Country toAttackFrom(); 
	public abstract Country toMoveFrom(); 
	public abstract Country toDefend();
}
