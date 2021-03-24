package controller.state.edit;

import controller.GameEngine;
import controller.state.gamephase.gamesetup.PreLoad;
import controller.GameEngine;

/**
 * PostEdit is inherited from the EditPhase class to support commands valid in post editing phase
 *
 */
public class PostEdit extends EditPhase {

	/**
	 * constructor method that takes game engine object from the parent class 
	 * @param p_gameEngine object of game engine
	 */
	public PostEdit(GameEngine p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	/**
	 * as this command is not applicable in this phase we print invalid command string
	 * @param p_fileName name of the map file to use for editing
	 * @return string containing invalid command string
	 */
	@Override
	public String editMap(String p_fileName) {
		return printInvalidCommandMessage();
	}
  
	/**
	 * as this command is applicable in this phase we process the editContinent command
	 * @param p_commandSplitted splitted command parts to execute
	 * @return string indicating the add or remove command result
	 */
	@Override
	public String editContinent(String[] p_commandSplitted) {
		String l_result;
		if ("-add".equals(p_commandSplitted[0])) {
			l_result = d_gameEngine.getGameMap().addContinent(Integer.parseInt(p_commandSplitted[1]),
					Integer.parseInt(p_commandSplitted[2]));
		} else {
			l_result = d_gameEngine.getGameMap().removeContinent(Integer.parseInt(p_commandSplitted[1]));
		}
		d_gameEngine.d_logEntryBuffer.setString(l_result);
		return l_result;
	}

	/**
	 * as this command is applicable in this phase we process the editCountry command
	 * @param p_commandSplitted splitted command parts to execute
	 * @return string indicating the add or remove command result
	 */
	@Override
	public String editCountry(String[] p_commandSplitted) {
		String l_result;

		if ("-add".equals(p_commandSplitted[0])) {
			l_result = d_gameEngine.getGameMap().addCountry(Integer.parseInt(p_commandSplitted[1]),
					Integer.parseInt(p_commandSplitted[2]));
		} else {
			l_result = d_gameEngine.getGameMap().removeCountry(Integer.parseInt(p_commandSplitted[1]));
		}
		d_gameEngine.d_logEntryBuffer.setString(l_result);
		return l_result;
	}

	/**
	 * as this command is applicable in this phase we process the editNeighbor command
	 * @param p_commandSplitted splitted command parts to execute
	 * @return string indicating the add or remove command result
	 */
	@Override
	public String editNeighbor(String[] p_commandSplitted) {
		String l_result;
		if ("-add".equals(p_commandSplitted[0])) {
			l_result = d_gameEngine.getGameMap().addNeighbor(Integer.parseInt(p_commandSplitted[1]),
					Integer.parseInt(p_commandSplitted[2]));
		} else {
			l_result = d_gameEngine.getGameMap().removeNeighbor(Integer.parseInt(p_commandSplitted[1]),
					Integer.parseInt(p_commandSplitted[2]));
		}
		d_gameEngine.d_logEntryBuffer.setString(l_result);
		return l_result;
	}

	/**
	 * as this command is applicable in this phase we process the saveMap command
	 * @param p_fileName name of the map file for saving
	 * @return string indicating the result of the map saving whether it was successful or not
	 */
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
		d_gameEngine.d_logEntryBuffer.setString(l_result);
		return l_result;
	}

	/**
	 * function to proceed to the next phase of the game
	 */
	@Override
	public void next() {
		d_gameEngine.setPhase(new PreLoad(d_gameEngine));
	}
}
