package controller.state.edit;

import controller.GameEngine;
import controller.GameEngine;
import controller.state.Phase;

public abstract class EditPhase extends Phase {

	public EditPhase(GameEngine p_gameEngine) {
		super(p_gameEngine);
		
	}
	
	abstract public String editMap(String p_fileName);
	
	abstract public String editContinent(String[] p_commandSplitted);
	
	abstract public String editCountry(String[] p_commandSplitted);
	
	abstract public String editNeighbor(String[] p_commandSplitted);
	
	abstract public String saveMap(String p_fileName);
	
	public String assignArmies() {
		return printInvalidCommandMessage();
	}
	
	public String addPlayer(String p_playerName) {
		return printInvalidCommandMessage();
	}
	
	public String removePlayer(String p_playerName) {
		return printInvalidCommandMessage();
	}
	
	public String issueOrders() {
		return printInvalidCommandMessage();
	}
	
	public String assignCountries() {
		return printInvalidCommandMessage();
	}
	
	public String gamePlayer(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	public String loadMap(String p_fileName) {
		return printInvalidCommandMessage();
		
	}
	
	public String deploy(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	public String executeOrders() {
		return printInvalidCommandMessage();
	}
	
	public String checkContinentOwnership() {
		return printInvalidCommandMessage();
	}
	
//	abstract public void checkContinentOwnership();
	
//	abstract public void assignArmies();
	

}
