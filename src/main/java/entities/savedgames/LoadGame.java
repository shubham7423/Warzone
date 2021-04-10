package entities.savedgames;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

import controller.GameEngine;
import entities.GameMap;

/**
 * Class to load a game from a ".game" file.
 */
public class LoadGame {
	private GameEngine d_gameEngine;
	private HashMap<String, Integer> d_continentsMap;
	private HashMap<Integer, String> d_countriesMap;
	Scanner d_reader;

	public LoadGame(GameEngine p_gameEngine) {
		d_gameEngine = p_gameEngine;
		d_continentsMap = new HashMap<>();
		d_countriesMap = new HashMap<>();
	}

	/**
	 * Method that opens a file and reads the Game Data from that file.
	 * 
	 * @param p_fileName from .game file
	 * @return 
	 */
	public String loadGame(String p_fileName){
		try {
			File l_mapFile = new File(
					Paths.get(Paths.get("").toAbsolutePath().toString() + "/games/" + p_fileName).toString());
			String l_line, l_dataString;
			int l_countryCtn = 0, l_continentCtn = 0;
			
			d_reader = new Scanner(l_mapFile);
			while (d_reader.hasNextLine()) {
				l_dataString = d_reader.nextLine();

//				Read continents
				if ("[continents]".equals(l_dataString)) {
					while (d_reader.hasNextLine()) {
						l_line = d_reader.nextLine();
						if (l_line.length() > 0) {
							String[] l_continents = l_line.split(" ");
							++l_continentCtn;
							d_continentsMap.put(l_continents[0], l_continentCtn);
							d_gameEngine.getGameMap().addContinent(l_continentCtn, Integer.parseInt(l_continents[1]));
						} else {
							break;
						}
					}
				}
			}
		}catch (FileNotFoundException p_e) {
			System.out.println("Exception " + p_e.getMessage());
			p_e.printStackTrace();
			return null;
		}
		
		return null;
	}
}
