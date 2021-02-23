package entities.mapops;

import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;

import entities.GameMap;

/**
 * Class to read map file
 *
 */
public class ReadMap {
	GameMap d_gameMap;
	HashMap<String, Integer> d_continentsMap;
	HashMap<Integer, String> d_countriesMap;
	Scanner d_reader;
	
	/**
	 * ReadMap Constructor
	 * @param p_gameMap GameMap object
	 */
	public ReadMap(GameMap p_gameMap) {
		d_gameMap = p_gameMap;
		d_continentsMap = new HashMap<>();
		d_countriesMap = new HashMap<>();
	}
	
	/**
	 * Read file and feed data to GameMap object
	 * @param p_filePath path to .map file
	 * @return true if map loaded successfully else false
	 */
	public boolean readFullMap(String p_filePath) {
		File l_mapFile = new File(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_filePath).toString());
		String l_line, l_dataString;
		int l_countryCtn = 0, l_continentCtn = 0;
		
		try {
			d_reader = new Scanner(l_mapFile);
			
			while (d_reader.hasNextLine()) {
				l_dataString = d_reader.nextLine();
				
//				Read continents
				if(l_dataString.equals("[continents]")) {
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if(l_line.length() > 0) {
							String[] l_continents = l_line.split(" ");
							++l_continentCtn;
							d_continentsMap.put(l_continents[0], l_continentCtn);
							d_gameMap.addContinent(l_continentCtn, Integer.parseInt(l_continents[1]));
						}
						else {
							break;
						}
					}
				}
				
//				Read countries
				else if(l_dataString.equals("[countries]")) {
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if(l_line.length() > 0) {
							String[] l_countries = l_line.split(" ");
							++l_countryCtn;
							d_countriesMap.put(l_countryCtn, l_countries[1]);
							d_gameMap.addCountry(Integer.parseInt(l_countries[0]), Integer.parseInt(l_countries[2]));
						}
						else {
							break;
						}
					}
					
				}
				
//				Read boundries
				else if(l_dataString.equals("[borders]")) {
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if(l_line.length() > 0) {
							String[] l_borders = l_line.split(" ");
							int l_countryId = Integer.parseInt(l_borders[0]);
							int l_neighbourId;
							for(int i=1; i<l_borders.length; i++) {
								l_neighbourId = Integer.parseInt(l_borders[i]);
								d_gameMap.addNeighbour(l_countryId, l_neighbourId);
							}
						}
						else {
							break;
						}
						
					}
				}		
			}
			d_reader.close();
			return true;
		} catch (FileNotFoundException e) {
			return false;
		}
	
	}
	
	/**
	 * Get continent ids
	 * @return Set of continent ids read
	 */
	public Set<Integer> getContinentIds() {
		return d_gameMap.getContinents().keySet();
	}
	
	/**
	 * Get countries name
	 * @return Set of countries id read
	 */
	public Set<Integer> getCountriesIds() {
		return d_gameMap.getCountries().keySet();
	}
}