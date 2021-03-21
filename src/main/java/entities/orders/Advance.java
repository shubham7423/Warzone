package entities.orders;

import java.util.Set;

import controller.GameEngine;
import entities.GameMap;
import entities.Player;
import entities.Country;

/**
 * This class represents the advance command.
 */
public class Advance implements Orders {
	
//	private GameMap d_gameMap; 
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
//		d_gameMap = new GameMap();
	}
	/**
	 * Method to execute advance command
	 * 
	 * @param p_game gets the object of GameEngine class
	 * @return string
	 */
	@Override
	public String executeOrder(GameEngine p_game) {
		// TODO Auto-generated method stub
		if (p_game.getGameMap().getCountries().get(d_countryNameFrom) == null) {
			return String.format("Country \"%d\" does not exist", d_countryNameFrom);
		}
		else if (p_game.getGameMap().getCountries().get(d_countryNameTo) == null) {
			return String.format("Country \"%d\" does not exist", d_countryNameTo);
		}
		else {
			if (!d_player.getCountries().containsKey(d_countryNameFrom)) {
				return String.format("Player \"%s\" does not control country \"%d\" ,hence armies cannot be moved.", d_player.getName(), d_countryNameFrom);
			}
			if (d_player.getCountries().get(d_countryNameFrom).getNumberOfArmiesPresent() < d_armies) {
				return String.format("Country \"%d\" does not have enough armies", d_countryNameFrom);
			}
			
			/**p_game.getGameMap().getCountries().get(d_country).placeArmies(d_armies);
			d_player.removeArmies(d_armies);
			return String.format("Player \"%s\" deployed \"%d\" armies to country \"%d\"", d_player.getName(), d_armies,d_country); */
			
			if ((d_player.getCountries().get(d_countryNameFrom).getNumberOfArmiesPresent() - d_armies) < 1) {
				return String.format("Country \"%d\" should remain with atleast 1 armies after advancing command", d_countryNameFrom);
			}
			
			Set<Country> l_neighboringCountries = d_player.getCountries().get(d_countryNameFrom).getNeighborCountries();
			if(!(l_neighboringCountries.contains(d_countryNameTo))) {
				return String.format("Armies cannot be moved from country \"%d\" to country \"%d\" because they are not neighbors", d_countryNameFrom, d_countryNameTo);				
			}
			if (d_player.getCountries().containsKey(d_countryNameFrom) && d_player.getCountries().containsKey(d_countryNameTo)) {
//				int l_armiesPresent = d_player.getCountries().get(d_countryNameFrom).getNumberOfArmiesPresent();
				d_player.getCountries().get(d_countryNameFrom).removeArmies(d_armies);
				d_player.getCountries().get(d_countryNameTo).placeArmies(d_armies);
				return String.format("Armies successfully moved from country \"%d\" to country \"%d\"", d_countryNameFrom, d_countryNameTo);
			}
			
			int l_sourceCountryArmies = p_game.getGameMap().getCountries().get(d_countryNameFrom).getNumberOfArmiesPresent();
			int l_destinationCountryArmies = p_game.getGameMap().getCountries().get(d_countryNameTo).getNumberOfArmiesPresent();
			
			int l_capabilitySourceCountryArmies = (int)Math.ceil(d_armies*0.6);
			int l_capabilityDestinationCountryArmies = (int)Math.ceil(l_destinationCountryArmies*0.7);
			
			if (l_capabilitySourceCountryArmies > l_destinationCountryArmies) {
				
				p_game.getGameMap().getCountries().get(d_countryNameTo).setPlayer(d_player);
				d_player.getCountries().get(d_countryNameFrom).setNumberOfArmiesPresent(l_sourceCountryArmies - d_armies);
				d_player.getCountries().get(d_countryNameTo).setNumberOfArmiesPresent(d_armies - l_capabilityDestinationCountryArmies);
				return String.format("Armies successfully moved from country \"%d\" to country \"%d\" and the ownership changed to \"%s\" player", d_countryNameFrom, d_countryNameTo, d_player.getName());
			}
			
			if (l_capabilitySourceCountryArmies < l_destinationCountryArmies) {
//				int l_remainingArmies = l_sourceCountryArmies - d_armies;
//				d_player.getCountries().get(d_countryNameFrom).setNumberOfArmiesPresent(l_sourceCountryArmies - d_armies + (d_armies - l_capabilityDestinationCountryArmies));
//				d_player.getCountries().get(d_countryNameTo).setNumberOfArmiesPresent(l_destinationCountryArmies - l_capabilitySourceCountryArmies);
				d_player.getCountries().get(d_countryNameFrom).setNumberOfArmiesPresent(l_sourceCountryArmies - l_capabilityDestinationCountryArmies);
				d_player.getCountries().get(d_countryNameTo).removeArmies(l_capabilitySourceCountryArmies);
			}
//			int l_remainingSourceCountryArmies = l_sourceCountryArmies - l_capabilityDestinationCountryArmies;
//			int l_remainingDestinationCountryArmies = l_destinationCountryArmies - l_capabilitySourceCountryArmies;
			
				
		}
		
		return null;
	}

}
