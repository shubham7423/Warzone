package entities.orders;

import controller.GameEngine;
import entities.GameMap;
import entities.Player;

/**
 * This class represents the advance command.
 */
public class Advance implements Orders {
	
	private GameMap d_gameMap; 
	private Player d_player;
	private int d_countryNameFrom;
	private int d_countryNameTo;
	private int d_armies;
	
	/**
	 * Constructor to assign initial values
	 * 
	 * @param p_player player who is firing advance command
	 * @param p_countryNameFrom country from which the reinforcements are to be taken
	 * @param p_countryNameTo country to which reinforcements are to be placed 
	 * @param p_armies number of reinforcements to be placed
	 */
	public Advance(Player p_player, int p_countryNameFrom, int p_countryNameTo, int p_armies) {
		d_player = p_player;
		d_countryNameFrom = p_countryNameFrom;
		d_countryNameTo = p_countryNameTo;
		d_armies = p_armies;
		d_gameMap = new GameMap();
	}
	/**
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		// TODO Auto-generated method stub
		if ((d_gameMap.getCountries().get(d_countryNameFrom) == null) || (d_gameMap.getCountries().get(d_countryNameTo) == null)) {
			return String.format("Country \"%d\" does not exist or country \"%d\" does not exist", d_countryNameFrom, d_countryNameTo);
		}
		else {
			if (!d_player.getCountries().containsKey(d_countryNameFrom)) {
				return String.format("Player \"%s\" does not control country \"%d\" ,hence armies cannot be moved.", d_player.getName(), d_countryNameFrom);
			}
		}
		
		return null;
	}

}
