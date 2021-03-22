package controller.state.gamephase;

import controller.GameEngine;
import controller.state.Phase;

/**
 * GamePhase is inherited from the Phase class to support commands valid in game phase(general commands)
 */
public abstract class GamePhase extends Phase {

	/**
	 * constructor method that takes game engine object from the parent class 
	 * @param p_gameEngine object of game engine
	 */
	public GamePhase(GameEngine p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * function to save map
	 * @return shows the current state of the map
	 */
	public String showMap() {
		return d_gameEngine.getGameMap().showMapPlay();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @param p_fileName name of the file used for editing
	 * @return string to print the invalid command message
	 */
	public String editMap(String p_fileName) {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String editContinent(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String editCountry(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String editNeighbor(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @param p_fileName name of the map file used for saving
	 * @return string to print the invalid command message
	 */
	public String saveMap(String p_fileName) {
		return printInvalidCommandMessage();
	}
	
	public String printWinner(String p_playerName) {
		return printInvalidCommandMessage();
	}

}
