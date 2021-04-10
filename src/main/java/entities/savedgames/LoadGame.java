package entities.savedgames;

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
	
	public LoadGame(GameEngine p_gameEngine){
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
		
		
		return null;
	}
}
