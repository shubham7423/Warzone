package entities.mapops;

import java.util.Set;
import java.util.HashMap;
import java.util.Scanner;

import java.io.File;
import java.io.FileNotFoundException;

import entities.GameMap;

public class ReadMap {
	GameMap d_gameMap;
	HashMap<Integer, String> d_continentsMap;
	HashMap<Integer, String> d_countriesMap;
	Scanner d_reader;
	
	public ReadMap(GameMap p_gameMap) {
		d_gameMap = p_gameMap;
		d_continentsMap = new HashMap<>();
		d_countriesMap = new HashMap<>();
	}
	
	
	
	public ReadMap() {
		
	}
	
	public void readFullMap(String p_filePath) {
		File l_mapFile = new File(p_filePath);
		boolean d_isContinents = false;
		String l_line;
		String l_dataString;
		int l_countryCtn = 0, l_continentCtn = 0;
		
		try {
			d_reader = new Scanner(l_mapFile);
			while (d_reader.hasNextLine()) {
				l_dataString = d_reader.nextLine();
				
				if(l_dataString.equals("[continents]")) {
					System.out.println("Continents: ");
					while ((l_line = d_reader.nextLine()) != null && l_line.length() > 0) {
						String[] l_continents = l_line.split(" ");
						++l_continentCtn;
						
						d_continentsMap.put(l_continentCtn, l_continents[0]);
						d_gameMap.addContinent(l_continents[0], Integer.parseInt(l_continents[1]));
					}
				}
				
				else if(l_dataString.equals("[countries]")) {
					System.out.println("Countries: ");
					while ((l_line = d_reader.nextLine()) != null && l_line.length() > 0) {
						String[] l_countries = l_line.split(" ");
						++l_countryCtn;
						System.out.println(l_countries[1]);
						d_countriesMap.put(l_countryCtn, l_countries[1]);
						d_gameMap.addCountry(l_countries[1], d_continentsMap.get(Integer.parseInt(l_countries[2])));
					}
					
				}
				
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	
	public Set<String> getContinentNames() {
		return d_gameMap.getContinents().keySet();
	}
	
	public Set<String> getCountriesNames() {
		return d_gameMap.getCountries().keySet();
	}
	
	
	public static void main(String[] args) {
		ReadMap map = new ReadMap(new GameMap()) ;
		map.readFullMap("/Users/shubhampatel/Downloads/Warzone-master/src/main/resources/maps/ameroki.map");
		System.out.println(map.getContinentNames());
		System.out.println(map.getCountriesNames());
		
	}
}
