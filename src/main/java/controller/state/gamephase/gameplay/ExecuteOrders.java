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
	
	public String advance(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	public String airlift(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	public String bomb(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	public String blockade(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
	}
	
	public String diplomacy(String[] p_commandSplitted) {
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
		String l_result;
		while(l_i < l_numOrders) {
			l_result = d_gameEngine.getPlayerOrder().nextOrder().executeOrder(d_gameEngine);
			System.out.println(l_result);
			d_gameEngine.d_logEntryBuffer.setString(l_result);
			++l_i;
		}
		System.out.print("\nExecution Complete");
		d_gameEngine.d_logEntryBuffer.setString("Execution Complete");
		next();
		d_gameEngine.getPhase().assignArmies();
		return "";
	}

	@Override
	public void next() {
		d_gameEngine.setPhase(new AssignArmies(d_gameEngine));
	}

}
