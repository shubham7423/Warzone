package controller.state.gamephase.gamesetup;

import controller.GameEngine;
import entities.GameMap;

public class PreLoad extends GameSetup {
	
	public PreLoad(GameEngine p_gameEngine) {
		super(p_gameEngine);

	}
	
	@Override
	public String loadMap(String p_fileName) {
		String l_result;
		d_gameEngine.setGameMap(new GameMap());
		l_result = d_gameEngine.getGameMap().loadMap(p_fileName);
		if (l_result.equals(String.format("Map \"%s\" cannot be loaded", p_fileName))) {
			return l_result;
		}
		String l_validMsg = d_gameEngine.getGameMap().validateMap();
		Boolean l_validateResult = d_gameEngine.getGameMap().getValidateStatus();
		if (!l_validateResult) {
			l_result = l_validMsg;
			d_gameEngine.setGameMap(new GameMap());
			return l_result;
		}
		d_gameEngine.setPhase(new PostLoad(d_gameEngine));
		return l_result;
	}

	@Override
	public String gamePlayer(String[] p_commandSplitted) {
		return String.format("Map Not Loaded yet");
	}

	@Override
	public String addPlayer(String p_playerName) {
		// TODO Auto-generated method stub
		return String.format("Map Not Loaded yet");
	}

	@Override
	public String removePlayer(String p_playerName) {
		// TODO Auto-generated method stub
		return String.format("Map Not Loaded yet");
	}
}
