package entities.savedgames;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

import controller.GameEngine;
import entities.Continent;
import entities.Country;
import entities.GameMap;

/**
 * Class to save the game into a ".game" file.
 */
public class SaveGame {

	private GameMap d_gameMap;
	private LinkedHashMap<Integer, Integer> d_continentsMap;
	private LinkedHashMap<Integer, Integer> d_countriesMap;
	private BufferedWriter d_writer;
	private GameEngine d_gameEngine;
	
	public SaveGame(GameEngine p_gameEngine) {
		d_gameMap = new GameMap();
		d_continentsMap = new LinkedHashMap<>();
		d_countriesMap = new LinkedHashMap<>();
		d_gameEngine = p_gameEngine;
	}

	/**
	 * Method that opens a file and writes the Game Data to that file.
	 * 
	 * @param p_fileName to .game file
	 * @return true if map saved successfully else false
	 */
	public String saveGame(String p_fileName) {
		int l_countryCtn = 0, l_continentCtn = 0;
		try {
			FileWriter l_fw = new FileWriter(
					Paths.get(Paths.get("").toAbsolutePath().toString() + "/games/" + p_fileName).toString());
			d_writer = new BufferedWriter(l_fw);

//			 Writing Continents
			HashMap<Integer, Continent> l_continents = new HashMap<>();
			l_continents = d_gameMap.getContinents();
			d_writer.write("[continents]");
			d_writer.newLine();
			for (int p_continents : l_continents.keySet()) {
				d_continentsMap.put(p_continents, ++l_continentCtn);
				d_writer.write(p_continents + " " + l_continents.get(p_continents).getControlValue());
				d_writer.newLine();
			}

//			 Writing countries
			d_writer.write("\n");
			d_writer.write("[countries]");
			d_writer.newLine();
			for (int p_continents : d_continentsMap.keySet()) {
				Set<Integer> l_countriesId = l_continents.get(p_continents).getCountriesIds();
				for (int p_countriesId : l_countriesId) {
					d_countriesMap.put(p_countriesId, ++l_countryCtn);
					d_writer.write(l_countryCtn + " " + p_countriesId + " " + d_continentsMap.get(p_continents));
					d_writer.newLine();
				}
			}

//			 Writing borders
			HashMap<Integer, Country> l_countries = new HashMap<>();
			l_countries = d_gameMap.getCountries();
			d_writer.write("\n");
			d_writer.write("[borders]");
			d_writer.newLine();
			for (int p_countries : d_countriesMap.keySet()) {
				Set<Integer> l_neighborIds = l_countries.get(p_countries).getNeighborIds();
				StringBuilder l_sb = new StringBuilder("");
				l_sb.append(d_countriesMap.get(p_countries).toString() + " ");

				for (int p_neighborIds : l_neighborIds) {
					l_sb.append(d_countriesMap.get(p_neighborIds).toString() + " ");
				}
				d_writer.write(l_sb.toString());
				d_writer.newLine();
			}
			d_writer.close();
			l_fw.close();
			return null;   //to be implemented
		} catch (Exception p_e) {
			System.out.println("Exception " + p_e.getMessage());
			p_e.printStackTrace();
			return null;
		}
	} 
}
