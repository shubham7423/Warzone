package controller.state.gamephase.gamesetup;

import controller.GameEngine;
import controller.state.gamephase.GamePhase;

public abstract class GameSetup extends GamePhase {

	public GameSetup(GameEngine p_gameEngine) {
		super(p_gameEngine);
	}
	
	@Override
	public String assignArmies() {
		return printInvalidCommandMessage();
	}
	
	@Override
	public String issueOrders() {
		return String.format("Game not yet Setup");
	}

	@Override
	public String assignCountries() {
		return String.format("Game not yet Setu");
	}
	
	public String deploy(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

}
