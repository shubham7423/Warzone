package controller.state.gamephase.gamesetup;

import controller.GameStarter;
import controller.state.gamephase.GamePhase;

public abstract class GameSetup extends GamePhase {

	public GameSetup(GameStarter p_gameEngine) {
		super(p_gameEngine);
	}
	
	@Override
	public String deployPhase() {
		return String.format("Game not yet Setup");
	}

	@Override
	public String assignCountries() {
		return String.format("Game not yet Setu");
	}

}
