package controller.state.gamephase.endphase;

import controller.GameEngine;
import controller.state.gamephase.GamePhase;

public class EndPhase extends GamePhase {

	public EndPhase(GameEngine p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String loadMap(String p_fileName) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}

	@Override
	public String gamePlayer(String[] p_commandSplitted) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}

	@Override
	public String addPlayer(String p_playerName) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}

	@Override
	public String removePlayer(String p_playerName) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}

	@Override
	public String assignCountries() {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
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

	@Override
	public String advance(String[] p_commandSplitted) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}

	@Override
	public String bomb(String[] p_commandSplitted) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}

	@Override
	public String blockade(String[] p_commandSplitted) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}

	@Override
	public String airlift(String[] p_commandSplitted) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}

	@Override
	public String diplomacy(String[] p_commandSplitted) {
		// TODO Auto-generated method stub
		return printInvalidCommandMessage();
	}
	
	public String printWinner(String p_playerName) {
		return "\nWinner:  " + p_playerName;
	}

	@Override
	public void next() {
		
	}

	@Override
	public String executeOrders() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkContinentOwnership() {
		// TODO Auto-generated method stub
		return null;
	}

}
