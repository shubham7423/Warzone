package entities;

import java.util.HashSet;
import java.util.Set;

/**
 * 
 * Continent in a Map
 *
 */
public class Continent {
	private int d_id;
	private int d_controlValue;
	private Set<Country> d_countriesSet;
	
	/**
	 * Constructor for continent
	 * @param p_id id of continent
	 * @param p_controlValue control value
	 */
	public Continent(int p_id, int p_controlValue) {
		d_id = p_id;
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
	public int getId() {
		return d_id;
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
	
	/**
	 * Get all countries ids present 
	 * @return set of country ids
	 */
	public Set<Integer> getCountriesIds() {
		Set<Integer> l_countryNameSet = new HashSet<>();
		for(Country l_country: d_countriesSet) {
			;l_countryNameSet.add(l_country.getId());
		}
		return l_countryNameSet;
	}
	
	
}
