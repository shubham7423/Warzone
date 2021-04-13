package controller.state.gamephase.gameplay;

import java.util.ArrayList;

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
	 * function to print invalid command message as this cannot be used in this phase
	 * @param p_maps list of maps
	 * @param p_players list of players
	 * @param p_games number of games to be played
	 * @param p_turns number of turns allowed
	 */
	public String tournament(ArrayList<String> p_maps, ArrayList<String> p_players, int p_games, int p_turns) {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command message as this cannot be used in this phase
	 */
	public String returnWinner() {
		return printInvalidCommandMessage();
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
