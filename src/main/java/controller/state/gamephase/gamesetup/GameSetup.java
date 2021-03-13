package controller.state.gamephase.gamesetup;

import controller.GameStarter;
import controller.state.gamephase.GamePhase;

public abstract class GameSetup extends GamePhase {

	public GameSetup(GameStarter p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String deployPhase() {
		// TODO Auto-generated method stub
		return String.format("Game not yet Setup");
	}

	@Override
	public String assignCountries() {
		// TODO Auto-generated method stub
		return String.format("Game not yet Setu");
	}

}
