package entities;

import java.util.HashSet;
import java.util.Set;

/**
 * This class will validate the map of the game
 * connection of all countries,
 * continents with their respective countries mapping
 *
 */
public class MapValidation {
	private boolean d_connectedGraph;
	private boolean d_connectedSubGraph; 
	private GameMap d_gameMap;
	
	/**
	 * Constructor of MapValidation
	 * @param gameMap the map which you want to validate
	 */
	public MapValidation(GameMap p_gameMap) {
		this.d_gameMap = p_gameMap;
		this.d_connectedGraph = true;
	}
	
	/**
	 * this is the prime function to validate whole graph
	 * it will show the status of validation of map
	 * @return
	 */
	public String validate() {
		StringBuilder l_validationResult = new StringBuilder();
		return l_validationResult.toString();
	}
	
	/**
	 * this function gathers information about all types of validation 
	 * @return
	 */
	public boolean checkAllMapValidationRules() {
		{
			Set<String> l_countryNames = d_gameMap.getCountries().keySet();
			d_connectedGraph = isMapConnected(d_gameMap.getCountries().values().iterator().next(), l_countryNames);
		}
		{
			d_connectedSubGraph = true;
			for (Continent l_continent : d_gameMap.getContinents().values()) {
				Set<String> l_countryNames = l_continent.getCountriesName();
				if (l_countryNames.isEmpty())
					continue;
				d_connectedSubGraph &= isMapConnected(l_continent.getCountriesSet().iterator().next(), l_countryNames);
			}

		}
		return d_connectedGraph && d_connectedSubGraph;
	}
	
	/**
	 * This function validates whether all the countries are traversable or not
	 * starting from any one country
	 * @param p_firstCountry it starts traversing through one country
	 * @param p_countryNames it compares with this parameter if all the countries are traversed
	 * @return
	 */
	public boolean isMapConnected(Country p_firstCountry, Set<String> p_countryNames) {
		
		Set<String> l_countryNames = new HashSet<String>();
		Set<Country> l_countryObjects = new HashSet<Country>();
		l_countryObjects.add(p_firstCountry);
		
		boolean l_flag = true;
		
		while(l_flag) {
			Set<Country> l_tempCountryObjectAdd = new HashSet<Country>();
			for(Country l_tempCountry : l_countryObjects) {
				if(!l_countryNames.contains(l_tempCountry.getNeighbourNames())) {
					l_countryNames.addAll(l_tempCountry.getNeighbourNames());
					l_tempCountryObjectAdd.addAll(l_tempCountry.getNeighbourCountries());
					l_flag = true;
				} else {
					l_flag = false;
				}
			}
			l_countryObjects.addAll(l_tempCountryObjectAdd);
		}
		
		return l_countryNames.contains(p_countryNames);
	}
	
	
}
