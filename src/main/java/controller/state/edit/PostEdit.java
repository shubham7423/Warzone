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
		String l_result;
			if (p_commandSplitted[0].equals("-add")) {
				l_result = d_gameEngine.getGameMap().addContinent(Integer.parseInt(p_commandSplitted[1]),
						Integer.parseInt(p_commandSplitted[2]));
			} else {
				l_result = d_gameEngine.getGameMap().removeContinent(Integer.parseInt(p_commandSplitted[1]));
			}
		return l_result;
	}

	@Override
	public String editCountry(String[] p_commandSplitted) {
		String l_result;
		
			if (p_commandSplitted[0].equals("-add")) {
				l_result = d_gameEngine.getGameMap().addCountry(Integer.parseInt(p_commandSplitted[1]),
						Integer.parseInt(p_commandSplitted[2]));
			} else {
				l_result = d_gameEngine.getGameMap().removeCountry(Integer.parseInt(p_commandSplitted[1]));
			}
		
		return l_result;
		}

	@Override
	public String editNeighbor(String[] p_commandSplitted) {
		String l_result;
			if (p_commandSplitted[0].equals("-add")) {
				l_result = d_gameEngine.getGameMap().addNeighbor(Integer.parseInt(p_commandSplitted[1]),
						Integer.parseInt(p_commandSplitted[2]));
			} else {
				l_result = d_gameEngine.getGameMap().removeNeighbor(Integer.parseInt(p_commandSplitted[1]),
						Integer.parseInt(p_commandSplitted[2]));
			}
		return l_result;
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
