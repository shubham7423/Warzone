package controller.state.edit;

import controller.GameEngine;
import controller.state.Phase;

public abstract class EditPhase extends Phase {

	EditPhase(GameEngine p_gameEngine) {
		super(p_gameEngine);
		
	}
	
	abstract public void editMap();
	
	abstract public void editContinent();
	
	abstract public void editCountry();
	
	abstract public void editNeighbor();
	
	abstract public void saveMap();
	
	public void validateMap() {
		
	}
	
	public void showMap() {
		
	}
	
	public void deployPhase() {
		printInvalidCommandMessage();
	}
	
	public void assignCountries() {
		printInvalidCommandMessage();
	}
	
	public void gamePlayer() {
		printInvalidCommandMessage();
	}
	
	public void loadMap() {
		printInvalidCommandMessage();
		
	}
	
//	abstract public void checkContinentOwnership();
	
//	abstract public void assignArmies();
	

}
