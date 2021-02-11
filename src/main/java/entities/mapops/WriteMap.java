package entities.mapops;
import java.io.*;
import java.util.*;
import entities.*;

/**
 * Class to write into map file
 * 
 */

public class WriteMap{
	GameMap d_gameMap;
	HashMap<String, Integer> d_continentsMap;
	HashMap<String, Integer> d_countriesMap;
	BufferedWriter d_writer;

	/**
	 * Constructor 
	 * @param p_gameMap GameMap object
	 */
	WriteMap (GameMap p_gameMap) {
		d_gameMap = p_gameMap;
		d_continentsMap = new HashMap<>();
		d_countriesMap = new HashMap<>();
	}

	/**
	 * Open a file and write the data to that file
	 * @param p_filePath to .map file 
	 * 
	 */
	public boolean writeFullMap (String p_filePath) {
//		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

		//		File l_mapFile = new java.io.File(classLoader.getResource(p_filePath).getFile());
//		Writer l_fileUrl = classLoader.getResource(p_filePath);
		String l_line, l_dataString;
		int l_countryCtn = 0, l_continentCtn = 0;

		try {
			FileWriter l_fw = new FileWriter(p_filePath);
			d_writer = new BufferedWriter(l_fw);

			//Writing Continents

			HashMap<String, Continent> l_continents = new HashMap<String, Continent>();
			l_continents = d_gameMap.getContinents();
			d_writer.write("[continents]");

			for (String p_continents: l_continents.keySet()) {
				d_continentsMap.put(p_continents, ++l_continentCtn);
				d_writer.write(p_continents + " " + l_continents.get(p_continents).getControlValue());//is it a control value or the increment value
				d_writer.newLine();
			}


			//Writing countries
			d_writer.write("[countries]");

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
			d_writer.write("[borders]");

			for (String p_countries: d_countriesMap.keySet()) {
				Set<String> l_neighbourNames = l_countries.get(p_countries).getNeighbourNames();
				
				StringBuilder l_sb = new StringBuilder("");
				l_sb.append(d_countriesMap.get(p_countries).toString() + " ");

				for (String p_neighbourNames: l_neighbourNames) {
					l_sb.append(d_countriesMap.get(p_neighbourNames).toString() + " ");
				}
				d_writer.write(l_sb.toString());
			}

		} catch (Exception e) {
			System.out.println("Exception " + e.getMessage());
			e.printStackTrace();
		}
		return true;
	}

	public static void main(String[] args) {
		GameMap gm = new GameMap();
		gm.addContinent("NorthAmerica", 1);
		gm.addContinent("SouthAmerica", 2);
		gm.addContinent("Asia", 3);
		
		gm.addCountry("Alaska", "NorthAmerica");
		gm.addCountry("Peru", "SouthAmerica");
		gm.addCountry("India", "Asia");
		
		gm.addNeighbour("India", "Alaska");
		gm.addNeighbour("Alaska", "Peru");
		
		WriteMap wMap = new WriteMap(gm);
		wMap.writeFullMap("D:/MACS/SOEN 6441 - Advanced programmig practices/Project/Warzone/src/main/java/entities/mapops/WorldMap.txt");
	}
}


