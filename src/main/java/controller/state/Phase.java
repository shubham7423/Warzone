package controller.state;
import entities.GameMap;

import controller.GameEngine;

public abstract class Phase {
	private GameEngine d_gameEngine;
	
	public Phase(GameEngine p_gameEngine){
		d_gameEngine = p_gameEngine;
	}
	
	abstract public void editMap();

	abstract public void loadMap();
	
	abstract public void editContinent();
	
	abstract public void editCountry();
	
	abstract public void editNeighbor();
	
	abstract public void saveMap();
	
	abstract public void gamePlayer();
	
//	abstract public void addPlayer();
	
//	abstract public void removePlayer();
	
//	abstract public GameMap getGameMap();
	
	abstract public void validateMap();
	
	abstract public void deployPhase();
	
	abstract public void assignCountries();
	
//	abstract public void checkContinentOwnership();
	
//	abstract public void assignArmies();
	
	abstract public void showMap();
	
	public void printInvalidCommandMessage() {
		System.out.println("Invalid command in state " + this.getClass().getSimpleName() );
	}
}
