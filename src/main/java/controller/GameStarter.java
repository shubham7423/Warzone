package controller;


import controller.state.Phase;
import controller.state.edit.PreEdit;
import entities.GameMap;

public class GameStarter {
	Phase d_phase;
	GameMap d_gameMap = new GameMap();
	
	public GameMap getGameMap() {
		return d_gameMap;
	}
	
	public void setPhase(Phase p_phase) {
		d_phase = p_phase;
	}
	
	public String executeCommand(String[] p_splittedCommand) {
		String l_result = "";
		switch (p_splittedCommand[0]) {
//		case "loadmap":
//			l_result = d_phase.loadMap(p_splittedCommand);
//			break;
//
		case "editcontinent":
			l_result = editContinent(p_splittedCommand);
			break;
//
		case "editcountry":
			l_result = editCountry(p_splittedCommand);
			break;
//
		case "editneighbor":
			l_result = editNeighbor(p_splittedCommand);
			break;

		case "editmap":
			l_result = editMap(p_splittedCommand);
			break;

//		case "savemap":
//			l_result = saveMap(p_splittedCommand);
//			break;

		case "gameplayer":
			l_result = gamePlayer(p_splittedCommand);
			break;

//		case "assigncountries":
//			l_result = assignCountries(p_splittedCommand);
//			break;
//
//		case "validatemap":
//			l_result = validateMap(p_splittedCommand);
//			break;
//
		case "showmap":
			l_result = showmap();
			break;

		default:
			l_result = "Command not found";
		}

		return l_result;
	}
	
	public String editMap(String[] p_splittedCommand) {
		if (p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		if (p_splittedCommand[1].split("\\.").length <= 1) {
			return "File extension should be .map";
		}
		if (!p_splittedCommand[1].split("\\.")[1].equals("map")) {
			return "File extension should be .map";
		}
		return d_phase.editMap(p_splittedCommand[1]);
	}
	
	public String gamePlayer(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		while (l_i < p_splittedCommand.length) {
			if (p_splittedCommand[l_i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.gamePlayer(l_commandParts);
				l_i = l_i + 2;
			} else if (p_splittedCommand[l_i].equals("-remove")) {
				l_commandParts = new String[2];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.gamePlayer(l_commandParts);
				l_i = l_i + 2;
			} else {
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				l_i++;
			}
		}
		return l_result;
	}
	
	public String showmap() {
		return d_phase.showMap();
	}
	
	public String editContinent(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		while (l_i < p_splittedCommand.length) {
			if (p_splittedCommand[l_i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				l_commandParts[2] = p_splittedCommand[l_i + 2];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.editContinent(l_commandParts);
				l_i = l_i + 3;
			} else if (p_splittedCommand[l_i].equals("-remove")) {
				l_commandParts = new String[2];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.editContinent(l_commandParts);
				l_i = l_i + 2;
			} else {
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				l_i++;
			}
		}
		return l_result;
	}
	
	public String editCountry(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		while (l_i < p_splittedCommand.length) {
			if (p_splittedCommand[l_i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				l_commandParts[2] = p_splittedCommand[l_i + 2];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.editCountry(l_commandParts);
				l_i = l_i + 3;
			} else if (p_splittedCommand[l_i].equals("-remove")) {
				l_commandParts = new String[2];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.editCountry(l_commandParts);
				l_i = l_i + 2;
			} else {
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				l_i++;
			}
		}
		return l_result;
	}
	
	public String editNeighbor(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int l_i = 1;
		if (p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		while (l_i < p_splittedCommand.length){
			if (p_splittedCommand[l_i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				l_commandParts[2] = p_splittedCommand[l_i + 2];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.editNeighbor(l_commandParts);
				l_i = l_i + 3;
			} else if (p_splittedCommand[l_i].equals("-remove")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[l_i];
				l_commandParts[1] = p_splittedCommand[l_i + 1];
				l_commandParts[2] = p_splittedCommand[l_i + 2];
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_phase.editNeighbor(l_commandParts);
				l_i = l_i + 3;
			} else {
				if (!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				l_i++;
			}
		}
		return l_result;
	}

	
	public static void main(String[] args) {
//		Commands l_commands = new Commands();
		UserCommand l_userCommand = new UserCommand();
		GameStarter l_gameStarter = new GameStarter();
		l_gameStarter.setPhase(new PreEdit(l_gameStarter));
		System.out.println("Welcome to Warzone");
		while (true) {
			String[] l_splittedCommandString = l_userCommand.getCommand();
			if (l_splittedCommandString[0].equals("exit()")) {
				break;
			}
			System.out.println(l_gameStarter.executeCommand(l_splittedCommandString));
		}
		System.out.print("\nThank you for playing Warzone :)");
		l_userCommand.l_scannerScanner.close();
	}
}
