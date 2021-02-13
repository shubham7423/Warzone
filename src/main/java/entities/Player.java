package entities;

import java.util.HashMap;

public class Player {
	private String d_name;
	private HashMap<Integer, Country> d_countries;
	private HashMap<Integer, Continent> d_continents;
	private int d_numberOfArmies;
	
	public Player(String p_name) {
		d_name = p_name;
		d_countries = new HashMap<>();
		d_continents = new HashMap<>();
		d_numberOfArmies = 0;
	}

	public String getName() {
		return d_name;
	}

	public HashMap<Integer, Country> getCountries() {
		return d_countries;
	}

	public HashMap<Integer, Continent> getContinents() {
		return d_continents;
	}
	
	public int getNumberOfArmies() {
		return d_numberOfArmies;
	}
	
	public void addCountry(Country p_country) {
		d_countries.put(p_country.getId(), p_country);
	}
	
	public void removeCountry(Country p_country) {
		d_countries.remove(p_country.getId());
	}
	
	public void addContinent(Continent p_continent) {
		d_continents.put(p_continent.getId(), p_continent);
	}
	
	public void removeContinent(Continent p_continent) {
		d_continents.remove(p_continent.getId());
	}
	
	public void setNumberOfArmies() {
		d_numberOfArmies = d_countries.size() / 3;
		if(d_numberOfArmies < 3) {
			d_numberOfArmies = 3;
		}
		for(Continent l_continent: d_continents.values()) {
			d_numberOfArmies += l_continent.getControlValue();
		}
	}
}
