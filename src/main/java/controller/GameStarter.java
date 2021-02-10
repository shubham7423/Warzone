package controller;

import entities.GameMap;

/**
 * 
 * Main file to start game
 *
 */
public class GameStarter {
	
	GameMap d_gameMap = new GameMap();
	
	/**
	 * method to load map
	 * @param p_fileName file name of map
	 * @return loaded map(responses positive or negative)
	 */
	public String loadMap(String p_fileName) {
		String l_result = d_gameMap.loadMap(p_fileName);
		
		return l_result;
	}
	
	/**
	 * Edit continents
	 * @param p_commandSplitted splitted commands
	 * @return shows whether continents are added or removed
	 */
	public String editContinent(String[] p_commandSplitted) {
		String l_result;
		if (p_commandSplitted[0].equals("-add")) {
			l_result = d_gameMap.addContinent(p_commandSplitted[1], Integer.parseInt(p_commandSplitted[2]));
		}
		else {
			l_result = d_gameMap.removeContinent(p_commandSplitted[1]);
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
		if (p_commandSplitted[0].equals("-add")) {
			l_result = d_gameMap.addCountry(p_commandSplitted[1], p_commandSplitted[2]);
		}
		else {
			l_result = d_gameMap.removeCountry(p_commandSplitted[1]);
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
		if (p_commandSplitted[0].equals("-add")) {
			l_result = d_gameMap.addNeighbour(p_commandSplitted[1], p_commandSplitted[2]);
		}
		else {
			l_result = d_gameMap.removeNeighbour(p_commandSplitted[1], p_commandSplitted[2]);
		}
		return l_result;
	}
}
