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
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if(l_line.length() > 0) {
							String[] l_continents = l_line.split(" ");
							++l_continentCtn;
							d_continentsMap.put(l_continentCtn, l_continents[0]);
							d_gameMap.addContinent(l_continents[0], Integer.parseInt(l_continents[1]));
						}
						else {
							break;
						}
					}
				}
				
				else if(l_dataString.equals("[countries]")) {
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						
						if(l_line.length() > 0) {
							String[] l_countries = l_line.split(" ");
							++l_countryCtn;
							d_countriesMap.put(l_countryCtn, l_countries[1]);
							d_gameMap.addCountry(l_countries[1], d_continentsMap.get(Integer.parseInt(l_countries[2])));
						}
						else {
							break;
						}
					}
					
				}
				
				else if(l_dataString.equals("[borders]")) {
					System.out.println("Borders: ");
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if(l_line.length() > 0) {
							String[] l_borders = l_line.split(" ");
							String l_countryName = d_countriesMap.get(Integer.parseInt(l_borders[0]));
							String l_neighbourName;
							System.out.println(l_borders[0]);
							for(int i=1; i<l_borders.length; i++) {
								l_neighbourName = d_countriesMap.get(Integer.parseInt(l_borders[i]));
								d_gameMap.addNeighbour(l_countryName, l_neighbourName);
							}
						}
						else {
							break;
						}
						
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
	
	public Set<String> getNeighbourNames() {
		return d_gameMap.getCountries().get("Frankreich").getNeighbourNames();
	}
	
	
	public static void main(String[] args) {
		ReadMap map = new ReadMap(new GameMap()) ;
		map.readFullMap("/Users/shubhampatel/Downloads/Warzone-master/src/main/resources/maps/eurasien.map");
		System.out.println(map.getContinentNames());
		System.out.println(map.getCountriesNames());
		System.out.println(map.getNeighbourNames());
	}
}
