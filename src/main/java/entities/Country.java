package entities;

import java.util.HashSet;
import java.util.Set;

/**
 * Country in a Map
 *
 */
public class Country {
	private int d_id;
	private Set<Country> d_neighborCountries;
	private int d_armiesPresent;
	private Continent d_continent;
	private Player d_owner;

	/**
	 * Constructor
	 * 
	 * @param p_id        id of the country
	 * @param p_continent Parent continent
	 */
	public Country(int p_id, Continent p_continent) {
		d_id = p_id;
		d_continent = p_continent;
		d_neighborCountries = new HashSet<Country>();
		d_armiesPresent = 0;
	}

	/**
	 * method to set owner of the country
	 * 
	 * @param p_player player object
	 */
	public void setPlayer(Player p_player) {
		d_owner = p_player;
	}

	/**
	 * method to get the owner of country
	 * 
	 * @return d_owner owner player object
	 */
	public Player getPlayer() {
		return d_owner;
	}

	/**
	 * method to place number of armies
	 * 
	 * @param p_armiesNumber number of armies to be added
	 */
	public void placeArmies(int p_armiesNumber) {
		d_armiesPresent += p_armiesNumber;
	}

	/**
	 * method to remove armies from country
	 * 
	 * @param p_armiesNumber number of armies to be removed
	 */
	public void removeArmies(int p_armiesNumber) {
		d_armiesPresent -= p_armiesNumber;
	}

	/**
	 * method to get id of country
	 * 
	 * @return d_id Id of a country
	 */
	public int getId() {
		return d_id;
	}

	/**
	 * method to get neighboring countries in form of set
	 * 
	 * @return d_neighborCountries set of neighboring countries
	 */
	public Set<Country> getNeighborCountries() {
		return d_neighborCountries;
	}

	/**
	 * method to add neighboring country
	 * 
	 * @param p_country Country to be added
	 */
	public void addNeighbor(Country p_country) {
		d_neighborCountries.add(p_country);
	}

	/**
	 * method to set number of armies present
	 * 
	 * @param p_armiesPresent number of armies to be set
	 */
	public void setNumberOfArmiesPresent(int p_armiesPresent) {
		d_armiesPresent = p_armiesPresent;
	}

	/**
	 * method to set number of armies present
	 * 
	 * @return d_armiesPresent number of armies
	 */
	public int getNumberOfArmiesPresent() {
		return d_armiesPresent;
	}

	/**
	 * method to get continent to which country belongs
	 * 
	 * @return d_continent continent to which country belongs
	 */
	public Continent getContinent() {
		return d_continent;
	}

	/**
	 * method to remove neighbor country
	 * 
	 * @param p_country country to be removed
	 * @return true of neighboring country present and deleted else false
	 */
	public boolean removeNeighbor(Country p_country) {
		if (!d_neighborCountries.contains(p_country)) {
			return false;
		}
		d_neighborCountries.remove(p_country);
		return true;
	}

	/**
	 * method to get neighbors names
	 * 
	 * @return l_neighborNameSet Set of neighbor names
	 */
	public Set<Integer> getNeighborIds() {
		Set<Integer> l_neighborNameSet = new HashSet<>();
		for (Country l_country : d_neighborCountries) {
			l_neighborNameSet.add(l_country.getId());
		}
		return l_neighborNameSet;
	}
}
