package controller.state.edit;

import java.util.ArrayList;

import controller.GameEngine;
import controller.GameEngine;
import controller.state.Phase;

/**
 * EditPhase is inherited from the Phase class to support commands valid in
 * editing phase
 */
public abstract class EditPhase extends Phase {

	/**
	 * constructor method that takes game engine object from the parent class
	 * 
	 * @param p_gameEngine object of game engine
	 */
	public EditPhase(GameEngine p_gameEngine) {
		super(p_gameEngine);
	}

	/**
	 * Function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_fileName name of game file that is to be saved
	 * @return string to print the invalid command message
	 */
	public String saveGame(String p_fileName) {
		return printInvalidCommandMessage();
	}

	/**
	 * Function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_fileName name of game file that is to be loaded
	 * @return string to print the invalid command message
	 */
	public String loadGame(String p_fileName) {
		return printInvalidCommandMessage();
	}

	/**
	 * Function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_maps    list of maps in the tournament
	 * @param p_players list of players in the tournament
	 * @param p_games   number of games in the tournament
	 * @param p_turns   number of turns in the tournament
	 * @return string to print the invalid message
	 */
	public String tournament(ArrayList<String> p_maps, ArrayList<String> p_players, int p_games, int p_turns) {
		return printInvalidCommandMessage();
	}

	/**
	 * Function to print invalid command as the following command cannot be used in
	 * this place
	 * 
	 * @return string to print the invalid message
	 */
	public String returnWinner() {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @return string to print the invalid command message
	 */
	public String assignArmies() {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_playerName name of the player to add
	 * @return string to print the invalid command message
	 */
	public String addPlayer(String p_playerName) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_playerName name of the player to remove
	 * @return string to print the invalid command message
	 */
	public String removePlayer(String p_playerName) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @return string to print the invalid command message
	 */
	public String issueOrders() {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @return string to print the invalid command message
	 */
	public String assignCountries() {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String gamePlayer(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_fileName name of the map file used for loading
	 * @return string to print the invalid command message
	 */
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
	 * @param p_playerName name of the winner to be displayed
	 * @return string to print the invalid command message
	 */
	public String printWinner(String p_playerName) {
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
}
