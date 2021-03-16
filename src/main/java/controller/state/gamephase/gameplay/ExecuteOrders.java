package controller.state.gamephase.gameplay;

import controller.GameEngine;

public class ExecuteOrders extends GamePlay {

	public ExecuteOrders(GameEngine p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String assignArmies() {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}

	@Override
	public String issueOrders() {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}

	@Override
	public String deploy(String[] p_commandSplitted) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}
	
	public String checkContinentOwnership() {
		return printInvalidCommandMessage();
	}
	
	public String executeOrders() {
		int l_numOrders = d_gameEngine.getPlayeraOrderSize();
		int l_i = 0;
		while(l_i < l_numOrders) {
			System.out.println(d_gameEngine.getPlayerOrder().nextOrder().executeOrder(d_gameEngine));
			++l_i;
		}
		System.out.print("\nExecution Complete");
		next();
		d_gameEngine.getPhase().assignArmies();
		return "\nExecution completed";
	}

	@Override
	public void next() {
		d_gameEngine.setPhase(new AssignArmies(d_gameEngine));
	}

}
