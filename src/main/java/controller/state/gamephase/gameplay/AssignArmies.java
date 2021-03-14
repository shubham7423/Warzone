package controller.state.gamephase.gameplay;

import java.util.HashSet;

import controller.GameStarter;
import entities.Player;
import entities.orders.Orders;
import entities.orders.ShowMap;

public class AssignArmies extends GamePlay {

	public AssignArmies(GameStarter p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String assignArmies() {
		// TODO Auto-generated method stub
		try {
			for (Player l_player : d_gameEngine.d_players.values()) {
				l_player.setNumberOfArmies();
			}
			System.out.println("Armies assigned");
			return "Assigned armies to players";
		}
		finally {
			deployPhase();
		}
	}
	
	@Override
	public String deployPhase() {
		// TODO Auto-generated method stub
		int l_currentPlayer = 0;
		Orders l_playerOrders = null;
		HashSet<String> l_playersCompleted = new HashSet<>();
		System.out.println("\nDeploy phase entered");
		System.out.println(org.apache.commons.lang3.StringUtils.repeat("-", 20));
		while (l_playersCompleted.size() < d_gameEngine.d_playerName.size()) {
			if (d_gameEngine.d_players.get(d_gameEngine.d_playerName.get(l_currentPlayer)).getNumberOfArmies() > 0) {
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
		return "Deploy completed";
	}
}
