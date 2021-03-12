package controller.state;
import entities.GameMap;

import controller.GameEngine;
import controller.GameStarter;

public abstract class Phase {
	private GameStarter d_gameEngine;
	
	public Phase(GameStarter p_gameEngine){
		d_gameEngine = p_gameEngine;
	}
	
	abstract public String editMap(String p_fileName);

	abstract public String loadMap();
	
	abstract public String editContinent();
	
	abstract public String editCountry();
	
	abstract public String editNeighbor();
	
	abstract public String saveMap();
	
	abstract public String gamePlayer(String[] p_commandSplitted);
	
//	abstract public void addPlayer();
	
//	abstract public void removePlayer();
	
//	abstract public GameMap getGameMap();
	
	abstract public String validateMap();
	
	abstract public String deployPhase();
	
	abstract public String assignCountries();
	
//	abstract public void checkContinentOwnership();
	
//	abstract public void assignArmies();
	
	abstract public String showMap();
	
	public String printInvalidCommandMessage() {
		return "Invalid command in state " + this.getClass().getSimpleName();
	}
}
