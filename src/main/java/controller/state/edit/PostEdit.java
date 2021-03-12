package controller.state.edit;

import controller.GameEngine;

public class PostEdit extends EditPhase {

	PostEdit(GameEngine p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String editMap(String p_fileName) {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String editContinent() {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String editCountry() {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String editNeighbor() {
		return printInvalidCommandMessage();
		
	}

	@Override
	public String saveMap() {
		// TODO Auto-generated method stub
		return new String();
		
	}

}
