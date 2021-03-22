package controller.state.gamephase.gameplay;

import java.util.HashSet;

import controller.GameEngine;
import entities.Continent;
import entities.Player;
import entities.orders.Orders;
import entities.orders.ShowMap;

/**
 * AssignArmies class that inherits GamePlay interface to support functions during this command
 */
public class AssignArmies extends GamePlay {

	/**
	 * constructor method that takes game engine object from the parent class 
	 * @param p_gameEngine object of the game engine
	 */
	public AssignArmies(GameEngine p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	/**
	 * function to assign armies to the players
	 */
	@Override
	public String assignArmies() {
		// TODO Auto-generated method stub
		try {
			checkContinentOwnership();
//			System.out.println(d_gameEngine.a);
			for (Player l_player : d_gameEngine.d_players.values()) {
				l_player.setNumberOfArmies();
			}
			System.out.println("Armies assigned");
			return "Assigned armies to players";
		}
		finally {
			next();
			d_gameEngine.d_logEntryBuffer.setString("Assigned Reinforcements to players");
			d_gameEngine.getPhase().issueOrders();
		}
	}
	
	/**
	 * function to check whether continent is owned by the players before assigning countries  
	 * @return string to output result of continent ownership
	 */
	public String checkContinentOwnership() {
		for (Player l_player : d_gameEngine.d_players.values()) {
			for (Continent l_continent : d_gameEngine.getGameMap().getContinents().values()) {
				if (l_player.checkContinent(l_continent)) {
					l_player.addContinent(l_continent);
				}
			}
		}
		return "Checked continent ownership";
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
	 * @param p_splittedCommand splitted command parts used for execution of command
	 * @return string to print the invalid command message
	 */
	public String deploy(String[] p_splittedCommand) {
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
	 * @return string to print the invalid command message
	 */
	@Override
	public String issueOrders() {
		return printInvalidCommandMessage();
	}
	
	/**
	 * function to print invalid command as the following command cannot be used in this phase
	 * @return string to print the invalid command message
	 */
	public String executeOrders() {
		return printInvalidCommandMessage();
	}

	/**
	 * function to proceed to the next phase of the game
	 */
	@Override
	public void next() {
		d_gameEngine.setPhase(new IssueOrders(d_gameEngine));		
	}
}
