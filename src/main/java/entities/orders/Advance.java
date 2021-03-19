package entities.orders;

import controller.GameEngine;
import entities.Player;

/**
 * This class represents the advance command.
 */
public class Advance implements Orders {
	
	private Player d_Player;
	private String d_countryNameFrom;
	private String d_countryNameTo;
	private int d_armies;
	
	/**
	 * Constructor to assign initial values
	 * 
	 * @param p_player player who is firing advance command
	 * @param p_countryNameFrom country from which the reinforcements are to be taken
	 * @param p_countryNameTo country to which reinforcements are to be placed 
	 * @param p_armies number of reinforcements to be placed
	 */
	public Advance(Player p_player, String p_countryNameFrom, String p_countryNameTo, int p_armies) {
		d_Player = p_player;
		d_countryNameFrom = p_countryNameFrom;
		d_countryNameTo = p_countryNameTo;
		d_armies = p_armies;
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
