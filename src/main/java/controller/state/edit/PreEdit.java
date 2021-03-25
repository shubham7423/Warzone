package controller.state.edit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import controller.GameEngine;
import controller.GameEngine;
import entities.GameMap;

/**
 * PreEdit is inherited from the EditPhase class to support commands valid in
 * pre editing phase
 *
 */
public class PreEdit extends EditPhase {

	/**
	 * constructor method that takes game engine object from the parent class
	 * 
	 * @param p_gameEngine object of game engine
	 */
	public PreEdit(GameEngine p_gameEngine) {
		super(p_gameEngine);
	}

	/**
	 * as this command is applicable in this phase we process the editMap command
	 * 
	 * @param p_fileName name of the map file for editing
	 * @return string indicating the result of the map editing whether it is
	 *         available to edit or not
	 */
	@Override
	public String editMap(String p_fileName) {
		String l_result;
		l_result = d_gameEngine.getGameMap().loadMap(p_fileName);
		if (l_result.equals(String.format("Map \"%s\" cannot be loaded", p_fileName))) {
			return l_result;
		}
		if (!Files.exists(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_fileName))) {
			try {
				Files.createDirectories(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps"));
				Files.createFile(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_fileName));
			} catch (IOException p_e) {
				p_e.printStackTrace();
			}
		}
		l_result = String.format("Map \"%s\" ready for edit", p_fileName);
		d_gameEngine.d_logEntryBuffer.setString("Map Edit Phase Entered");
		d_gameEngine.d_logEntryBuffer.setString(l_result);
		next();
		return l_result;
	}

	/**
	 * validate map cannot be used in this phase
	 * 
	 * @return string to output the result
	 */
	public String validateMap() {
		return String.format("Map not loaded yet.");
	}

	/**
	 * as this command is not applicable in this phase we print invalid command
	 * string
	 * 
	 * @param p_commandSplitted splitted command parts to execute
	 * @return string containing invalid command string
	 */
	@Override
	public String editContinent(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * as this command is not applicable in this phase we print invalid command
	 * string
	 * 
	 * @param p_commandSplitted splitted command parts to execute
	 * @return string containing invalid command string
	 */
	@Override
	public String editCountry(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * as this command is not applicable in this phase we print invalid command
	 * string
	 * 
	 * @param p_commandSplitted splitted command parts to execute
	 * @return string containing invalid command string
	 */
	@Override
	public String editNeighbor(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * as this command is not applicable in this phase we print invalid command
	 * string
	 * 
	 * @param p_fileName name of the map file for saving
	 * @return string containing invalid command string
	 */
	@Override
	public String saveMap(String p_fileName) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to proceed to the next phase of the editing phase of the game
	 */
	@Override
	public void next() {
		d_gameEngine.setPhase(new PostEdit(d_gameEngine));
	}

}
