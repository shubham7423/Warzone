package controller.state.gamephase;

import controller.GameStarter;
import entities.GameMap;

public class PreLoad extends GamePhase {
	
	public PreLoad(GameStarter p_gameEngine) {
		super(p_gameEngine);

	}
	
	@Override
	public String loadMap(String p_fileName) {
		String l_result;
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
		return l_result;
	}

	@Override
	public String gamePlayer(String[] p_commandSplitted) {
		return String.format("Map Not Loaded yet");
	}

	@Override
	public String addPlayer() {
		// TODO Auto-generated method stub
		return String.format("Map Not Loaded yet");
	}

	@Override
	public String removePlayer() {
		// TODO Auto-generated method stub
		return String.format("Map Not Loaded yet");
	}

	@Override
	public String deployPhase() {
		// TODO Auto-generated method stub
		return String.format("Map Not Loaded yet");
	}

	@Override
	public String assignCountries() {
		// TODO Auto-generated method stub
		return String.format("Map Not Loaded yet");
	}

	@Override
	public String showMap() {
		return String.format("Map Not Loaded yet");
	}
}
