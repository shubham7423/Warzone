package controller.state;
import entities.GameMap;

import controller.GameEngine;

public abstract class Phase {
	private GameEngine d_gameEngine;
	
	Phase(GameEngine p_gameEngine){
		d_gameEngine = p_gameEngine;
	}
	
	abstract public String editMap();

	abstract public String loadMap();
	
	abstract public String editContinent();
	
	abstract public String editCountry();
	
	abstract public String editNeighbor();
	
	abstract public String saveMap();
	
	abstract public String gamePlayer();
	
	abstract public String addPlayer();
	
	abstract public String removePlayer();
	
	abstract public GameMap getGameMap();
	
	abstract public String validateMap();
	
	abstract public void deployPhase();
	
	abstract public String assignCountries();
	
	abstract public void checkContinentOwnership();
	
	abstract public void assignArmies();
	
	abstract public String showMap();
	
	public void printInvalidCommandMessage() {
		System.out.println("Invalid command in state " + this.getClass().getSimpleName() );
	}
}
