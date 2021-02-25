package entities;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;


import controller.UserCommand;
import entities.orders.Deploy;
import entities.orders.Orders;
import entities.orders.ShowMap;

/**
 * Player in a game
 *
 */
public class Player {
	private String d_name;
	private HashMap<Integer, Country> d_countries;
	private HashMap<Integer, Continent> d_continents;
	private Queue<Orders> d_orders;
	private int d_numberOfArmies;
	
	/**
	 * Constructor of player which sets initial values for player data
	 * @param p_name name of the player
	 */
	public Player(String p_name) {
		d_name = p_name;
		d_countries = new HashMap<>();
		d_continents = new HashMap<>();
		d_orders = new LinkedList<>();
		d_numberOfArmies = 0;
	}

	/**
	 * method to get name of player
	 * @return d_name name of player
	 */
	public String getName() {
		return d_name;
	}

	/**
	 * method to get the countries occupied by player
	 * @return d_countries HashMap of countries and their id occupied by player
	 */
	public HashMap<Integer, Country> getCountries() {
		return d_countries;
	}

	/**
	 * method to get the continents occupied by player
	 * @return d_continents HashMap of continents and their id occupied by player
	 */
	public HashMap<Integer, Continent> getContinents() {
		return d_continents;
	}
	
	/**
	 * method to get the number of armies player has
	 * @return d_numberOfArmies number of armies
	 */
	public int getNumberOfArmies() {
		return d_numberOfArmies;
	}
	
	/**
	 * method to remove armies 
	 * @param p_numberArmies number of armies to be removed
	 */
	public void removeArmies(int p_numberArmies) {
		d_numberOfArmies -= p_numberArmies;
	}
	
	/**
	 * method to add country occupied by player
	 * @param p_country Object of country
	 */
	public void addCountry(Country p_country) {
		d_countries.put(p_country.getId(), p_country);
	}
	
	/**
	 * method to remove country from player, when player looses the country
	 * @param p_countryId Id of the country
	 */
	public void removeCountry(int p_countryId) {
		d_countries.remove(p_countryId);
	}
	
	/**
	 * method to add continent to player when player wins all the countries in that continent
	 * @param p_continent object of the continent
	 */
	public void addContinent(Continent p_continent) {
		d_continents.put(p_continent.getId(), p_continent);
	}
	
	/**
	 * method to remove continent from player
	 * @param p_continentId Id of continent
	 */
	public void removeContinent(int p_continentId) {
		d_continents.remove(p_continentId);
	}
	
	/**
	 * method to set number of armies, which depends upon number of countries and continents occupied by player
	 */
	public void setNumberOfArmies() {
		d_numberOfArmies = d_countries.size() / 3;
		for(Continent l_continent: d_continents.values()) {
			d_numberOfArmies += l_continent.getControlValue();
		}
		if(d_numberOfArmies < 3) {
			d_numberOfArmies = 3;
		}
	}
	
	/**
	 * method to issue order called by Game engine
	 */
	public void issueOrder() {
		
		UserCommand l_userCommand = new UserCommand();
		String[] l_splittedOrder = null;
		boolean l_isCorrect = false;
		while(!l_isCorrect) {
			l_splittedOrder = l_userCommand.getCommand();
			if(l_splittedOrder[0].equals("deploy") && l_splittedOrder.length < 3) {
				System.out.println("Invalid command");
				continue;
			}
			else if(l_splittedOrder[0].equals("showmap") && l_splittedOrder.length > 1) {
				System.out.println("Invalid command");
				continue;
			}
			l_isCorrect = true;
		}
		if(l_splittedOrder[0].equals("deploy")) {
			Deploy l_deploy = new Deploy(this, Integer.parseInt(l_splittedOrder[1]),Integer.parseInt(l_splittedOrder[2]));
			d_orders.add(l_deploy);
		}
		else if (l_splittedOrder[0].equals("showmap")) {
			ShowMap l_ShowMap = new ShowMap();
			d_orders.add(l_ShowMap);
		}
	}
	
	/**
	 * method to check if player owns all countries of a continent
	 * @param p_continent continent for which ownership is to be checked
	 * @return true if player owns all the countries of continent; else false
	 */
	public boolean checkContinent(Continent p_continent) {
		if(d_countries.keySet().containsAll(p_continent.getCountriesIds())) {
			return true;
		}
		return false;
	}
	
	/**
	 * method to get next order from the orders queue
	 * @return order
	 */
	public Orders nextOrder() {
		return d_orders.remove();
	}
}
