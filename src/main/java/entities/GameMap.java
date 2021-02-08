package entities;

import java.util.HashMap;
import java.util.Set;

import entities.mapops.ReadMap;

/**
 * GameMap Class
 *
 */
public class GameMap {
	private HashMap<String, Continent> d_continents;
	private HashMap<String, Country> d_countries;
	
	/**
	 * Constructor for GameMap
	 */
	public GameMap() {
		d_continents = new HashMap<>();
		d_countries = new HashMap<>();
	}
	
	/**
	 * Add continent to the map
	 * @param p_continentName Name of continent
	 * @param p_controlValue Control value of continent
	 * @return Positive response if continent added
	 */
	public String addContinent(String p_continentName, int p_controlValue) {
		if(d_continents.containsKey(p_continentName)) {
			return String.format("Continent \"%s\" already present in map", p_continentName);
		}
		d_continents.put(p_continentName, new Continent(p_continentName, p_controlValue));
		return String.format("Continent \"%s\" added to map", p_continentName);
	}
	
	/**
	 * Remove continent from map
	 * @param p_continentName Continent name to be removed
	 * @return Positive response if continent removed
	 */
	public String removeContinent(String p_continentName) {
		if(!d_continents.containsKey(p_continentName)) {
			return String.format("Continent \"%s\" not present in map", p_continentName);
		}
		if(d_continents.get(p_continentName).getCountriesSet().size() > 0) {
			for(Country l_country: d_continents.get(p_continentName).getCountriesSet()) {
				removeCountry(l_country.getName());
			}
		}
		d_continents.remove(p_continentName);
		return String.format("Continent \"%s\" successfully removed from map", p_continentName);
	}
	
	/**
	 * Add country to the map
	 * @param p_countryName Name of country
	 * @param p_continentName Name of continent where country present
	 * @return Positive response if country added
	 */
	public String addCountry(String p_countryName, String p_continentName) {
		if(d_countries.containsKey(p_continentName)) {
			return String.format("Country \"%s\" already present in map", p_countryName);
		}
		Country l_newCountry = new Country(p_countryName, d_continents.get(p_continentName));
		d_countries.put(p_countryName, l_newCountry);
		d_continents.get(p_continentName).addCountry(l_newCountry);
		return String.format("Country \"%s\" successfully removed to map", p_countryName);
	}
	
	/**
	 * Remove country from map
	 * @param p_countryName Name of country
	 * @return Positive response if country removed
	 */
	public String removeCountry(String p_countryName) {
		if(!d_countries.containsKey(p_countryName)) {
			return String.format("Country \"%s\" not present in map", p_countryName);
		}
		for(Country l_country: d_countries.values()) {
			if(l_country.getNeighbourNames().contains(p_countryName)) {
				removeNeighbour(l_country.getName(), p_countryName);
			}
		}
		d_countries.remove(p_countryName);
		return String.format("Country \"%s\" successfully removed from map", p_countryName);
	}
	
	/**
	 * Add neighbour to country
	 * @param p_sourceCountryName Country name
	 * @param p_destCountryName Neighbour country name
	 * @return Positive response if neighbour added
	 */
	public String addNeighbour(String p_sourceCountryName, String p_destCountryName) {
		if(!d_countries.containsKey(p_sourceCountryName) && !d_countries.containsKey(p_destCountryName)) {
			return String.format("Ensure that both countries are present in map");
		}
		Country l_country1 = d_countries.get(p_sourceCountryName);
		Country l_country2 = d_countries.get(p_destCountryName);	
		if(l_country1.getNeighbourCountries().contains(l_country2)) {
			return String.format("Country \"%s\" already a neighbour of \"%s\"", p_destCountryName, p_sourceCountryName);
		}
		l_country1.addNeighbour(l_country2);
		return String.format("Country \"%s\" is now a neighbour of country \"%s\"", p_destCountryName, p_sourceCountryName);
	}
	
	/**
	 * Remove a neighbour
	 * @param p_countryName Country name
	 * @param p_neighbourName Neighbour country name
	 * @return Positive response if neighbour removed
	 */
	public String removeNeighbour(String p_countryName, String p_neighbourName) {
		Country l_country = d_countries.get(p_countryName);
		Country l_neighbour = d_countries.get(p_neighbourName);	
		if(!l_country.getNeighbourCountries().contains(l_neighbour)) {
			return String.format("Country \"%s\" is not a neighbour of \"%s\"", p_neighbourName, p_countryName);
		}	
		l_country.removeNeighbour(l_neighbour);
		return String.format("Country \"%s\" removed from neighbours of \"%s\"", p_neighbourName, p_countryName);
	}
	
	public String loadMap(String p_fileName) {
		ReadMap l_mapRead = new ReadMap(this);
		Boolean l_loadCheck = l_mapRead.readFullMap(p_fileName);
		if(!l_loadCheck) {
			return String.format("Map \"%s\" cannot be loaded", p_fileName);
		}
		return String.format("Map \"%s\" loaded successfully", p_fileName);
	}
	
	/**
	 * Return all continents
	 * @return Set of continents
	 */
	public HashMap<String, Continent> getContinents() {
		return d_continents;
	}
	
	/**
	 * Return all countries
	 * @return Set of countries
	 */
	public HashMap<String, Country> getCountries() {
		return d_countries;
	}
}
