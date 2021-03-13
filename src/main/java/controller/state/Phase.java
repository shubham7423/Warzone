package controller.state;
import entities.GameMap;

import controller.GameEngine;
import controller.GameStarter;

public abstract class Phase {
	public GameStarter d_gameEngine;
	
	public Phase(GameStarter p_gameEngine){
		d_gameEngine = p_gameEngine;
	}
	
	abstract public String editMap(String p_fileName);
	
	abstract public String editContinent(String[] p_commandSplitted);
	
	abstract public String editCountry(String[] p_commandSplitted);
	
	abstract public String editNeighbor(String[] p_commandSplitted);
	
	abstract public String saveMap(String p_fileName);
	
	abstract public String loadMap(String p_fileName);
	
	abstract public String gamePlayer(String[] p_commandSplitted);
	
	abstract public String addPlayer();
	
	abstract public String removePlayer();
	
//	abstract public GameMap getGameMap();
	
	abstract public String deployPhase();
	
	abstract public String assignCountries();
	
//	abstract public void checkContinentOwnership();
	
//	abstract public void assignArmies();
	
	public String showMap() {	
		return d_gameEngine.getGameMap().showMapEdit();
	}
	
	public String validateMap() {
		String l_result;
		if (d_gameEngine.getGameMap() != null) {
			l_result = d_gameEngine.getGameMap().validateMap();
		} else {
			l_result = String.format("Cannot validate map");
		}
		return l_result;
	}
	
	public String printInvalidCommandMessage() {
		return "Invalid command in state " + this.getClass().getSimpleName();
	}
}
