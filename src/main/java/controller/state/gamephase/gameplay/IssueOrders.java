package controller.state.gamephase.gameplay;

import java.util.ArrayList;
import java.util.HashSet;

import controller.GameEngine;
import entities.Player;
import strategy.Cheater;
import strategy.HumanPlayer;

/**
 * IssueOrder class that inherits GamePlay interface to support functions during
 * this command
 */
public class IssueOrders extends GamePlay {

	/**
	 * constructor method that takes game engine object from the parent class
	 * 
	 * @param p_gameEngine object of the game engine
	 */
	public IssueOrders(GameEngine p_gameEngine) {
		super(p_gameEngine);
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
	 * function to support deploy command for further processing
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return merged string of the deploy order provided
	 */
	@Override
	public String deploy(String[] p_commandSplitted) {
		StringBuilder l_sb = new StringBuilder();
		for (String l_str : p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}

	/**
	 * function to support advance command for further processing
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return merged string of the advance order provided
	 */
	@Override
	public String advance(String[] p_commandSplitted) {
		StringBuilder l_sb = new StringBuilder();
		for (String l_str : p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}

	/**
	 * function to support airlift command for further processing
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return merged string of the airlift order provided
	 */
	@Override
	public String airlift(String[] p_commandSplitted) {
		StringBuilder l_sb = new StringBuilder();
		for (String l_str : p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}

	/**
	 * function to support bomb command for further processing
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return merged string of the bomb order provided
	 */
	@Override
	public String bomb(String[] p_commandSplitted) {
		StringBuilder l_sb = new StringBuilder();
		for (String l_str : p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}

	/**
	 * function to support blockade command for further processing
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return merged string of the blockade order provided
	 */
	@Override
	public String blockade(String[] p_commandSplitted) {
		StringBuilder l_sb = new StringBuilder();
		for (String l_str : p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}

	/**
	 * function to support diplomacy command for further processing
	 * 
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return merged string of the diplomacy order provided
	 */
	@Override
	public String diplomacy(String[] p_commandSplitted) {
		StringBuilder l_sb = new StringBuilder();
		for (String l_str : p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}

	/**
	 * function that takes player's and that adds to them to the orders queue
	 * 
	 * @return string to output result of issue orders
	 */
	@Override
	public String issueOrders() {
		int l_currentPlayer = 0;
		HashSet<String> l_playersCompleted = new HashSet<>();
		System.out.println("\nIssue orders phase entered");
		d_gameEngine.d_logEntryBuffer.setString("Issue orders phase entered");
		System.out.println(org.apache.commons.lang3.StringUtils.repeat("-", 20));
		for (Player l_player : d_gameEngine.d_players.values()) {
			l_player.setIsCommit(false);
			l_player.d_negotiatedPlayerNames = new ArrayList<String>();
			l_player.d_isConquered = false;
		}
		
		while (l_playersCompleted.size() < d_gameEngine.d_playerName.size()) {
			if (!d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getIsCommit()) {
				System.out.println(d_gameEngine.getGameMap().showMapPlay());
				System.out.println("Player " + d_gameEngine.d_playerName.get(l_currentPlayer) + "'s turn");
				d_gameEngine.d_logEntryBuffer
						.setString("Player " + d_gameEngine.d_playerName.get(l_currentPlayer) + "'s turn");
				System.out.println("Armies: " + d_gameEngine.d_players
						.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getNumberOfArmies());
				System.out.println("Cards: "
						+ d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).d_cardsOwned);
				d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).issueOrder();
//				if (!d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getIsCommit()) {
//					if(!(d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getPlayerBehaviour() instanceof Cheater)) {
//						d_gameEngine.addPlayerOrder(d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)));
//					}
//				}
				if(!d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getIsCommit()) {
					d_gameEngine.addPlayerOrder(d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)));
				}
				
				if(!(d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getPlayerBehaviour() instanceof HumanPlayer)) {
					d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).setIsCommit(true);
				}
			} else {
				l_playersCompleted.add(d_gameEngine.d_playerName.get(l_currentPlayer));
			}
			++l_currentPlayer;
			if (l_currentPlayer == d_gameEngine.d_playerName.size()) {
				l_currentPlayer = 0;
			}
		}
		next();
		d_gameEngine.d_logEntryBuffer.setString("Issue orders phase completed");
		d_gameEngine.getPhase().executeOrders();
		return "";
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
	public String showMap() {
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
	 * function to proceed to the next phase of the game
	 */
	@Override
	public void next() {
		d_gameEngine.setPhase(new ExecuteOrders(d_gameEngine));
	}
}
