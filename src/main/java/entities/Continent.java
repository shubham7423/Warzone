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
	 * method to add country to the continent
	 * @param p_country  country
	 */
	public void addCountry(Country p_country) {
		d_countriesSet.add(p_country);
	}
	
	/**
	 * method to remove country from continent
	 * @param p_country country
	 */
	public void removeCountry(Country p_country) {
		d_countriesSet.remove(p_country);
	}
	
	/**
	 * Get name of the continent
	 * @return d_id name of continent
	 */
	public int getId() {
		return d_id;
	}

	/**
	 * method to get control value of the continent
	 * @return d_controlValue control value of continent
	 */
	public int getControlValue() {
		return d_controlValue;
	}

	/**
	 * method to get the set of countries in the continent
	 * @return d_countrieSet set of countries
	 */
	public Set<Country> getCountriesSet() {
		return d_countriesSet;
	}
	
	/**
	 * method to get all countries id present 
	 * @return l_countryNameSet set of country id
	 */
	public Set<Integer> getCountriesIds() {
		Set<Integer> l_countryNameSet = new HashSet<>();
		for(Country l_country: d_countriesSet) {
			;l_countryNameSet.add(l_country.getId());
		}
		return l_countryNameSet;
	}
	
	
}
