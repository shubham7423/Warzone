/**
 * 
 */
package entities.orders;

import controller.GameEngine;
import entities.Player;

/**
 * This class represents the diplomacy card.
 */
public class Diplomacy implements Orders {

	private Player d_player;
	private Player d_otherPlayer;
	private int d_otherPlayerInt;
	
	public Diplomacy(Player p_player, int p_otherPlayer) {
		d_player = p_player;
		d_otherPlayerInt = p_otherPlayer;
	}
	
	/**
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		// TODO Auto-generated method stub
		
		return null;
	}

}
