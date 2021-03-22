package controller.state.gamephase.gameplay;

import controller.GameEngine;

/**
 * ExecuteOrder class that inherits GamePlay interface to support functions during this command
 */
public class ExecuteOrders extends GamePlay {

	/**
	 * constructor method that takes game engine object from the parent class 
	 * @param p_gameEngine object of the game engine
	 */
	public ExecuteOrders(GameEngine p_gameEngine) {
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
	 * function to print invalid command as the following command cannot be used in this phase
	 * @return string to print the invalid command message
	 */
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

	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @param p_commandSplitted splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	@Override
	public String deploy(String[] p_commandSplitted) {
		// TODO Auto-generated method stub
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
	 * function to execute order given by the player
	 * @return string to output result of execution of order
	 */
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

	/**
	 * function to proceed to the next phase of the game
	 */
	@Override
	public void next() {
		d_gameEngine.setPhase(new AssignArmies(d_gameEngine));
	}

}
