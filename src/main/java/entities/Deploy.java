package entities;

import controller.GameStarter;

/**
 * Deploy order class
 *
 */
public class Deploy implements Orders {
	
	Player d_player;
	int d_country;
	int d_armies;
	
	/**
	 * Constructor to assign initial values
	 * @param p_player player who is firing deploy command
	 * @param p_country country to which reinforcements are to be placed
	 * @param p_armies number of reinforcements to be placed
	 */
	public Deploy(Player p_player, int p_country, int p_armies) {
		d_player = p_player;
		d_country = p_country;
		d_armies = p_armies;
	}

	
	/**
	 * Execute deploy command
	 * @param p_game object calling this function
	 * @return Positive response if command was successful, otherwise negative reply 
	 */
	@Override
	public String executeOrder(GameStarter p_game) {
		if(!d_player.getCountries().containsKey(d_country)) {
			return String.format("Player \"%s\" does not control country \"%d\"", d_player.getName(), d_country);
		}
		if(d_player.getNumberOfArmies() < d_armies) {
			return String.format("Player \"%s\" does not enough armies", d_player.getName());
		}
		p_game.getGameMap().getCountries().get(d_country).placeArmies(d_armies);
		d_player.removeArmies(d_armies);
		return String.format("Player \"%s\" deployed \"%d\" armies to country \"%d\"", d_player.getName(), d_armies, d_country);
	}

}
