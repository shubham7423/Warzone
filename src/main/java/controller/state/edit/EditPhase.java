package controller.state.edit;

import controller.GameEngine;
import controller.GameEngine;
import controller.state.Phase;

/**
 * EditPhase is inherited from the Phase class to support commands valid in editing phase
 */
public abstract class EditPhase extends Phase {
	
	/**
	 * constructor method that take game engine object from the parent class 
	 * @param p_gameEngine object of game engine
	 */
	public EditPhase(GameEngine p_gameEngine) {
		super(p_gameEngine);
		
	}
	
	/**
	 * abstract function that can be used in edit phase of the command to edit map
	 * @param p_fileName name of the map file used for editing
	 */
	abstract public String editMap(String p_fileName);
	
	/**
	 * abstract function that can be used in edit phase of the command for adding and removing continents
	 * @param p_commandSplitted splitted command parts used for execution of command
	 */
	abstract public String editContinent(String[] p_commandSplitted);
	
	/**
	 * abstract function that can be used in edit phase of the command for adding and removing countries
	 * @param p_commandSplitted splitted command parts used for execution of command
	 */
	abstract public String editCountry(String[] p_commandSplitted);
	
	/**
	 * abstract function that can be used in edit phase of the command for adding and removing neighbors
	 * @param p_commandSplitted splitted command parts used for execution of command
	 */
	abstract public String editNeighbor(String[] p_commandSplitted);
	
	/**
	 * abstract function that can be used in edit phase of the command to save map
	 * @param p_fileName name of the map file used for saving
	 */
	abstract public String saveMap(String p_fileName);
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @return string to print the invalid command message
	 */
	public String assignArmies() {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @param p_playerName name of the player to add
	 * @return string to print the invalid command message
	 */
	public String addPlayer(String p_playerName) {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @param p_playerName name of the player to remove
	 * @return string to print the invalid command message
	 */
	public String removePlayer(String p_playerName) {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @return string to print the invalid command message
	 */
	public String issueOrders() {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @return string to print the invalid command message
	 */
	public String assignCountries() {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String gamePlayer(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @param p_fileName name of the map file used for loading
	 * @return string to print the invalid command message
	 */
	public String loadMap(String p_fileName) {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String deploy(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @return string to print the invalid command message
	 */
	public String executeOrders() {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @return string to print the invalid command message
	 */
	public String checkContinentOwnership() {
		return printInvalidCommandMessage();
	}
	
//	abstract public void checkContinentOwnership();
	
//	abstract public void assignArmies();
	

}
