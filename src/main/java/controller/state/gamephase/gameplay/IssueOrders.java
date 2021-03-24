package controller.state.gamephase.gameplay;

import java.util.ArrayList;
import java.util.HashSet;

import controller.GameEngine;
import entities.Player;
import entities.orders.Orders;
import entities.orders.ShowMap;

/**
 * IssueOrder class that inherits GamePlay interface to support functions during this command
 */
public class IssueOrders extends GamePlay {

	/**
	 * constructor method that takes game engine object from the parent class 
	 * @param p_gameEngine object of the game engine
	 */
	public IssueOrders(GameEngine p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @return string to print the invalid command message
	 */
	@Override
	public String assignArmies() {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to deploy armies to other countries
	 * @return string to output result of deployment
	 */
	@Override
	public String deploy(String[] p_commandSplitted) {
		// TODO Auto-generated method stub
		StringBuilder l_sb = new StringBuilder();
//		System.out.println("DEPLOYYYY");
		for(String l_str: p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}
	
	public String advance(String[] p_commandSplitted) {
		StringBuilder l_sb = new StringBuilder();
//		System.out.println("DEPLOYYYY");
		for(String l_str: p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}
	
	public String airlift(String[] p_commandSplitted) {
		StringBuilder l_sb = new StringBuilder();
		for(String l_str: p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}
	
	public String bomb(String[] p_commandSplitted) {
		StringBuilder l_sb = new StringBuilder();
//		System.out.println("DEPLOYYYY");
		for(String l_str: p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}
	
	public String blockade(String[] p_commandSplitted) {
		StringBuilder l_sb = new StringBuilder();
//		System.out.println("DEPLOYYYY");
		for(String l_str: p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}
	
	public String diplomacy(String[] p_commandSplitted) {
		StringBuilder l_sb = new StringBuilder();
//		System.out.println("DEPLOYYYY");
		for(String l_str: p_commandSplitted) {
			l_sb.append(l_str);
			l_sb.append(" ");
		}
		return l_sb.toString();
	}

	/**
	 * function that takes player's and that adds to them to the orders queue
	 * @return string to output result of issue orders
	 */
	@Override
	public String issueOrders() {
		// TODO Auto-generated method stub
		int l_currentPlayer = 0;
		Orders l_playerOrders = null;
		HashSet<String> l_playersCompleted = new HashSet<>();
		System.out.println("\nIssue orders phase entered");
		d_gameEngine.d_logEntryBuffer.setString("Issue orders phase entered");
		System.out.println(org.apache.commons.lang3.StringUtils.repeat("-", 20));
		
//		while (l_playersCompleted.size() < d_gameEngine.d_playerName.size()) {
//			if (d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getNumberOfArmies() > 0) {
//				System.out.println(d_gameEngine.getGameMap().showMapPlay());
//				System.out.println("Player " + d_gameEngine.d_playerName.get(l_currentPlayer) + "'s turn");
//				System.out.println("Number of armies left: "
//						+ d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getNumberOfArmies());
//				d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).issueOrder();
//				l_playerOrders = d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).nextOrder();
//				System.out.println(l_playerOrders.executeOrder(d_gameEngine));
//			} else {
//				l_playersCompleted.add(d_gameEngine.d_playerName.get(l_currentPlayer));
//			}
//
//			if (!(l_playerOrders instanceof ShowMap)) {
//				++l_currentPlayer;
//			}
//			if (l_currentPlayer == d_gameEngine.d_playerName.size()) {
//				l_currentPlayer = 0;
//			}
//		}
		for(Player l_player: d_gameEngine.d_players.values()) {
			l_player.setIsCommit(false);
			l_player.d_negotiatedPlayerNames = new ArrayList<String>();
			l_player.d_isConquered = false;
		}
		while (l_playersCompleted.size() < d_gameEngine.d_playerName.size()) {
			if(!d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getIsCommit()) {
				System.out.println(d_gameEngine.getGameMap().showMapPlay());
				System.out.println("Player " + d_gameEngine.d_playerName.get(l_currentPlayer) + "'s turn");
				d_gameEngine.d_logEntryBuffer.setString("Player " + d_gameEngine.d_playerName.get(l_currentPlayer) + "'s turn");
				System.out.println("Armies: " + d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getNumberOfArmies());
				System.out.println("Cards: " + d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).d_cardsOwned);
				d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).issueOrder();
				if(!d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getIsCommit()) {
					d_gameEngine.addPlayerOrder(d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)));
				}
			} else {
				l_playersCompleted.add(d_gameEngine.d_playerName.get(l_currentPlayer));
			}
			++l_currentPlayer;
			if(l_currentPlayer == d_gameEngine.d_playerName.size()) {
				l_currentPlayer = 0;
			}
		}
		next();
		d_gameEngine.d_logEntryBuffer.setString("Deploy completed");
		d_gameEngine.getPhase().executeOrders();
		return "";
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @return string to print the invalid command message
	 */
	public String executeOrders() {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @return string to print the invalid command message
	 */
	public String showMap() {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @return string to print the invalid command message
	 */
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
