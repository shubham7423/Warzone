package controller.state.edit;

import controller.GameEngine;
import controller.GameStarter;

public class PostEdit extends EditPhase {

	PostEdit(GameStarter p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String editMap(String p_fileName) {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String editContinent(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String editCountry(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String editNeighbor(String[] p_commandSplitted) {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String saveMap() {
		// TODO Auto-generated method stub
		return new String();
		
	}

}
