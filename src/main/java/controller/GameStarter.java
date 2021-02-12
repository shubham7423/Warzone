package controller;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import entities.GameMap;

/**
 * 
 * Main file to start game
 *
 */
public class GameStarter {
	
	GameMap d_gameMap = new GameMap();
	boolean is_loadedMap = false, is_editMap = false;
	
	/**
	 * method to edit map, it creates new file when specified file name does not exists else loads existing map file.
	 * @param p_fileName file name
	 * @return String which states completion of the operation
	 */
	public String editMap(String p_fileName) {
		String l_result;
		if (!is_editMap && !is_loadedMap) {
			this.loadMap(p_fileName);
			if(!Files.exists(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_fileName))) {
				System.out.println(Paths.get("").toAbsolutePath().toString());
				try {
					Files.createDirectories(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps"));
					Files.createFile(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_fileName));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			l_result = String.format("Map \"%s\" ready for edit", p_fileName);
			is_editMap = true;
			is_loadedMap = false;
		}
		else {
			l_result = String.format("Edit map not available");
		}
		return l_result;
	}
	
	/**
	 * method to load map
	 * @param p_fileName file name of map
	 * @return loaded map(responses positive or negative)
	 */
	public String loadMap(String p_fileName) {
		String l_result;
		if(!is_editMap) {
			l_result = d_gameMap.loadMap(p_fileName);
			is_loadedMap = true;
		}
		else {
			l_result = String.format("Cannot be loaded map when map is edited");
		}
		return l_result;
	}
	
	/**
	 * Edit continents
	 * @param p_commandSplitted splitted commands
	 * @return shows whether continents are added or removed
	 */
	public String editContinent(String[] p_commandSplitted) {
		String l_result;
		if(is_editMap && !is_loadedMap) {
			if (p_commandSplitted[0].equals("-add")) {
				l_result = d_gameMap.addContinent(p_commandSplitted[1], Integer.parseInt(p_commandSplitted[2]));
			}
			else {
				l_result = d_gameMap.removeContinent(p_commandSplitted[1]);
			}
		}
		else {
			l_result = String.format("Map can only be edited when file is open in edit phase");
		}
		return l_result;
	}
	
	/**
	 * edit countries
	 * @param p_commandSplitted splitted commands
	 * @return shows whether countries are added or removed with respect to their continents
	 */
	public String editCountry(String[] p_commandSplitted) {
		String l_result;
		if(is_editMap && !is_loadedMap) {
			if (p_commandSplitted[0].equals("-add")) {
				l_result = d_gameMap.addCountry(p_commandSplitted[1], p_commandSplitted[2]);
			}
			else {
				l_result = d_gameMap.removeCountry(p_commandSplitted[1]);
			}
		}
		else {
			l_result = String.format("Map can only be edited when file is open in edit phase");
		}
		return l_result;
	}
	
	/**
	 * edit neighbours
	 * @param p_commandSplitted splitted commands
	 * @return shows whether neighbours countries are added or removed
	 */
	public String editNeighbour(String[] p_commandSplitted) {
		String l_result;
		if(is_editMap && !is_loadedMap) {
			if (p_commandSplitted[0].equals("-add")) {
				l_result = d_gameMap.addNeighbour(p_commandSplitted[1], p_commandSplitted[2]);
			}
			else {
				l_result = d_gameMap.removeNeighbour(p_commandSplitted[1], p_commandSplitted[2]);
			}
		}
		else {
			l_result = String.format("Map can only be edited when file is open in edit phase");
		}
		return l_result;
	}
	
	public static void main(String[] args) {
		GameStarter gStarter = new GameStarter();
		System.out.print(gStarter.editMap("hajsgdjh.map"));
	}
}
