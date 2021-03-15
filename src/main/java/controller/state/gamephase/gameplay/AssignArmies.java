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
//			System.out.println(d_gameEngine.a);
			for (Player l_player : d_gameEngine.d_players.values()) {
				l_player.setNumberOfArmies();
			}
			System.out.println("Armies assigned");
			return "Assigned armies to players";
		}
		finally {
			d_gameEngine.setPhase(new IssueOrders(d_gameEngine));
			d_gameEngine.getPhase().issueOrders();
		}
	}
	
	public String showMap() {
		return printInvalidCommandMessage();
	}
	
	public String deploy(String[] p_splittedCommand) {
		return printInvalidCommandMessage();
	}
	
	@Override
	public String issueOrders() {
		return printInvalidCommandMessage();
	}
}
