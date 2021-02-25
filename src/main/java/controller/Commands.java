package controller;

/**
 * Class to execute user commands
 *
 */
public class Commands {
	
	private GameStarter d_gameStarter = new GameStarter();
	
	/**
	 * Main execution method to run all the required command types
	 * @param p_splittedCommand splitting of a command to pass to respective methods
	 * @return l_result result after executing a specific type of command
	 */
	public String executeCommand(String[] p_splittedCommand) {
		String l_result = "";
		switch(p_splittedCommand[0]) {
			case "loadmap": 
				l_result = loadMap(p_splittedCommand);
				break;
			
			case "editcontinent":
				l_result = editContinent(p_splittedCommand);
				break;
				
			case "editcountry":
				l_result = editCountry(p_splittedCommand);
				break;
				
			case "editneighbour":
				l_result = editNeighbour(p_splittedCommand);
				break;
				
			case "editmap":
				l_result = editMap(p_splittedCommand);
				break;
				
			case "savemap":
				l_result = saveMap(p_splittedCommand);
				break;
				
			case "gameplayer":
				l_result = gamePlayer(p_splittedCommand);
				break;
			
			case "assigncountries":
				l_result = assignCountries(p_splittedCommand);
				break;
				
			case "validatemap":
				l_result = validateMap(p_splittedCommand);
				break;
				
			case "showmap":
				l_result = showmap();
				break;
				
			default:
				l_result = "Command not found";		
		}
		
		return l_result;
	}
	
	/**
	 * method to load a specific map as entered by the user
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return loaded map(responses positive or negative)
	 */
	public String loadMap(String[] p_splittedCommand) {
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		if(p_splittedCommand[1].split("\\.").length <= 1) {
			return "File extension should be .map";
		}
		if(!p_splittedCommand[1].split("\\.")[1].equals("map")) {
			return "File extension should be .map";
		}
		return d_gameStarter.loadMap(p_splittedCommand[1]);
	}
	
	/**
	 * method to edit the continents 
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return l_result shows whether continents are added or removed
	 */
	public String editContinent(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int i=1;
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		while(i < p_splittedCommand.length) {			
			if(p_splittedCommand[i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				l_commandParts[2] = p_splittedCommand[i+2];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editContinent(l_commandParts);
				i = i + 3;
			}
			else if (p_splittedCommand[i].equals("-remove")) {
				l_commandParts = new String[2];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editContinent(l_commandParts);
				i = i + 2;
			}
			else {
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				i++;
			}
		}
		return l_result;
	}
	
	/**
	 * method to edit countries
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return l_result shows whether countries are added or removed with respect to their continents
	 */
	public String editCountry(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int i=1;
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		while(i < p_splittedCommand.length) {			
			if(p_splittedCommand[i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				l_commandParts[2] = p_splittedCommand[i+2];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editCountry(l_commandParts);
				i = i + 3;
			}
			else if (p_splittedCommand[i].equals("-remove")) {
				l_commandParts = new String[2];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editCountry(l_commandParts);
				i = i + 2;
			}
			else {
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				i++;
			}
		}
		return l_result;
	}
	
	/**
	 * method to edit neighbors
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return l_result shows whether the neighbors are added or removed to the country
	 */
	public String editNeighbour(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int i=1;
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		while(i < p_splittedCommand.length) {			
			if(p_splittedCommand[i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				l_commandParts[2] = p_splittedCommand[i+2];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editNeighbour(l_commandParts);
				i = i + 3;
			}
			else if (p_splittedCommand[i].equals("-remove")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				l_commandParts[2] = p_splittedCommand[i+2];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.editNeighbour(l_commandParts);
				i = i + 3;
			}
			else {
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				i++;
			}
		}
		return l_result;
	}
	
	/**
	 * method to edit map
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return shows whether map is available to edit or not
	 */
	public String editMap(String[] p_splittedCommand) {
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		if(p_splittedCommand[1].split("\\.").length <= 1) {
			return "File extension should be .map";
		}
		if(!p_splittedCommand[1].split("\\.")[1].equals("map")) {
			return "File extension should be .map";
		}
		return d_gameStarter.editMap(p_splittedCommand[1]);
	}
	
	/**
	 * method to save map
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return shows whether map is saved or not
	 */
	public String saveMap(String[] p_splittedCommand) {
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		if(p_splittedCommand[1].split("\\.").length <= 1) {
			return "File extension should be .map";
		}
		if(!p_splittedCommand[1].split("\\.")[1].equals("map")) {
			return "File extension should be .map";
		}
		return d_gameStarter.saveMap(p_splittedCommand[1]);
	}
	
	/**
	 * method to add or remove player
	 * @param p_splittedCommand splitted commands to extract sub parts
	 * @return l_result shows whether players are added or not
	 */
	public String gamePlayer(String[] p_splittedCommand) {
		String[] l_commandParts;
		String l_result = "";
		int i=1;
		if(p_splittedCommand.length < 2) {
			return "Please enter valid command";
		}
		while(i < p_splittedCommand.length) {			
			if(p_splittedCommand[i].equals("-add")) {
				l_commandParts = new String[3];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.gamePlayer(l_commandParts);
				i = i + 2;
			}
			else if (p_splittedCommand[i].equals("-remove")) {
				l_commandParts = new String[2];
				l_commandParts[0] = p_splittedCommand[i];
				l_commandParts[1] = p_splittedCommand[i+1];
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += d_gameStarter.gamePlayer(l_commandParts);
				i = i + 2;
			}
			else {
				if(!l_result.equals("")) {
					l_result += "\n";
				}
				l_result += "Command needs to have -add or -remove.";
				i++;
			}
		}
		return l_result;
	}
	
	/**
	 * method to validate the map
	 * @param p_splittedCommand command for map validation
	 * @return result of validating a map
	 */
	public String validateMap(String[] p_splittedCommand) {
		if(p_splittedCommand.length > 1) {
			return "Please enter valid command";}
		return d_gameStarter.validateMap();
	}
	
	/**
	 * method to randomly assign countries to players and start game phase
	 * @param p_splittedCommand command to assign countries
	 * @return shows message that game phase is completed
	 */
	public String assignCountries(String[] p_splittedCommand) {
		if(p_splittedCommand.length > 1) {
			return String.format("Invalid Command");
		}
		return d_gameStarter.assignCountries();
	}
	
	/**
	 * method to show a map to user
	 * @return map in string format
	 */
	public String showmap() {
		return d_gameStarter.showmap();
	}
}

