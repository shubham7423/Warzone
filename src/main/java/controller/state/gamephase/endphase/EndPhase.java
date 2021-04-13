package controller.state.gamephase.endphase;

import java.util.ArrayList;

import controller.GameEngine;
import controller.state.gamephase.GamePhase;

/**
 * This is the class that inherits GamePhase class whose function is to print
 * the winner of the game
 */
public class EndPhase extends GamePhase {
	private String d_winner;

	/**
	 * constructor method for the class that takes in the object of GameEngine
	 * 
	 * @param p_gameEngine object of GameEngine class
	 */
	public EndPhase(GameEngine p_gameEngine) {
		super(p_gameEngine);
	}

	/**
	 * Function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_fileName name of game file that is to be saved
	 * @return string to print the invalid command message
	 */
	@Override
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
	@Override
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
	@Override
	public String tournament(ArrayList<String> p_maps, ArrayList<String> p_players, int p_games, int p_turns) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_fileName name of the file used to load
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
	 * @param p_playerName name of the player to add
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
	 * @param p_playerName name of the player to remove
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
	 * @return string to print the invalid command message
	 */
	@Override
	public String assignArmies() {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @return string to print the invalid command message
	 */
	@Override
	public String issueOrders() {
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
	@Override
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public String diplomacy(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print the winner of the game
	 * 
	 * @param p_playerName name of the player that has won
	 * @return string to print winner of the game
	 */
	@Override
	public String printWinner(String p_playerName) {
		d_winner = p_playerName;
		return "\nWinner:  " + p_playerName;
	}

	/**
	 * Function to return the winner of the game
	 * 
	 * @return d_winner returns the winner of the game
	 */
	@Override
	public String returnWinner() {
		return d_winner;
	}

	/**
	 * function to proceed to the next phase of the game but as this is the final
	 * phase of the game this function does not execute
	 */
	@Override
	public void next() {
		System.exit(0);
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @return string to print the invalid command message
	 */
	@Override
	public String executeOrders() {
		return printInvalidCommandMessage();
	}

	/**
	 * function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @return string to print the invalid command message
	 */
	@Override
	public String checkContinentOwnership() {
		return printInvalidCommandMessage();
	}
}
