package entities;

import java.util.HashMap;

/**
 * Player in a game
 *
 */
public class Player {
	private String d_name;
	private HashMap<Integer, Country> d_countries;
	private HashMap<Integer, Continent> d_continents;
	private int d_numberOfArmies;
	
	/**
	 * Constructor of player which sets initial values for player data
	 * @param p_name name of the player
	 */
	public Player(String p_name) {
		d_name = p_name;
		d_countries = new HashMap<>();
		d_continents = new HashMap<>();
		d_numberOfArmies = 0;
	}

	/**
	 * Get name of player
	 * @return name of player
	 */
	public String getName() {
		return d_name;
	}

	/**
	 * Get the countries occupied by player
	 * @return HashMap of countries and their id occupied by player
	 */
	public HashMap<Integer, Country> getCountries() {
		return d_countries;
	}

	/**
	 * Get the continents occupied by player
	 * @return HashMap of continents and their id occupied by player
	 */
	public HashMap<Integer, Continent> getContinents() {
		return d_continents;
	}
	
	/**
	 * Get the number of armies player has
	 * @return number of armies
	 */
	public int getNumberOfArmies() {
		return d_numberOfArmies;
	}
	
	/**
	 * Remove armies 
	 * @param p_numberArmies number of armies to be removed
	 */
	public void removeArmies(int p_numberArmies) {
		d_numberOfArmies -= p_numberArmies;
	}
	
	/**
	 * Add country occupied by player
	 * @param p_country Object of country
	 */
	public void addCountry(Country p_country) {
		d_countries.put(p_country.getId(), p_country);
	}
	
	/**
	 * Remove country from player, when player looses the country
	 * @param p_countryId Id of the country
	 */
	public void removeCountry(int p_countryId) {
		d_countries.remove(p_countryId);
	}
	
	/**
	 * Add continent to player when player wins all the countries in that continent
	 * @param p_continent object of the continent
	 */
	public void addContinent(Continent p_continent) {
		d_continents.put(p_continent.getId(), p_continent);
	}
	
	/**
	 * Remove continent from player
	 * @param p_continentId Id of continent
	 */
	public void removeContinent(int p_continentId) {
		d_continents.remove(p_continentId);
	}
	
	/**
	 * Set number of armies, which depends upon number of countries and continents accupied by player
	 */
	public void setNumberOfArmies() {
		d_numberOfArmies = d_countries.size() / 3;
		if(d_numberOfArmies < 3) {
			d_numberOfArmies = 3;
		}
		for(Continent l_continent: d_continents.values()) {
			d_numberOfArmies += l_continent.getControlValue();
		}
	}
}
