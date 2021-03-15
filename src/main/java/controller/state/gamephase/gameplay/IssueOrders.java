package controller.state.gamephase.gameplay;

import java.util.HashSet;

import controller.GameEngine;
import entities.Player;
import entities.orders.Orders;
import entities.orders.ShowMap;

public class IssueOrders extends GamePlay {

	public IssueOrders(GameEngine p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String assignArmies() {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}
	
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

	@Override
	public String issueOrders() {
		// TODO Auto-generated method stub
		int l_currentPlayer = 0;
		Orders l_playerOrders = null;
		HashSet<String> l_playersCompleted = new HashSet<>();
		System.out.println("\nDeploy phase entered");
		System.out.println(org.apache.commons.lang3.StringUtils.repeat("-", 20));
		for(Player l_player: d_gameEngine.d_players.values()) {
//			System.out.println("Assigned" + d_gameEngine.d_userCommand);
			l_player.setUserCommand(d_gameEngine.d_userCommand);
		}
		while (l_playersCompleted.size() < d_gameEngine.d_playerName.size()) {
			if (d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getNumberOfArmies() > 0) {
				System.out.println(d_gameEngine.getGameMap().showMapPlay());
				System.out.println("Player " + d_gameEngine.d_playerName.get(l_currentPlayer) + "'s turn");
				System.out.println("Number of armies left: "
						+ d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getNumberOfArmies());
				d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).issueOrder();
				l_playerOrders = d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).nextOrder();
				System.out.println(l_playerOrders.executeOrder(d_gameEngine));
			} else {
				l_playersCompleted.add(d_gameEngine.d_playerName.get(l_currentPlayer));
			}

			if (!(l_playerOrders instanceof ShowMap)) {
				++l_currentPlayer;
			}
			if (l_currentPlayer == d_gameEngine.d_playerName.size()) {
				l_currentPlayer = 0;
			}
		}
		d_gameEngine.setPhase(new AssignArmies(d_gameEngine));
		d_gameEngine.getPhase().assignArmies();
		return "Deploy completed";
	}
	
	public String showMap() {
		return printInvalidCommandMessage();
	}

	@Override
	public void next() {
		
	}
}
