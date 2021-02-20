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
			if(d_emptyMap) {
				l_validationResult.append("Map does not contain any country.");
			}
			
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
		Set<Integer> l_countryIds = d_gameMap.getCountries().keySet();
		d_connectedGraph = isMapConnected(d_gameMap.getCountries().values().iterator().next(), l_countryIds);

		d_connectedSubGraph = true;
		for (Continent l_continent : d_gameMap.getContinents().values()) {
			l_countryIds = l_continent.getCountriesIds();
			if (l_countryIds.isEmpty()) {
				d_emptyContinent = true;
				continue;	
			}
			d_connectedSubGraph &= isMapConnected(l_continent.getCountriesSet().iterator().next(), l_countryIds);
		}
		return d_connectedGraph && d_connectedSubGraph;
	}

	/**
	 * This function validates whether all the countries are traversable or not
	 * starting from any one country
	 * @param p_firstCountry it starts traversing through one country to check the connection of graph
	 * @param p_countryIds it compares with this parameter if all the countries are traversed
	 * @return returns the status if map is connected or not. Returns true if connected.
	 */
	public boolean isMapConnected(Country p_firstCountry, Set<Integer> p_countryIds) {
		if(p_countryIds.size() == 0) {
			d_emptyMap = true;
		}
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
