package entities;

import java.util.HashSet;
import java.util.Set;

/**
 * Country in a Map
 *
 */
public class Country {
	private int d_id;
	private Set<Country> d_neighbourCountries;
	private int d_armiesPresent;
	Continent d_continent;
	
	/**
	 * Constructor
	 * @param p_id id of the country
	 * @param p_continent Parent continent
	 */
	public Country(int p_id, Continent p_continent) {
		d_id = p_id;
		d_continent = p_continent;
		d_neighbourCountries = new HashSet<Country>();
		d_armiesPresent = 0;
	}
	
	/**
	 * Place number of armies
	 * @param p_armiesNumber armies to be added
	 */
	public void placeArmies(int p_armiesNumber) {
		d_armiesPresent += p_armiesNumber;
	}
	
	/**
	 * Remove armies from country 
	 * @param p_armiesNumber armies to be removed
	 */
	public void removeArmies(int p_armiesNumber) {
		d_armiesPresent -= p_armiesNumber;
	}

	/**
	 * Get id of country
	 * @return country id
	 */
	public int getId() {
		return d_id;
	}


	/**
	 * Get neighbouring country
	 * @return set of neighbouring countries 
	 */
	public Set<Country> getNeighbourCountries() {
		return d_neighbourCountries;
	}


	/**
	 * Add neighbouring country
	 * @param p_country Country to be added
	 */
	public void addNeighbour(Country p_country) {
		d_neighbourCountries.add(p_country);
	}
	
	/**
	 * Get number of armies present
	 * @return number of armies
	 */
	public int getNumberOfArmiesPresent() {
		return d_armiesPresent;
	}
	
	/**
	 * Get continent to which country belongs
	 * @return continent to which country belongs
	 */
	public Continent getContinent() {
		return d_continent;
	}
	
	/**
	 * Remove neighbour country
	 * @param p_country country to be removed
	 * @return true of neighbouring country present and deleted else false
	 */
	public boolean removeNeighbour(Country p_country) {
		if (!d_neighbourCountries.contains(p_country)) {
			return false;
		}
		d_neighbourCountries.remove(p_country);
		return true;
	}
	
	/**
	 * Get neighbours names
	 * @return Set of neighbour names
	 */
	public Set<Integer> getNeighbourIds() {
		Set<Integer> l_neighbourNameSet = new HashSet<>();
		for(Country l_country: d_neighbourCountries) {
			l_neighbourNameSet.add(l_country.getId());
		}
		return l_neighbourNameSet;
	}
}
