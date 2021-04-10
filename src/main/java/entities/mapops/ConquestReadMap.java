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
 */
public class ConquestReadMap {
	private GameMap d_gameMap;
	private HashMap<String, Integer> d_continentsMap;
	private HashMap<String, Integer> d_countriesMap;
	Scanner d_reader;

	/**
	 * ReadMap Constructor
	 * 
	 * @param p_gameMap GameMap object
	 */
	public ConquestReadMap(GameMap p_gameMap) {
		d_gameMap = p_gameMap;
		d_continentsMap = new HashMap<>();
		d_countriesMap = new HashMap<>();
	}

	/**
	 * method to return the gameMap object of the map file.
	 * 
	 * @return gameMap object.
	 */
	public GameMap getGameMap() {
		return d_gameMap;
	}

	/**
	 * method to read a file and feed data to GameMap object
	 * 
	 * @param p_filePath path to .map file
	 * @return true if map loaded successfully else false
	 */
	public boolean readFullMap(String p_filePath) {
		File l_mapFile = new File(
				Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_filePath).toString());
		String l_line, l_dataString;
		int l_countryCtn = 0, l_continentCtn = 0;

		try {
			d_reader = new Scanner(l_mapFile);
			while (d_reader.hasNextLine()) {
				l_dataString = d_reader.nextLine();

//				Read continents
				if ("[Continents]".equals(l_dataString)) {
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if (l_line.length() > 0) {
							String[] l_continents = l_line.split("=");
							++l_continentCtn;
							d_continentsMap.put(l_continents[0], l_continentCtn);
							d_gameMap.addContinent(l_continentCtn, Integer.parseInt(l_continents[1]));
						} else {
							break;
						}
					}
				}

//				Read countries
				else if ("[Territories]".equals(l_dataString)) {
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if (l_line.length() > 0) {
							String[] l_countries = l_line.split(",");
							if(l_countries.length>2)
							{
								++l_countryCtn;
								d_countriesMap.put(l_countries[0], l_countryCtn);
							}
						} else {
							break;
						}
					}
					
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if (l_line.length() > 0) {
							String[] l_countries = l_line.split(",");
							if(l_countries.length>2) {
								System.out.println(d_continentsMap);
								System.out.println(d_countriesMap);
								d_gameMap.addCountry(d_countriesMap.get(l_countries[0]), d_continentsMap.get(l_countries[3]));
								int l_neighborId;
								for (int i = 4; i < l_countries.length; i++) {
									l_neighborId = d_countriesMap.get(l_countries[i]);
									d_gameMap.addNeighbor(d_countriesMap.get(l_countries[0]), l_neighborId);
								}
							}
						} else {
							break;
						}
					}

				}
				
			}
			d_reader.close();
			return true;
		} catch (FileNotFoundException p_e) {
			return false;
		}
	}

	/**
	 * method to get continents ids
	 * 
	 * @return Set of continents ids read
	 */
	public Set<Integer> getContinentIds() {
		return d_gameMap.getContinents().keySet();
	}

	/**
	 * method to get countries ids
	 * 
	 * @return Set of countries ids read
	 */
	public Set<Integer> getCountriesIds() {
		return d_gameMap.getCountries().keySet();
	}
	
	/*public static void main(String[] args)
	{
		GameMap gameMap = new GameMap();
		ConquestReadMap map = new ConquestReadMap(gameMap);
		map.readFullMap("Africa.map");
		System.out.println(map.getContinentIds());
		System.out.println(map.getCountriesIds());
	}*/
}