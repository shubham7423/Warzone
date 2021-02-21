package entities.mapops;

import java.util.HashSet;
import java.util.Set;

import entities.Continent;
import entities.Country;
import entities.GameMap;

/**
 * This class will validate the map of the game
 * connection of all countries,
 * continents with their respective countries mapping
 *
 */
public class MapValidation {
	private boolean d_connectedGraph; 
	private GameMap d_gameMap;
	private boolean d_emptyMap = false;
	private boolean d_emptyContinent = false;
	public boolean d_flag = false;
	/**
	 * Constructor of MapValidation
	 * @param p_gameMap the map which you want to validate
	 */
	public MapValidation(GameMap p_gameMap) {
		this.d_gameMap = p_gameMap;
	}

	/**
	 * This method is used to get the instant status of map validation 
	 * in the form of boolean. True signifies that the map validation is successful 
	 * and vice versa. (Do not call this method before calling the validate method)
	 * @return instant status of the validated map in the form of boolean.
	 */
	public boolean getMapValidationStatus() {
		if(d_flag) {			
			return d_connectedGraph&&(!d_emptyContinent)&&(!d_emptyMap);
		} else {
			System.out.println("Please validate the map before getting the status of map.");
			return false;
		}
	}
	
	/**
	 * This is the prime function to validate whole graph
	 * it will show the status of validation of map
	 * @return return the validation result to print based on all the validation criteria like connected countries, connected continent, empty continents.
	 * @throws Exception error is thrown
	 */
	public String validate() {
		d_flag = true;
		StringBuilder l_validationResult = new StringBuilder();
		boolean l_result = checkAll();
		
		if(!l_result) {
			if(d_emptyMap) {				
				l_validationResult.append("The Map does not contain any countries.");
			}
			else {
				if(!d_connectedGraph) {
					l_validationResult.append(" The graph is not connected. Countries are not traverseble.");
				}
				if(d_emptyContinent) {
					l_validationResult.append(" Empty Continent(s) found.");
				}
			}
		} else {
			if(d_connectedGraph) {
				l_validationResult.append(" The graph is connected. Countries are traverseble.");
			}
			if(d_emptyContinent) {
				l_validationResult.append(" Empty Continent(s) found.");
			}
		}
		return l_validationResult.toString();
	}

	/**
	 * this function gathers information about all types of validation 
	 * @return false if all the validation of the Map are successful or not. If everything in map is correct,
	 * then it will return true
	 */
	public boolean checkAll() {
		if(d_gameMap.getCountries().size() == 0) {
			d_emptyMap = true;
			return false;
		}
		
		Set<Integer> l_countryIds = d_gameMap.getCountries().keySet();
		this.d_connectedGraph = isConnected(d_gameMap.getCountries().values().iterator().next(), l_countryIds);

		for (Continent l_continent : d_gameMap.getContinents().values()) {
			l_countryIds = l_continent.getCountriesIds();
			if (l_countryIds.isEmpty()) {
				d_emptyContinent = true;
				break;
			}
		}
		return d_connectedGraph;
	}

	/**
	 * This function validates whether all the countries are traversable or not
	 * starting from any one country
	 * @param p_firstCountry it starts traversing through one country to check the connection of graph
	 * @param p_countryIds it compares with this parameter if all the countries are traversed
	 * @return returns the status if map is connected or not. Returns true if connected.
	 */
	public boolean isConnected(Country p_firstCountry, Set<Integer> p_countryIds) {
		Set<Integer> l_countryIdsVisited = new HashSet<Integer>();
		l_countryIdsVisited = countryIterator(p_firstCountry, l_countryIdsVisited);
		return l_countryIdsVisited.equals(p_countryIds);
	}

	/**
	 * This is a recursive function that visits all the adjacent country 
	 * and traverse in the BFS manner to the adjacent countries.
	 * @param p_currentCountry this is the current country that is being visited for traversing
	 * @param p_visitedCountryIds set of all the countries that are already visited
	 * @return will return the set of visited countries
	 */
	public Set<Integer> countryIterator(Country p_currentCountry, Set<Integer> p_visitedCountryIds){
		if(p_visitedCountryIds.contains(p_currentCountry.getId())){
			return p_visitedCountryIds;
		} else {
			p_visitedCountryIds.add(p_currentCountry.getId());
			for(Country l_nextCountry: p_currentCountry.getNeighbourCountries()) {
				p_visitedCountryIds = countryIterator(l_nextCountry, p_visitedCountryIds);
			}
		}
		return p_visitedCountryIds;
	}
}
