package controller.state.gamephase.gameplay;

import controller.GameStarter;
import controller.state.gamephase.GamePhase;
import controller.state.gamephase.gamesetup.PostLoad;
import entities.GameMap;

public abstract class GamePlay extends GamePhase {

	public GamePlay(GameStarter p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}
	
	public String loadMap(String p_fileName) {
		return printInvalidCommandMessage();
	}
	
	@Override
	public String gamePlayer(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	@Override
	public String addPlayer(String p_playerName) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}

	@Override
	public String removePlayer(String p_playerName) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}
	
	public String assignCountries() {
		return printInvalidCommandMessage();
	}

}
