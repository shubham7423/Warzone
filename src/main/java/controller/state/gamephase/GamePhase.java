package controller.state.gamephase;

import controller.GameStarter;
import controller.state.Phase;

public abstract class GamePhase extends Phase {

	public GamePhase(GameStarter p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}
	
	abstract public String loadMap(String p_fileName);
	
	abstract public String gamePlayer(String[] p_commandSplitted);
	
	abstract public String addPlayer();
	
	abstract public String removePlayer();
	
	public String editMap(String p_fileName) {
		return printInvalidCommandMessage();
	}
	
	public String editContinent(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	public String editCountry(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	public String editNeighbor(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	public String saveMap(String p_fileName) {
		return printInvalidCommandMessage();
	}

}
