package controller.state;
import entities.GameMap;

import controller.GameEngine;
import controller.GameEngine;

/**
 * An abstract class for the Phase implementation of state pattern
 *
 */
public abstract class Phase {
	public GameEngine d_gameEngine;
	
	/**
	 * Constructor method to initialize the phase
	 * @param p_gameEngine object of game Engine
	 */
	public Phase(GameEngine p_gameEngine){
		d_gameEngine = p_gameEngine;
	}
	
	abstract public String editMap(String p_fileName);
	
	abstract public String editContinent(String[] p_commandSplitted);
	
	abstract public String editCountry(String[] p_commandSplitted);
	
	abstract public String editNeighbor(String[] p_commandSplitted);
	
	abstract public String saveMap(String p_fileName);
	
	abstract public String loadMap(String p_fileName);
	
	abstract public String gamePlayer(String[] p_commandSplitted);
	
	abstract public String addPlayer(String p_playerName);
	
	abstract public String removePlayer(String p_playerName);
	
//	abstract public GameMap getGameMap();
	
	abstract public String assignCountries();
	
	abstract public String assignArmies();
	
	abstract public String issueOrders();
	
	abstract public String deploy(String[] p_commandSplitted);
	
	abstract public String advance(String[] p_commandSplitted);
	
	abstract public String bomb(String[] p_commandSplitted);
	
	abstract public String blockade(String[] p_commandSplitted);
	
	abstract public String airlift(String[] p_commandSplitted);
	
	abstract public String diplomacy(String[] p_commandSplitted);
	
	abstract public void next();
	
	abstract public String executeOrders();
	
	abstract public String checkContinentOwnership();
	
	public String showMap() {	
		return d_gameEngine.getGameMap().showMapEdit();
	}
	
	/**
	 * Validation method to check validity of the game map in the phases
	 * @return the validation string representing the validity or not
	 */
	public String validateMap() {
		String l_result;
		if (d_gameEngine.getGameMap() != null) {
			l_result = d_gameEngine.getGameMap().validateMap();
		} else {
			l_result = String.format("Cannot validate map");
		}
		return l_result;
	}
	
	/**
	 * Common method to print invalid command message when a command is executed in the wrong phase
	 * @return string containing the message
	 */
	public String printInvalidCommandMessage() {
		return "Invalid command in state " + this.getClass().getSimpleName();
	}
}
