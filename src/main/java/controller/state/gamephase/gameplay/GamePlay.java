package controller.state.gamephase.gameplay;

import controller.GameEngine;
import controller.state.gamephase.GamePhase;

/**
 * GamePlay class that inherits GamePhase to support gameplay compatible
 * commands
 */
public abstract class GamePlay extends GamePhase {

	/**
	 * constructor method that takes game engine object from the parent class
	 * 
	 * @param p_gameEngine object of the game engine
	 */
	public GamePlay(GameEngine p_gameEngine) {
		super(p_gameEngine);
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_fileName name of the map file used for loading
	 * @return string to print the invalid command message
	 */
	@Override
	public String loadMap(String p_fileName) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	@Override
	public String gamePlayer(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_playerName name of the player to add to the game
	 * @return string to print the invalid command message
	 */
	@Override
	public String addPlayer(String p_playerName) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_playerName name of the player to remove from the game
	 * @return string to print the invalid command message
	 */
	@Override
	public String removePlayer(String p_playerName) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @return string to print the invalid command message
	 */
	@Override
	public String assignCountries() {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_playerName name of the winner of the game
	 * @return string to print the invalid command message
	 */
	@Override
	public String printWinner(String p_playerName) {
		return printInvalidCommandMessage();
	}
}
