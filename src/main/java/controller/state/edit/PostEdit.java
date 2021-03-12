package controller.state.edit;

import controller.GameEngine;
import controller.GameStarter;

public class PostEdit extends EditPhase {

	PostEdit(GameStarter p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String editMap(String p_fileName) {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String editContinent(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String editCountry(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String editNeighbor(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String saveMap(String p_fileName) {
		String l_result;
		
			String l_validMsg = d_gameEngine.getGameMap().validateMap();
			Boolean l_validateResult = d_gameEngine.getGameMap().getValidateStatus();
			if (!l_validateResult) {
				l_result = l_validMsg;
				return l_result;
			}

			l_result = d_gameEngine.getGameMap().saveMap(p_fileName);
			
		
		return l_result;
	}

}
