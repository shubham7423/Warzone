package entities;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Continent in a Map
 *
 */
public class Continent {
	private String d_name;
	private int d_controlValue;
	private Set<Country> d_countriesSet;
	
	/**
	 * Constructor for continent
	 * @param p_name name of continent
	 * @param p_controlValue control value
	 */
	public Continent(String p_name, int p_controlValue) {
		d_name = p_name;
		d_controlValue = p_controlValue;
		d_countriesSet = new HashSet<Country>();
	}

	/**
	 * Add country to the continent
	 * @param p_country  country
	 */
	public void addCountry(Country p_country) {
		d_countriesSet.add(p_country);
	}
	
	/**
	 * Remove country from continent
	 * @param p_country country
	 */
	public void removeCountry(Country p_country) {
		d_countriesSet.remove(p_country);
	}
	
	/**
	 * Get name of the continent
	 * @return name of continent
	 */
	public String getName() {
		return d_name;
	}

	/**
	 * Get control value of the continent
	 * @return control value of continent
	 */
	public int getControlValue() {
		return d_controlValue;
	}

	/**
	 * Get the set of countries in the continent
	 * @return set of countries
	 */
	public Set<Country> getCountriesSet() {
		return d_countriesSet;
	}
	
	
}
