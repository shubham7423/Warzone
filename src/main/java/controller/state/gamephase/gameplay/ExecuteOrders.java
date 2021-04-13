package controller.state.gamephase.gameplay;

import java.util.HashSet;
import java.util.Set;

import controller.GameEngine;
import controller.state.gamephase.endphase.EndPhase;
import entities.Player;
import entities.orders.Dummy;
import entities.orders.Orders;

/**
 * ExecuteOrder class that inherits GamePlay interface to support functions
 * during this command
 */
public class ExecuteOrders extends GamePlay {

	/**
	 * constructor method that takes game engine object from the parent class
	 * 
	 * @param p_gameEngine object of the game engine
	 */
	public ExecuteOrders(GameEngine p_gameEngine) {
		super(p_gameEngine);
	}

	/**
	 * Function to print invalid command as the following command cannot be used in
	 * this phase
	 * 
	 * @param p_fileName File that saves the game.
	 * @return string to print the invalid command message
	 */
	@Override
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
	public String diplomacy(String[] p_commandSplitted) {
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
	 * @return string to print the invalid command message
	 */
	@Override
	public String checkContinentOwnership() {
		return printInvalidCommandMessage();
	}

	/**
	 * function to execute order given by the player
	 * 
	 * @return string to output result of execution of order
	 */
	@Override
	public String executeOrders() {
		int l_numOrders = d_gameEngine.getPlayersOrderSize();
		int l_i = 0;
		String l_result;
		if (d_gameEngine.getCurrentTurn() == d_gameEngine.getMaxTurns()) {
			d_gameEngine.setPhase(new EndPhase(d_gameEngine));
			System.out.println(d_gameEngine.getPhase().printWinner("Draw"));
			d_gameEngine.d_logEntryBuffer.setString("Execution Complete");
			d_gameEngine.d_logEntryBuffer.setString(d_gameEngine.getPhase().printWinner("Draw"));
			return d_gameEngine.getPhase().printWinner("Draw");
		}
		System.out.println("\nExecution Phase Started");
		System.out.println(org.apache.commons.lang3.StringUtils.repeat("-", 20));
		d_gameEngine.d_logEntryBuffer.setString("Execution Phase Started");
		while (l_i < l_numOrders) {
			Player l_currentPlayer = d_gameEngine.getPlayerOrder();
			Orders l_order = l_currentPlayer.nextOrder();
			if (l_order instanceof Dummy) {
				++l_i;
				continue;
			}
			l_result = l_order.executeOrder(d_gameEngine);
			System.out.println(l_currentPlayer.getName() + ": " + l_result);
			d_gameEngine.d_logEntryBuffer.setString(l_currentPlayer.getName() + ": " + l_result);
			++l_i;
		}
		Set<Player> l_tempSet = new HashSet<>(d_gameEngine.d_players.values());
		for (Player l_player : l_tempSet) {
			if (l_player.getCountries().size() == 0) {
				d_gameEngine.d_playerName.remove(l_player.getName());
				d_gameEngine.d_players.remove(l_player.getName());
			}
		}
		if (d_gameEngine.d_playerName.size() == 1) {
			d_gameEngine.setPhase(new EndPhase(d_gameEngine));
			System.out.println(d_gameEngine.getPhase().printWinner(d_gameEngine.d_playerName.get(0)));
			d_gameEngine.d_logEntryBuffer.setString("Execution Complete");
			d_gameEngine.d_logEntryBuffer
					.setString(d_gameEngine.getPhase().printWinner(d_gameEngine.d_playerName.get(0)));
			return d_gameEngine.getPhase().printWinner(d_gameEngine.d_playerName.get(0));
		} else {
			d_gameEngine.incrementTurn();
			;
			System.out.println("\nExecution Complete\n");
			d_gameEngine.d_logEntryBuffer.setString("Execution Complete");
			next();
			d_gameEngine.getPhase().assignArmies();
		}
		return "";
	}

	/**
	 * function to proceed to the next phase of the game
	 */
	@Override
	public void next() {
		d_gameEngine.setPhase(new AssignArmies(d_gameEngine));
	}
}
