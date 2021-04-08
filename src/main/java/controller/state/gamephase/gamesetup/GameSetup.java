package controller.state.gamephase.gamesetup;

import controller.GameEngine;
import controller.state.gamephase.GamePhase;

/**
 * Abstract class of game setup the represents the initial process of enetering
 * the gameplay
 */
public abstract class GameSetup extends GamePhase {

	/**
	 * constructor method of the class that takes game engine object from the parent
	 * class
	 * 
	 * @param p_gameEngine object of the game engine
	 */
	public GameSetup(GameEngine p_gameEngine) {
		super(p_gameEngine);
	}
	
	public String loadGame() {
		return null;
	}
	
	public String saveGame(String p_fileName) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @return string to print the invalid command message
	 */
	@Override
	public String assignArmies() {
		return printInvalidCommandMessage();
	}

	/**
	 * Function to allow users to provide orders
	 * 
	 * @return string conveying that game has not yet been setup to use this command
	 */
	@Override
	public String issueOrders() {
		return String.format("Game not yet Setup");
	}

	/**
	 * function to assign countries to the players in the game
	 * 
	 * @return string conveying that game has not yet been setup to use this command
	 */
	@Override
	public String assignCountries() {
		return String.format("Game not yet Setup");
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String deploy(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String advance(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String airlift(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String bomb(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String blockade(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String diplomacy(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @return string to print the invalid command message
	 */
	public String executeOrders() {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @return string to print the invalid command message
	 */
	public String checkContinentOwnership() {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_playerName name of the winner of the game
	 * @return string to print the invalid command message
	 */
	public String printWinner(String p_playerName) {
		return printInvalidCommandMessage();
	}
}
