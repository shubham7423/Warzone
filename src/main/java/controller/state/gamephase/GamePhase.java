package controller.state.gamephase;

import controller.GameStarter;
import controller.state.Phase;

public abstract class GamePhase extends Phase {

	public GamePhase(GameStarter p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}
	
	public String showMap() {
		return d_gameEngine.getGameMap().showMapPlay();
	}
	
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
