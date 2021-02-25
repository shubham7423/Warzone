package entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

import dnl.utils.text.table.TextTable;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;


import entities.mapops.ReadMap;
import entities.mapops.WriteMap;
import entities.mapops.MapValidation;

/**
 * GameMap Class
 *
 */
public class GameMap {
	private HashMap<Integer, Continent> d_continents;
	private HashMap<Integer, Country> d_countries;
	private boolean d_isValid;
	
	/**
	 * Constructor for GameMap
	 */
	public GameMap() {
		d_continents = new HashMap<>();
		d_countries = new HashMap<>();
		d_isValid = false;
	}
	
	/**
	 * method to add a continent to the map
	 * @param p_continentId Id of continent
	 * @param p_controlValue Control value of continent
	 * @return Positive response if continent added successfully
	 */
	public String addContinent(int p_continentId, int p_controlValue) {
		if(d_continents.containsKey(p_continentId)) {
			return String.format("Continent \"%d\" already present in map", p_continentId);
		}
		d_continents.put(p_continentId, new Continent(p_continentId, p_controlValue));
		return String.format("Continent \"%d\" added to map", p_continentId);
	}
	
	/**
	 * method to remove a continent from map
	 * @param p_continentId Continent id to be removed
	 * @return Positive response if continent removed successfully
	 */
	public String removeContinent(int p_continentId) {
		if(!d_continents.containsKey(p_continentId)) {
			return String.format("Continent \"%d\" not present in map", p_continentId);
		}
		if(d_continents.get(p_continentId).getCountriesSet().size() > 0) {
			for(Country l_country: d_continents.get(p_continentId).getCountriesSet()) {
				removeCountry(l_country.getId());
			}
		}
		d_continents.remove(p_continentId);
		return String.format("Continent \"%s\" successfully removed from map", p_continentId);
	}
	
	/**
	 * method to add a country to the map
	 * @param p_countryId Id of country
	 * @param p_continentId Id of continent where country present
	 * @return Positive response if country added successfully
	 */
	public String addCountry(int p_countryId, int p_continentId) {
		if(d_countries.containsKey(p_countryId)) {
			return String.format("Country \"%d\" already present in map", p_countryId);
		}
		Country l_newCountry = new Country(p_countryId, d_continents.get(p_continentId));
		d_countries.put(p_countryId, l_newCountry);
		d_continents.get(p_continentId).addCountry(l_newCountry);
		return String.format("Country \"%d\" successfully added to map", p_countryId);
	}
	
	/**
	 * method to remove a country from map
	 * @param p_countryId id of country
	 * @return Positive response if country removed successfully
	 */
	public String removeCountry(int p_countryId) {
		if(!d_countries.containsKey(p_countryId)) {
			return String.format("Country \"%d\" not present in map", p_countryId);
		}
		for(Country l_country: d_countries.values()) {
			if(l_country.getNeighborIds().contains(p_countryId)) {
				removeNeighbor(l_country.getId(), p_countryId);
			}
		}
		d_countries.remove(p_countryId);
		return String.format("Country \"%d\" successfully removed from map", p_countryId);
	}
	
	/**
	 * method to add neighbor to a respective country
	 * @param p_sourceCountryId Country id
	 * @param p_destCountryId Neighbor country id
	 * @return Positive response if neighbor added successfully
	 */
	public String addNeighbor(int p_sourceCountryId, int p_destCountryId) {
		if(!d_countries.containsKey(p_sourceCountryId) && !d_countries.containsKey(p_destCountryId)) {
			return String.format("Ensure that both countries are present in map");
		}
		Country l_country1 = d_countries.get(p_sourceCountryId);
		Country l_country2 = d_countries.get(p_destCountryId);	
		if(l_country1 == null || l_country2 == null) {
			return String.format("Country not present");
		}
		if(l_country1.getNeighborCountries().contains(l_country2)) {
			return String.format("Country \"%d\" already a neighbor of \"%d\"", p_destCountryId, p_sourceCountryId);
		}
		l_country1.addNeighbor(l_country2);
		return String.format("Country \"%d\" is now a neighbor of country \"%d\"", p_destCountryId, p_sourceCountryId);
	}
	
	/**
	 * method to remove a neighbor of a respective country
	 * @param p_countryId Country id
	 * @param p_neighborId Neighbor country id
	 * @return Positive response if neighbor removed successfully
	 */
	public String removeNeighbor(int p_countryId, int p_neighborId) {
		Country l_country = d_countries.get(p_countryId);
		Country l_neighbor = d_countries.get(p_neighborId);	
		if(!l_country.getNeighborCountries().contains(l_neighbor)) {
			return String.format("Country \"%d\" is not a neighbor of \"%d\"", p_neighborId, p_countryId);
		}	
		l_country.removeNeighbor(l_neighbor);
		return String.format("Country \"%d\" removed from neighbors of \"%d\"", p_neighborId, p_countryId);
	}
	
	/**
	 * method to load a map from .map file
	 * @param p_fileName Name of a .map file
	 * @return Positive response if map file is loaded successfully or a negative response if it isn't
	 */
	public String loadMap(String p_fileName) {
		ReadMap l_mapRead = new ReadMap(this);
		Boolean l_loadCheck = l_mapRead.readFullMap(p_fileName);
		if(!l_loadCheck) {
			return String.format("Map \"%s\" cannot be loaded", p_fileName);
		}
		return String.format("Map \"%s\" loaded successfully", p_fileName);
	}

	/**
	 * method to show Map from Map file in specific representation in edit map phase
	 * print String of Countries, Continent, Corresponding neighbors
	 * @return l_final_data Table containing data in string format
	 */
	public String showMapEdit()
	{
		String[] l_column = {"Country","Continent; Control Value","Neighbors"};
		Object[][] l_data = new Object[d_countries.size()][l_column.length];
		Country l_country;
		TextTable l_tt;
		final ByteArrayOutputStream l_baos = new ByteArrayOutputStream();
		String l_final_data;
		
		int l_count = 0;
		
		for (HashMap.Entry<Integer, Country> l_item : d_countries.entrySet()) {
            l_country = l_item.getValue();
            l_data[l_count] = fillCountryData(l_country, true);
            l_count++;
        }
		
		l_tt = new TextTable(l_column, l_data);
        l_tt.setAddRowNumbering(false);
        l_tt.setSort(0);
        
        
        try (PrintStream l_ps = new PrintStream(l_baos, true, "UTF-8")) {
            l_tt.printTable(l_ps, 0);
            
        } catch (UnsupportedEncodingException p_e) {
        	
        	p_e.printStackTrace();
        }
        
        l_final_data = new String(l_baos.toByteArray(), StandardCharsets.UTF_8);
		return l_final_data;
	}
	
	/**
	 * method to show Map from Map file in specific representation in Game play phase
	 * print String of Countries, Continent, Owner, Armies present, Corresponding Neighbors
	 * @return l_final_data Table containing data in string format
	 */
	public String showMapPlay()
	{
		String[] l_column = {"Country", "Continent; Control Value", "Owner", "Armies","Neighbors"};
		Object[][] l_data = new Object[d_countries.size()][l_column.length];
		Country l_country;
		TextTable l_tt;
		final ByteArrayOutputStream l_baos = new ByteArrayOutputStream();
		String l_final_data;
		
		int l_count = 0;
		
		for (HashMap.Entry<Integer, Country> l_item : d_countries.entrySet()) {
            l_country = l_item.getValue();
            l_data[l_count] = fillCountryData(l_country, false);
            l_count++;
        }
		
		l_tt = new TextTable(l_column, l_data);
        l_tt.setAddRowNumbering(false);
        l_tt.setSort(0);
        
        
        try (PrintStream l_ps = new PrintStream(l_baos, true, "UTF-8")) {
            l_tt.printTable(l_ps, 0);
            
        } catch (UnsupportedEncodingException p_e) {
        	
        	p_e.printStackTrace();
        }
        
        l_final_data = new String(l_baos.toByteArray(), StandardCharsets.UTF_8);
        return l_final_data;
	}
	
	/**
	 * method to fill country data for each country
	 * @param p_country country for which data is to be present
	 * @param p_isEdit true if showMap called in edit phase, false if called in gameplay phase
	 * @return array of data of the specific country i.e. Country name, Continent Name and it's neighbors
	 */
	public String[] fillCountryData(Country p_country, boolean p_isEdit)
	{
		ArrayList<String> l_result = new ArrayList<String>();

		int l_id;

        String l_neighborsAsCsv = p_country.getNeighborCountries()
        	.stream()
        	.map(Country::getId)
        	.collect(Collectors.toSet())
        	.toString();

        l_id =  p_country.getId();
        l_result.add(l_id+"");
        l_result.add(p_country.getContinent().getId() + "; " + p_country.getContinent().getControlValue());
        if (!p_isEdit) {
        	l_result.add(p_country.getPlayer() != null ? p_country.getPlayer().getName() : "");
        	l_result.add(p_country.getNumberOfArmiesPresent() + "");
        }
        l_result.add(l_neighborsAsCsv);
        
        return l_result.toArray(new String[0]);
	}
	
	/**
	 * method to write map to file
	 * @param p_fileName File name
	 * @return Positive response if map written to file successfully
	 */
	public String saveMap(String p_fileName) {
		WriteMap l_writeMap = new WriteMap(this);
		if(!l_writeMap.writeFullMap(p_fileName)) {
			return String.format("Map file \"%s\" cannot be saved", p_fileName);
		}
		return String.format("Map file \"%s\" saved successfully", p_fileName);
	}
	
	/**
	 * Method to Validate the map
	 * @return the result of map validation
	 */
	public String validateMap() {
		MapValidation l_mapValidation = new MapValidation(this);
		String l_valCheck = l_mapValidation.validate();
		d_isValid = l_mapValidation.getMapValidationStatus();
		return String.format(l_valCheck);
	}
	
	/**
	 * method to get the status of map validation in the boolean format
	 * @return d_isValid boolean whether the map is valid or not.
	 */
	public boolean getValidateStatus() {
		return d_isValid;
	}
	
	/**
	 * method that returns all the continents
	 * @return d_continents Set of continents
	 */
	public HashMap<Integer, Continent> getContinents() {
		return d_continents;
	}
	
	/**
	 * method that returns all the countries
	 * @return d_countries Set of countries
	 */
	public HashMap<Integer, Country> getCountries() {
		return d_countries;
	}
}
