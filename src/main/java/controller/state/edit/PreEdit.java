package controller.state.edit;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import controller.GameEngine;
import controller.GameStarter;
import entities.GameMap;

public class PreEdit extends EditPhase {
	
	public PreEdit(GameStarter p_gameEngine) {
		super(p_gameEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String editMap(String p_fileName) {
		String l_result;
		l_result = d_gameEngine.getGameMap().loadMap(p_fileName);
		if (l_result.equals(String.format("Map \"%s\" cannot be loaded", p_fileName))) {
			return l_result;
		}
		if (!Files.exists(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_fileName))) {
			try {
				Files.createDirectories(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps"));
				Files.createFile(Paths.get(Paths.get("").toAbsolutePath().toString() + "/maps/" + p_fileName));
			} catch (IOException p_e) {
				p_e.printStackTrace();
			}
		}
		l_result = String.format("Map \"%s\" ready for edit", p_fileName);
		return l_result;
	}

	@Override
	public String editContinent(String[] p_commandSplitted) {
		String l_result;
			if (p_commandSplitted[0].equals("-add")) {
				l_result = d_gameEngine.getGameMap().addContinent(Integer.parseInt(p_commandSplitted[1]),
						Integer.parseInt(p_commandSplitted[2]));
			} else {
				l_result = d_gameEngine.getGameMap().removeContinent(Integer.parseInt(p_commandSplitted[1]));
			}
		return l_result;
	}

	@Override
	public String editCountry(String[] p_commandSplitted) {
		// TODO Auto-generated method stub
		return new String();

	}

	@Override
	public String editNeighbor(String[] p_commandSplitted) {
		// TODO Auto-generated method stub
		return new String();

	}

	@Override
	public String saveMap() {
		return printInvalidCommandMessage();

	}


}
