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
	private boolean d_emptyMap = false;
	private boolean d_emptyContinent;

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
	 * @return return the validation result to print based on all the validation criteria like connected countries, connected continent, empty continents.
	 */
	public String validate() {
		StringBuilder l_validationResult = new StringBuilder();
		boolean l_result = checkAllMapValidationRules();
		if(l_result) {
			l_validationResult.append("The graph is connected.\nCountries are traverseble.\nContinents are connected.");
			
			if(d_emptyContinent) {
				l_validationResult.append("One of the Continent is empty.");
			}
		} else {
			if(d_connectedGraph) {
				l_validationResult.append("The graph is connected.\nCountries are traverseble.");
			} else {
				l_validationResult.append("The graph is not connected.\nCountries are not traverseble.");
			}
			
			if(d_connectedSubGraph) {
				l_validationResult.append("Continents are connected.");
			} else {
				l_validationResult.append("Continents are not connected.");
			}
			
			if(d_emptyContinent) {
				l_validationResult.append("One of the Continent is empty.");
			}
		}
		return l_validationResult.toString();
	}

	/**
	 * this function gathers information about all types of validation 
	 * @return if all the validation of the Map are successful or not. If everything in map is correct,
	 * then it will return true
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
				if (l_countryNames.isEmpty()) {
					d_emptyContinent = true;
					continue;	
				}
				d_connectedSubGraph &= isMapConnected(l_continent.getCountriesSet().iterator().next(), l_countryNames);
			}

		}
		return d_connectedGraph && d_connectedSubGraph;
	}

	/**
	 * This function validates whether all the countries are traversable or not
	 * starting from any one country
	 * @param p_firstCountry it starts traversing through one country to check the connection of graph
	 * @param p_countryNames it compares with this parameter if all the countries are traversed
	 * @return returns the status if map is connected or not. Returns true if connected.
	 */
	public boolean isMapConnected(Country p_firstCountry, Set<String> p_countryNames) {
		if(p_countryNames.size() == 0) {
			d_emptyMap = true;
		}
		Set<String> l_countryNamesVisited = new HashSet<String>();
		l_countryNamesVisited = countryIterator(p_firstCountry, l_countryNamesVisited);
		return l_countryNamesVisited.equals(p_countryNames);
	}

	/**
	 * This is a recursive function that visits all the adjacent country 
	 * and traverse in the BFS manner to the adjacent countries.
	 * @param p_currentCountry this is the current country that is being visited for traversing
	 * @param p_visitedCountryNames set of all the countries that are already visited
	 * @return will return the set of visited countries 
	 */
	public Set<String> countryIterator(Country p_currentCountry, Set<String> p_visitedCountryNames){
		if(p_visitedCountryNames.contains(p_currentCountry.getName())){
			return p_visitedCountryNames;
		}
		else {
			p_visitedCountryNames.add(p_currentCountry.getName());
			for(Country l_nextCountry: p_currentCountry.getNeighbourCountries()) {
				p_visitedCountryNames = countryIterator(l_nextCountry, p_visitedCountryNames);
			}
		}
		return p_visitedCountryNames;
	}
}
