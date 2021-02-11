package entities.mapops;
import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import entities.*;

/**
 * Class to write into map file
 * 
 */
public class WriteMap{
	GameMap d_gameMap;
	LinkedHashMap<String, Integer> d_continentsMap;
	LinkedHashMap<String, Integer> d_countriesMap;
	BufferedWriter d_writer;

	/**
	 * Constructor 
	 * @param p_gameMap GameMap object
	 */
	WriteMap (GameMap p_gameMap) {
		d_gameMap = p_gameMap;
		d_continentsMap = new LinkedHashMap<>();
		d_countriesMap = new LinkedHashMap<>();
	}

	/**
	 * Opens a file and writes the data to that file
	 * @param p_filePath to .map file 
	 * @return
	 * 
	 */
	public boolean writeFullMap (String p_filePath) {
		int l_countryCtn = 0, l_continentCtn = 0;
		try {
			FileWriter l_fw = new FileWriter(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_filePath).toString());
			d_writer = new BufferedWriter(l_fw);
			
			//Writing Continents
			HashMap<String, Continent> l_continents = new HashMap<String, Continent>();
			l_continents = d_gameMap.getContinents();
			d_writer.write("[continents]");
			d_writer.newLine();
			for (String p_continents: l_continents.keySet()) {
				d_continentsMap.put(p_continents, ++l_continentCtn);
				d_writer.write(p_continents + " " + l_continents.get(p_continents).getControlValue());
				d_writer.newLine();
			}

			//Writing countries
			d_writer.write("\n");
			d_writer.write("[countries]");
			d_writer.newLine();
			for (String p_continents: d_continentsMap.keySet()) {
				Set<String> l_countriesName = l_continents.get(p_continents).getCountriesName();
				for(String p_countriesName: l_countriesName) {
					d_countriesMap.put(p_countriesName, ++l_countryCtn);
					d_writer.write(l_countryCtn + " " + p_countriesName + " " + d_continentsMap.get(p_continents));
					d_writer.newLine();
				}
			}
			
			//Writing borders
			HashMap<String, Country> l_countries = new HashMap<>();
			l_countries = d_gameMap.getCountries();
			d_writer.write("\n");
			d_writer.write("[borders]");
			d_writer.newLine();
			for (String p_countries: d_countriesMap.keySet()) {
				Set<String> l_neighbourNames = l_countries.get(p_countries).getNeighbourNames();
				StringBuilder l_sb = new StringBuilder("");
				l_sb.append(d_countriesMap.get(p_countries).toString() + " ");

				for (String p_neighbourNames: l_neighbourNames) {
					l_sb.append(d_countriesMap.get(p_neighbourNames).toString() + " ");
				}
				d_writer.write(l_sb.toString());
				d_writer.newLine();
			}
			d_writer.close();
			l_fw.close();
			return true;
		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			e.printStackTrace();
			return false;
		}
	}
}


