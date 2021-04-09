package controller.state.gamephase.gamesetup;

import java.util.ArrayList;
import java.util.HashMap;

import controller.GameEngine;
import controller.state.gamephase.GamePhase;
import entities.Player;
import strategy.Aggresive;
import strategy.Benevolent;
import strategy.Cheater;
import strategy.PlayerStrategy;
import strategy.RandomPlayer;

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

	public String saveGame() {
		return null;
	}

	public String returnWinner() {
		return printInvalidCommandMessage();
	}

	public String tournament(ArrayList<String> p_maps, ArrayList<String> p_players, int p_games, int p_turns) {
		HashMap<String, ArrayList<String>> l_winnersMap = new HashMap<>();
		ArrayList<String> l_gameWinner;
		for (String l_map : p_maps) {
			l_gameWinner = new ArrayList<>();
			for (int l_i = 0; l_i < p_games; l_i++) {
				d_gameEngine.setMaxTurns(p_turns);
				d_gameEngine.setPhase(new PreLoad(d_gameEngine));
				d_gameEngine.getPhase().loadMap(l_map);
				for (String l_player : p_players) {
					d_gameEngine.getPhase().addPlayer(l_player);
					Player l_playerObj = d_gameEngine.d_players.get(l_player);
					switch (l_player) {
					case "Random":
						l_playerObj.setStrategy(new RandomPlayer(l_playerObj, d_gameEngine));
						break;

					case "Aggresive":
						l_playerObj.setStrategy(new Aggresive(l_playerObj, d_gameEngine));
						break;

					case "Benevolent":
						l_playerObj.setStrategy(new Benevolent(l_playerObj, d_gameEngine));
						break;

					case "Cheater":
						l_playerObj.setStrategy(new Cheater(l_playerObj, d_gameEngine));
						break;
					}
				}
				d_gameEngine.getPhase().assignCountries();
				l_gameWinner.add(d_gameEngine.getPhase().returnWinner());
				d_gameEngine = new GameEngine();
			}
			l_winnersMap.put(l_map, l_gameWinner);
		}
//		System.out.println(l_winnersMap);
		return l_winnersMap.toString();
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
