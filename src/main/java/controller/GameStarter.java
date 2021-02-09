package controller;

import entities.GameMap;

public class GameStarter {
	
	GameMap d_gameMap = new GameMap();

	public GameStarter() {
		
	}
	
	public String loadMap(String p_fileName) {
		String l_result = d_gameMap.loadMap(p_fileName);
		
		return l_result;
	}
	
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
}
