package entities.mapops;

import java.io.*;
import java.nio.file.Paths;
import java.util.*;
import entities.*;

/**
 * Class to write into map file
 */
public class ConquestWriteMap {
	private GameMap d_gameMap;
	private LinkedHashMap<Integer, Integer> d_continentsMap;
	private LinkedHashMap<Integer, Integer> d_countriesMap;
	private BufferedWriter d_writer;

	/**
	 * Constructor
	 * 
	 * @param p_gameMap GameMap object
	 */
	public ConquestWriteMap(GameMap p_gameMap) {
		d_gameMap = p_gameMap;
		d_continentsMap = new LinkedHashMap<>();
		d_countriesMap = new LinkedHashMap<>();
	}

	/**
	 * method that opens a file and writes the data to that file
	 * 
	 * @param p_filePath to .map file
	 * @return true if map saved successfully else false
	 * 
	 */
	/**
	 * @param p_filePath
	 * @return
	 */
	public boolean writeFullMap(String p_filePath) {
		int l_countryCtn = 0, l_continentCtn = 0;
		try {
			FileWriter l_fw = new FileWriter(
					Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_filePath).toString());
			d_writer = new BufferedWriter(l_fw);

//			 Writing Continents
			HashMap<Integer, Continent> l_continents = new HashMap<>();
			l_continents = d_gameMap.getContinents();
			d_writer.write("[Continents]");
			System.out.println("[Continents]\n");
			//System.out.println(l_continents);
			d_writer.newLine();
			for (int p_continents : l_continents.keySet()) {
				d_continentsMap.put(p_continents, ++l_continentCtn);
				d_writer.write(p_continents + "=" + l_continents.get(p_continents).getControlValue());	
				System.out.println(p_continents + "=" + l_continents.get(p_continents).getControlValue());
				d_writer.newLine();
			}

//			 Writing countries
			d_writer.write("\n");
			d_writer.write("[Territories]");
			System.out.println("[Territories]\n");
			d_writer.newLine();
			HashMap<Integer, Country> l_countries = new HashMap<>();
			l_countries = d_gameMap.getCountries();
			//System.out.println(l_countries);
			for (int p_continents : d_continentsMap.keySet()) {
				Set<Integer> l_countriesId = l_continents.get(p_continents).getCountriesIds();
				for (int p_countriesId : l_countriesId) {
//					Set<Integer> l_neighborIds = l_countries.get(p_countriesId).getNeighborIds();
					StringBuilder l_sb = new StringBuilder("");
//					d_countriesMap.put(p_countriesId, ++l_countryCtn);
//					for (int p_neighborIds : l_neighborIds) {
////						System.out.println(d_countriesMap);
//						l_sb.append("," + d_countriesMap.get(p_neighborIds).toString());
//					}
//					d_writer.write(p_countriesId + "," + "0,"+ "0," + d_continentsMap.get(p_continents) + l_sb.toString());
//					System.out.println(p_countriesId + "," + "0,"+ "0," + d_continentsMap.get(p_continents) + l_sb.toString());
//					d_writer.newLine();
//					System.out.println(p_countriesId);
					
					for(Country l_neighbor: l_countries.get(p_countriesId).getNeighborCountries()) {
						l_sb.append("," + l_neighbor.getId());
					}
					d_writer.write(p_countriesId + "," + "0,"+ "0," + d_continentsMap.get(p_continents) + l_sb.toString());
					d_writer.newLine();
				}
				d_writer.newLine();
			}

			d_writer.close();
			l_fw.close();
			return true;
		} catch (Exception p_e) {
			System.out.println("Exception " + p_e.getMessage());
			p_e.printStackTrace();
			return false;
		}
	}
	
	/*public static void main(String[] args)
	{
		GameMap gameMap = new GameMap();
		ConquestReadMap rmap = new ConquestReadMap(gameMap);
		ConquestWriteMap wmap = new ConquestWriteMap(gameMap);
		rmap.readFullMap("Canada.map");
		wmap.writeFullMap("conquestMapTest.map");
	}*/
}
